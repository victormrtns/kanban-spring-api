package br.com.kanban.kanban.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.kanban.kanban.model.Quadro;

@Repository
public interface QuadroRepository extends JpaRepository<Quadro, Long> {
}

