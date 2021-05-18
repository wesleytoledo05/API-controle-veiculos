package com.controle.controlecarros.controler;

import javax.validation.Valid;

import com.controle.controlecarros.entidades.Veiculo;
import com.controle.controlecarros.services.ControleVeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/veiculo" })
public class VeiculoController {
    
    @Autowired
    private ControleVeiculoService controleService;

    // @Autowired
    // private VeiculoRepositorio veiculoRepo;
    
    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<Veiculo> adicionaVeiculo(@Valid @RequestBody Veiculo veiculo){
        try {
            System.out.println("antes");
            Veiculo veiculoCar = controleService.postCar(veiculo);
            return new ResponseEntity<>(veiculoCar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
