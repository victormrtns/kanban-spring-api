package br.com.kanban.kanban.controller;


import java.util.ArrayList;
import java.util.List;


import br.com.kanban.kanban.dto.ResponseDto;
import br.com.kanban.kanban.dto.createQuadroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.kanban.kanban.model.Quadro;
import br.com.kanban.kanban.model.Usuario;
import br.com.kanban.kanban.service.QuadroService;
import br.com.kanban.kanban.service.UsuarioService;

@RestController
@RequestMapping("/quadros")
public class QuadroController {

    @Autowired
    private QuadroService quadroService;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<createQuadroDto> criarQuadro(@RequestBody createQuadroDto quadroDto) {
        Usuario usuario = new Usuario();
        usuario = usuarioService.findById(quadroDto.getUsuario_id());
        if(usuario == null){
            return ResponseEntity.badRequest().build();
        }

        List<Quadro> quadrosDoUsuario = usuario.getQuadros();
        for(Quadro quadroExistente : quadrosDoUsuario) {
            if(quadroExistente.getNome().equals(quadroDto.getNome())) {
                return ResponseEntity.badRequest().build();
            }
        }

        List<Usuario> lista_usuario = new ArrayList<>();
        lista_usuario.add(usuario);

        Quadro quadro = new Quadro();
        quadro.setNome(quadroDto.getNome());
        quadro.setUsuarios(lista_usuario);

        quadroService.criarQuadro(quadro);

        return ResponseEntity.ok(quadroDto);
    }

    @GetMapping
    public ResponseEntity<List<Quadro>> getAllQuadros() {
        List<Quadro> quadros = quadroService.getAllQuadros();
        return new ResponseEntity<>(quadros, HttpStatus.OK);
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