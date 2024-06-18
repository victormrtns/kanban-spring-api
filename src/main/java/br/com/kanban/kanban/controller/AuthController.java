package br.com.kanban.kanban.controller;

import br.com.kanban.kanban.dto.LoginRequestDto;
import br.com.kanban.kanban.dto.ResponseDto;
import br.com.kanban.kanban.dto.createUserDto;
import br.com.kanban.kanban.infra.security.TokenService;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private final UsuarioRepository repository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final TokenService tokenService;

    public AuthController(UsuarioRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto body){
        Usuario usuario = this.repository.findByEmail((body.getEmail())).orElseThrow(() -> new RuntimeException("Usuario Nao Encontrado"));
        if(passwordEncoder.matches(body.getSenha(), usuario.getSenha())){
            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseDto(usuario.getNome(),token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody createUserDto body){
        Optional<Usuario> usuario = this.repository.findByEmail((body.getEmail()));
        if(usuario.isEmpty()){
            Usuario novoUsuario = new Usuario();
            novoUsuario.setSenha(passwordEncoder.encode(body.getSenha()));
            novoUsuario.setEmail(body.getEmail());
            novoUsuario.setNome(body.getNome());
            this.repository.save(novoUsuario);
            String token = this.tokenService.generateToken(novoUsuario);
            return ResponseEntity.ok(new ResponseDto(novoUsuario.getNome(),token));
        }
        return ResponseEntity.badRequest().build();
    }
}
