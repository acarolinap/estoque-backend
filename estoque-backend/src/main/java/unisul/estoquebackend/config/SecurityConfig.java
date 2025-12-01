package unisul.estoquebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import unisul.estoquebackend.auth.domain.User;
import unisul.estoquebackend.auth.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserRepository userRepository;
	
	public SecurityConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthFilter) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			// CORREÇÃO: Usa o padrão Customizer.withDefaults() para carregar o CorsConfig corretamente
			.cors(Customizer.withDefaults())
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/**").permitAll()
				// CORREÇÃO: Libera o método OPTIONS para evitar erro de CORS/403 no Delete/Put
				.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.anyRequest().authenticated()
			)
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return email -> {
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
			
			return org.springframework.security.core.userdetails.User
					.builder()
					.username(user.getEmail())
					.password(user.getPassword())
					.authorities("USER")
					.build();
		};
	}
}
