package br.com.kanban.kanban.service;


import java.util.List;



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
        // Adicione qualquer lógica de validação aqui, se necessário
        return quadroRepository.save(quadro);
    }

    public List<Quadro> getAllQuadros() {
        return quadroRepository.findAll();
    }

    public Quadro getQuadroById(Long id) {
        return quadroRepository.findById(id).orElse(null);
    }
}

    
