package com.controle.controlecarros.controler;


import javax.validation.Valid;

import com.controle.controlecarros.entidades.Usuario;
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


    @PostMapping(consumes = {"application/json"})
    public <cpf, email> ResponseEntity<Usuario> adicionaUsuario(@RequestBody @Valid Usuario usuario){
        
        try {
            usuarioRepo.save(usuario);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Usuario> usuarioPorCpf(@PathVariable String cpf){
      return ResponseEntity.ok().body(usuarioRepo.findByCpf(cpf).get());
    }

    // @RequestMapping({"/veiculo"})
    // @PostMapping(consumes = {"application/json"})
    // public ResponseEntity<Veiculo> adicionaVeiculo(@RequestBody @Valid Veiculo veiculo){

    //   try {
    //     veiculoRepo.save(veiculo);
    //     return new ResponseEntity<>(veiculo, HttpStatus.CREATED);
    //   } catch (Exception e) {
    //     return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //   }
    // }

    
    
    
    
    
}
