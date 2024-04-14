package br.com.kanban.kanban.controller;

import java.util.List;

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
    public ResponseEntity<Quadro> criarQuadro(@RequestBody Quadro quadro) {
        Quadro novoQuadro = quadroService.criarQuadro(quadro);

          // Atualizar usuários associados com o novo quadro
          for (Usuario usuario : quadro.getUsuarios()) {
            usuario.getQuadros().add(novoQuadro);
            usuarioService.alterarUsuario(usuario.getId(), usuario);
        }
        return new ResponseEntity<>(novoQuadro, HttpStatus.CREATED);
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
}