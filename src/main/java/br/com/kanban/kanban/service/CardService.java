package br.com.kanban.kanban.service;

import br.com.kanban.kanban.dto.cardDto;
import br.com.kanban.kanban.model.Bug;
import br.com.kanban.kanban.model.Card;
import br.com.kanban.kanban.model.Feature;
import br.com.kanban.kanban.model.Quadro;
import br.com.kanban.kanban.repository.CardRepository;
import br.com.kanban.kanban.repository.QuadroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private QuadroRepository quadroRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    public List<Card> getCardsByQuadro(Long quadroId) {
        return cardRepository.findByQuadroId(quadroId);
    }

    public Card createCard(cardDto cardDTO) {
        Optional<Quadro> quadroOpt = quadroRepository.findById(cardDTO.getQuadroId());
        if (!quadroOpt.isPresent()) {
            throw new IllegalArgumentException("Quadro não encontrado");
        }

        Quadro quadro = quadroOpt.get();
        Card card;

        if ("BUG".equalsIgnoreCase(cardDTO.getType())) {
            Bug bug = new Bug();
            bug.setNome(cardDTO.getNome());
            bug.setDescricao(cardDTO.getDescricao());
            bug.setStatus(cardDTO.getStatus());
            bug.setQuadro(quadro);
            card = cardRepository.save(bug);
        } else if ("FEATURE".equalsIgnoreCase(cardDTO.getType())) {
            Feature feature = new Feature();
            feature.setNome(cardDTO.getNome());
            feature.setDescricao(cardDTO.getDescricao());
            feature.setStatus(cardDTO.getStatus());
            feature.setQuadro(quadro);
            card = cardRepository.save(feature);
        } else {
            throw new IllegalArgumentException("Tipo de card inválido");
        }

        return card;
    }

    public Card updateCard(Long id, cardDto cardDTO) {
        Optional<Card> optionalCard = cardRepository.findById(id);
        if (!optionalCard.isPresent()) {
            throw new IllegalArgumentException("Card não encontrado");
        }

        Optional<Quadro> quadroOpt = quadroRepository.findById(cardDTO.getQuadroId());
        if (!quadroOpt.isPresent()) {
            throw new IllegalArgumentException("Quadro não encontrado");
        }

        if(optionalCard.isPresent() && quadroOpt.isPresent()){
            Card card = optionalCard.get();
            Quadro quadro = quadroOpt.get();

            if(card.getQuadro() != quadro){
                throw new IllegalArgumentException("Card não pode ser registrado em outro quadro");
            }
        }

        Quadro quadro = quadroOpt.get();
        Card card = optionalCard.get();
        card.setNome(cardDTO.getNome());
        card.setDescricao(cardDTO.getDescricao());
        card.setStatus(cardDTO.getStatus());
        card.setQuadro(quadro);

        if (card instanceof Bug && "BUG".equalsIgnoreCase(cardDTO.getType())) {
            ((Bug) card).setBug_id(((Bug) card).getBug_id());
        } else if (card instanceof Feature && "FEATURE".equalsIgnoreCase(cardDTO.getType())) {
            ((Feature) card).setFeature_id(((Feature) card).getFeature_id());
        }

        return cardRepository.save(card);
    }

    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}