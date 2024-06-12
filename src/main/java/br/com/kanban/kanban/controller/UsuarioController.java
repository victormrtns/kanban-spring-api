package br.com.kanban.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.kanban.kanban.config.UpdateUserDtoToUsuarioConverter;
import br.com.kanban.kanban.config.UsuarioMapper;
import br.com.kanban.kanban.dto.createUserDto;
import br.com.kanban.kanban.dto.getUsuarioDto;
import br.com.kanban.kanban.dto.updateUserDto;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.service.UsuarioService;
import org.springframework.lang.NonNull;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    private final ModelMapper modelMapper;
    private final UsuarioMapper usuarioMapper;
    private final UpdateUserDtoToUsuarioConverter converter;
 

    @Autowired
    public UsuarioController(UsuarioService usuarioService, ModelMapper modelMapper,UsuarioMapper usuarioMapper,  UpdateUserDtoToUsuarioConverter converter) {
        this.usuarioService = usuarioService;
        this.modelMapper = modelMapper;
        this.usuarioMapper = usuarioMapper;
        this.converter = converter;
        

    }

    @GetMapping("/{id}")
    public ResponseEntity<getUsuarioDto> getUsuarioById( @PathVariable("id") @NonNull Long id) {
        Usuario usuario = usuarioService.findById(id);
        getUsuarioDto usuarioDto = usuarioMapper.mapToDto(usuario);

        return ResponseEntity.ok(usuarioDto);
      
        
    }


    @GetMapping
    public List<getUsuarioDto> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        List<getUsuarioDto> usuariosDto = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            getUsuarioDto usuarioDto = usuarioMapper.mapToDto(usuario);
            usuariosDto.add(usuarioDto);
        }
        return usuariosDto;
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable Long id, @RequestBody updateUserDto dto) {
        // Convertendo UpdateUserDto para Usuario manualmente
        Usuario usuario = converter.convert(dto);
        
        // Definindo o ID do usuário com base no ID do path da requisição
        usuario.setId(id);
        
        // Chamando o serviço para alterar o usuário
        Usuario novoUsuario = usuarioService.alterarUsuario(id, usuario);
        
        // Retornando o resultado da operação
        return ResponseEntity.ok(novoUsuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuarioById(@PathVariable Long id) {
        usuarioService.deleteUsuarioById(id);
    }

}

