package br.com.kanban.kanban.config;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Component;

import br.com.kanban.kanban.dto.updateUserDto;
import br.com.kanban.kanban.model.Quadro;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.service.QuadroService;

@Component
public class UpdateUserDtoToUsuarioConverter {

    private final QuadroService quadroService;

    public UpdateUserDtoToUsuarioConverter(QuadroService quadroService) {
        this.quadroService = quadroService;
    }

    public Usuario convert(updateUserDto source) {
        Usuario usuario = new Usuario();
        usuario.setNome(source.getNome());
        usuario.setEmail(source.getEmail());
        usuario.setSenha(source.getSenha());

        List<Quadro> quadros = new ArrayList<>();
        if (source.getQuadros() != null) {
            for (Long quadroId : source.getQuadros()) {
                Quadro quadro = quadroService.getQuadroById(quadroId);
                if (quadro != null) {
                    quadros.add(quadro);
                } else {
                    throw new IllegalArgumentException("Quadro não encontrado com o ID: " + quadroId);
                }
            }
        }
        usuario.setQuadros(quadros);

        return usuario;
    }
}
