package com.controle.controlecarros.controler;

import java.util.List;

import javax.validation.Valid;

import com.controle.controlecarros.entidades.Caracteristicas;
import com.controle.controlecarros.entidades.FipeResposta;
import com.controle.controlecarros.entidades.Veiculo;
import com.controle.controlecarros.error.ResourceNotFoundException;
import com.controle.controlecarros.repositorios.VeiculoRepositorio;
import com.controle.controlecarros.services.ControleVeiculoService;
import com.controle.controlecarros.services.FipeService;

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
@RequestMapping({ "/veiculo" })
public class VeiculoController {

    @Autowired
    private ControleVeiculoService controleService;


    @Autowired
   private VeiculoRepositorio veiculoRepo;

    @Autowired
    private FipeService fipeService;

    @GetMapping
  public ResponseEntity<List<Veiculo>> listCarros() {

   try {
      List<Veiculo> retornoVeiculos = veiculoRepo.findAll();

      return new ResponseEntity<>(retornoVeiculos, HttpStatus.OK);
    } catch (Exception e) {
      e.getMessage();
      throw new ResourceNotFoundException(String.valueOf("Nenhum carro encontrado!"));
    }
  }


    @PostMapping(consumes =  "application/json" )
    public ResponseEntity<Veiculo> adicionaVeiculo(@Valid @RequestBody Veiculo veiculo) {
        try {
            Veiculo veiculoCar = controleService.postCar(veiculo);
            return new ResponseEntity<>(veiculoCar, HttpStatus.CREATED);
        } catch (Exception e) {
          e.getMessage();
          throw new ResourceNotFoundException(String.valueOf("Erro ao adicionar veiculo!"));
        }
    }

    @GetMapping("/marcas")
    public ResponseEntity<List<FipeResposta>> getTodasMarcas() {
      try{
        List<FipeResposta> fipe = fipeService.buscarTodasMarcas();
        return ResponseEntity.ok().body(fipe);
      }catch(Exception e){
        return ResponseEntity.notFound().build();
      }

    }

    @GetMapping("/marcas/{marca}/modelos")
    public ResponseEntity<FipeResposta> getModeloVeiculo(@PathVariable String marca) {
      try{
        FipeResposta fipe = fipeService.buscaModelos(marca);
        return ResponseEntity.ok().body(fipe);
      }catch(Exception e){
        return ResponseEntity.notFound().build();
      }
    }

    @GetMapping("/marcas/{marca}/modelos/{modelos}/anos")
    public ResponseEntity<List<FipeResposta>> getAnoVeiculo(@PathVariable String marca, @PathVariable String modelos) {
        try{
            List<FipeResposta> fipe = fipeService.buscaModeloAno(marca, modelos);
            return ResponseEntity.ok().body(fipe);
        }catch(Exception e){
          return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/marcas/{marca}/modelos/{modelos}/anos/{anos}")
    public ResponseEntity<Caracteristicas> getInfoVeiculo(@PathVariable String marca, @PathVariable String modelos, @PathVariable String anos) {

      try{
        Caracteristicas fipe = fipeService.buscaValor(marca, modelos, anos);
        return ResponseEntity.ok().body(fipe);
      }catch(Exception e){
        return ResponseEntity.notFound().build();
      }
    }

}