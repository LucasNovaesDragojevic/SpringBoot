package br.com.forum.config.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ErrorFormDto 
{
	private String campo;
	private String erro;
}
