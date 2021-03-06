package br.com.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.forum.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService 
{
	@Value("${forum.jwt.secret}")
	private String secret;
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	public String gerarToken(Authentication authentication) 
	{
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date exipiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
					.setIssuer("API do Fórum da Alura")
					.setSubject(logado.getId().toString())
					.setIssuedAt(hoje)
					.setExpiration(exipiracao)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
	}

	public Boolean isTokenValido(String token) 
	{
		try
		{
			Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public Long getIdUsuario(String token) 
	{
		Claims body = Jwts.parser()
							.setSigningKey(secret)
							.parseClaimsJws(token)
							.getBody();
		return Long.parseLong(body.getSubject());
	}
}
