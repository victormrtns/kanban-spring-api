package br.com.kanban.kanban.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import br.com.kanban.kanban.dto.createQuadroDto;
import br.com.kanban.kanban.dto.updateQuadroDto;
import br.com.kanban.kanban.infra.security.TokenService;
import br.com.kanban.kanban.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.kanban.kanban.model.Quadro;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.service.QuadroService;
import br.com.kanban.kanban.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/quadros")
public class QuadroController {

    @Autowired
    private QuadroService quadroService;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity<createQuadroDto> criarQuadro(@RequestHeader("Authorization") String bearerToken,@RequestBody createQuadroDto quadroDto) {

        bearerToken = bearerToken.replace("Bearer ","");
        String email = tokenService.validateToken(bearerToken);
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if(usuario.isPresent()){
            Usuario newUsuario = usuario.get();
            List<Quadro> quadrosDoUsuario = newUsuario.getQuadros();
            for(Quadro quadroExistente : quadrosDoUsuario) {
                if(quadroExistente.getNome().equals(quadroDto.getNome())) {
                    return ResponseEntity.badRequest().build();
                }
            }
            List<Usuario> lista_usuario = new ArrayList<>();
            lista_usuario.add(newUsuario);

            Quadro quadro = new Quadro();
            quadro.setNome(quadroDto.getNome());
            quadro.setUsuarios(lista_usuario);

            quadroService.criarQuadro(quadro);

            return ResponseEntity.ok(quadroDto);
        }

        return ResponseEntity.badRequest().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Quadro> updateQuadro(@RequestHeader("Authorization") String bearerToken,@PathVariable Long id,@RequestBody updateQuadroDto quadroDto) {

        bearerToken = bearerToken.replace("Bearer ","");
        String email = tokenService.validateToken(bearerToken);
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        Quadro quadro = quadroService.getQuadroById(id);
        if(quadro == null){
            return ResponseEntity.badRequest().build();
        }
        if(usuario.isPresent()){
            Usuario newUsuario = usuario.get();
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(newUsuario);
            quadro.setUsuarios(usuarios);
            quadro.setNome(quadroDto.getNome());
            Quadro novoQuadro = quadroService.alterarQuadro(id,quadro);
            return ResponseEntity.ok(novoQuadro);
        }

        return ResponseEntity.badRequest().build();

    }


    @GetMapping
    public ResponseEntity<List<Quadro>> getAllQuadros() {
        List<Quadro> quadros = quadroService.getAllQuadros();
        return new ResponseEntity<>(quadros, HttpStatus.OK);
    }

    @GetMapping("/email")
    public Optional<List<Quadro>> getAllQuadrosByEmail(@RequestHeader("Authorization") String bearerToken ) {
        bearerToken = bearerToken.replace("Bearer ","");
        String email = tokenService.validateToken(bearerToken);
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if(usuario.isPresent()){
            Usuario newUsuario = usuario.get();
            return Optional.ofNullable(newUsuario.getQuadros());
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quadro> getQuadroById(@PathVariable("id") Long id) {
        Quadro quadro = quadroService.getQuadroById(id);
        if (quadro != null) {
            return new ResponseEntity<>(quadro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteQuadroById(@PathVariable Long id) {
        quadroService.deleteQuadroById(id);
    }

}