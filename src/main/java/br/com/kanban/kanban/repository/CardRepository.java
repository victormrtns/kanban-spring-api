package br.com.kanban.kanban.repository;

import br.com.kanban.kanban.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByQuadroId(Long quadroId);
}
