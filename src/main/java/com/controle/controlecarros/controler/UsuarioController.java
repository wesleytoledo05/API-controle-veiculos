package com.controle.controlecarros.controler;


import javax.validation.Valid;

import com.controle.controlecarros.entidades.Usuario;
import com.controle.controlecarros.services.ControleVeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/usuario" })
public class UsuarioController {
  
    @Autowired
    private ControleVeiculoService controleService;


    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<Usuario> adicionaUsuario(@Valid @RequestBody  Usuario usuario){
        try {
            Usuario usuarioResp = controleService.postUsuario(usuario);

            return new ResponseEntity<>(usuarioResp, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
          }
        }


    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> usuarioPorCpf(@PathVariable String cpf) throws Exception{

      try{
        Usuario usuario = controleService.buscaPorCpf(cpf);
      return new ResponseEntity<>(usuario, HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
      }
      
    }
}
