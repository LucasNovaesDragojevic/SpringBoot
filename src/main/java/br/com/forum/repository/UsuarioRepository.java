package br.com.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> 
{
	Usuario findByEmail(String email);
}
