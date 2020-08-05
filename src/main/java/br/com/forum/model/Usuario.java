package br.com.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario 
{
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String email;
	private String senha;
}
