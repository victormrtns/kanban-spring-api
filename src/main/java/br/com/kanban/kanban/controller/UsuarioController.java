package br.com.kanban.kanban.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kanban.kanban.config.UpdateUserDtoToUsuarioConverter;
import br.com.kanban.kanban.config.UsuarioMapper;
import br.com.kanban.kanban.dto.createUserDto;
import br.com.kanban.kanban.dto.getUsuarioDto;
import br.com.kanban.kanban.dto.updateUserDto;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.service.UsuarioService;
import org.springframework.lang.NonNull;

@RestController
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
    public ResponseEntity<Usuario> getUsuarioById( @PathVariable("id") @NonNull Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<Usuario> criarUsuario(@RequestBody createUserDto dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
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

