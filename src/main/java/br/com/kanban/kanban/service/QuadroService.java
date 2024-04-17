package br.com.kanban.kanban.service;


import java.util.List;
import java.util.Optional;


import br.com.kanban.kanban.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kanban.kanban.model.Quadro;

import br.com.kanban.kanban.repository.QuadroRepository;


@Service
public class QuadroService {

    @Autowired
    private QuadroRepository quadroRepository;
    @Autowired  // Inject usuarioService here
  private UsuarioService usuarioService;

    public Quadro criarQuadro(Quadro quadro) {
        return quadroRepository.save(quadro);
    }

    public List<Quadro> getAllQuadros() {
        return quadroRepository.findAll();
    }

    public Quadro getQuadroById(Long id) {
        return quadroRepository.findById(id).orElse(null);
    }

    public void deleteQuadroById(Long id) {
        Optional<Quadro> quadroOptional = quadroRepository.findById(id);
        if (quadroOptional.isPresent()) {
            quadroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Quadro não encontrado com o ID: " + id);
        }
    }

    public Quadro alterarQuadro(Long id, Quadro quadroNovo) {
        return quadroRepository.findById(id)
                .map(quadro -> {
                    quadro.setNome(quadroNovo.getNome());
                    quadro.setUsuarios(quadroNovo.getUsuarios());
                    return quadroRepository.save(quadro);
                })
                .orElseThrow(() -> new RuntimeException("Quadro não encontrado com o ID: " + id));
    }
}

    
