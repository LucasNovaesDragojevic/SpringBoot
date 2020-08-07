package br.com.forum.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.forum.model.Topico;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AtualizacaoTopicoForm 
{
	@NotBlank @Size(min = 5, max = 140)
	private String titulo;
	@NotBlank @Size(min = 5)
	private String mensagem;
	
	public Topico atualizar(Topico topico) 
	{
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
}
