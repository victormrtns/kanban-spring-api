package br.com.kanban.kanban.config;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.kanban.kanban.dto.getUsuarioDto;
import br.com.kanban.kanban.model.Quadro;
import br.com.kanban.kanban.model.Usuario;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsuarioMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.addConverter(quadroListConverter);
    }

    private final AbstractConverter<List<Quadro>, List<Long>> quadroListConverter = new AbstractConverter<>() {
        @Override
        protected List<Long> convert(List<Quadro> source) {
            List<Long> quadrosIds = new ArrayList<>();
            for (Quadro quadro : source) {
                quadrosIds.add(quadro.getId());
            }
            return quadrosIds;
        }
    };

    public getUsuarioDto mapToDto(Usuario usuario) {
        return modelMapper.map(usuario, getUsuarioDto.class);
    }
}
