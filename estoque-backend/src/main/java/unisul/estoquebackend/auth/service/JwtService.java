package unisul.estoquebackend.auth.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        // A chave deve ter no mínimo 512 bits (64 bytes) para HS512
        if (keyBytes.length < 64) {
             throw new IllegalArgumentException("A chave JWT é muito curta para o algoritmo HS512. Use uma chave com pelo menos 64 bytes.");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(email)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(getSigningKey(), Jwts.SIG.HS512) // FORÇANDO O ALGORITMO CORRETO
                .compact();
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            System.err.println("Erro JWT: Assinatura inválida! " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.err.println("Erro JWT: Token mal formado! " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.err.println("Erro JWT: Token expirado! " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.err.println("Erro JWT: Token não suportado! " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Erro JWT: Argumento ilegal! " + e.getMessage());
        }
        return false;
    }
}
