package br.com.kanban.kanban.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanban.kanban.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);

    void deleteById(Long id);

    @SuppressWarnings("unchecked")
    Usuario save(Usuario usuario);    
}
