package br.com.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Curso 
{
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String categoria;

	public Curso(String nome, String categoria) {
		this.nome = nome;
		this.categoria = categoria;
	}
}
