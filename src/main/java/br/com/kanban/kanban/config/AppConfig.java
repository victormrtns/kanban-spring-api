package br.com.kanban.kanban.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import br.com.kanban.kanban.dto.updateUserDto;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.model.Quadro;
import br.com.kanban.kanban.service.QuadroService;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class AppConfig {

    private final QuadroService quadroService;

    @Autowired
    public AppConfig(QuadroService quadroService) {
        this.quadroService = quadroService;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<updateUserDto, Usuario> converter = new Converter<updateUserDto, Usuario>() {
            @Override
            public Usuario convert(MappingContext<updateUserDto, Usuario> context) {
                updateUserDto source = context.getSource();
                Usuario usuario = new Usuario();
                usuario.setNome(source.getNome());
                usuario.setEmail(source.getEmail());
                usuario.setSenha(source.getSenha());

                for (Long quadroId : source.getQuadros()) {
                    Quadro quadro = quadroService.getQuadroById(quadroId);
                    if (quadro != null) {
                        usuario.getQuadros().add(quadro);
                    } else {
                        throw new IllegalArgumentException("Quadro não encontrado com o ID: " + quadroId);
                    }
                }

                return usuario;
            }
        };

        modelMapper.addConverter(converter);

        return modelMapper;
    }
}

