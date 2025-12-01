package unisul.estoquebackend.config;

import java.io.IOException;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import unisul.estoquebackend.auth.service.JwtService;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;
	private final UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(JwtService jwtService, @Lazy UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		System.out.println("==========================================================");
		System.out.println(">>> NOVA REQUISIÇÃO RECEBIDA: " + request.getMethod() + " " + request.getRequestURI());
		System.out.println("--- Headers da Requisição ---");
		java.util.Collections.list(request.getHeaderNames())
			.forEach(headerName -> 
				System.out.println(headerName + ": " + request.getHeader(headerName))
			);
		System.out.println("-----------------------------");

		// Adicionado para ignorar rotas de autenticação e requisições OPTIONS
		if (request.getRequestURI().startsWith("/auth") || request.getMethod().equalsIgnoreCase("OPTIONS")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			System.err.println("JWT: Authorization header ausente ou mal formatado");
			filterChain.doFilter(request, response);
			return;
		}

		// Removemos o log genérico daqui para vermos os erros específicos do JwtService
		// O try-catch ainda é útil para capturar outros erros inesperados no filtro.
		try {
			String token = authHeader.substring(7);

			if (jwtService.validateToken(token)) {
				String email = jwtService.getEmailFromToken(token);
				if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = userDetailsService.loadUserByUsername(email);
					UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(
							userDetails,
							null,
							userDetails.getAuthorities()
						);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			System.err.println("Erro inesperado no filtro JWT: " + e.getMessage());
			e.printStackTrace();
		}

		filterChain.doFilter(request, response);
	}
}
