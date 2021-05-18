package com.controle.controlecarros.controler;


import javax.validation.Valid;

import com.controle.controlecarros.entidades.Usuario;
import com.controle.controlecarros.error.ResourceNotFoundException;
import com.controle.controlecarros.repositorio.UsuarioRepositorio;

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
    private UsuarioRepositorio usuarioRepo;


    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<Usuario> adicionaUsuario(@Valid @RequestBody  Usuario usuario){
          System.out.println("antes");
        try {
        
            usuarioRepo.save(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
          } catch (Exception e) {
            System.out.println("depois");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
          }
        }


    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> usuarioPorCpf(@PathVariable String cpf){

      Usuario usuario = usuarioRepo.findByCpf(cpf)
      .orElseThrow(() -> new ResourceNotFoundException("Nao existe uma pessoa com o CPF = " + cpf));

      return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
