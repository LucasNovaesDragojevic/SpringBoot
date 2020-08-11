package br.com.forum.form;

import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;

@Getter @Setter @ToString
public class LoginForm 
{
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	public UsernamePasswordAuthenticationToken converter() 
	{
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
