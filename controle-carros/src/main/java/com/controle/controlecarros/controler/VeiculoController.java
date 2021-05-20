package com.controle.controlecarros.controler;

import java.util.List;

import javax.validation.Valid;

import com.controle.controlecarros.entidades.Caracteristicas;
import com.controle.controlecarros.entidades.FipeResposta;
// import com.controle.controlecarros.entidades.Caracteristicas;
import com.controle.controlecarros.entidades.Veiculo;
import com.controle.controlecarros.services.ControleVeiculoService;
// import com.controle.controlecarros.services.FipeService;
import com.controle.controlecarros.services.FipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
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
    private FipeService fipeService;


    @PostMapping(consumes = { "application/json" })
    public ResponseEntity<Veiculo> adicionaVeiculo(@Valid @RequestBody Veiculo veiculo) {
        try {
            System.out.println("antes");
            Veiculo veiculoCar = controleService.postCar(veiculo);
            return new ResponseEntity<>(veiculoCar, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/marcas")
    public ResponseEntity<List<FipeResposta>> getTodasMarcas() {

        List<FipeResposta> fipeRespostas = fipeService.buscarTodasMarcas();

        return fipeRespostas != null ? ResponseEntity.ok().body(fipeRespostas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/marcas/{marca}/modelos")
    public ResponseEntity<FipeResposta> getModeloVeiculo(@PathVariable String marca) {

        FipeResposta fipeRespostas = fipeService.buscaModelos(marca);

        return fipeRespostas != null ? ResponseEntity.ok().body(fipeRespostas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/marcas/{marca}/modelos/{modelos}/anos")
    public ResponseEntity<List<FipeResposta>> getAnoVeiculo(@PathVariable("marca") String marca,
            @PathVariable("modelos") String modelos) {

        List<FipeResposta> fipeRespostas = fipeService.buscaModeloAno(marca, modelos);

        return fipeRespostas != null ? ResponseEntity.ok().body(fipeRespostas) : ResponseEntity.notFound().build();
    }

    @GetMapping("/marcas/{marca}/modelos/{modelos}/anos/{anos}")
    public ResponseEntity<Caracteristicas> getInfoVeiculo(@PathVariable("marca") String marca,
            @PathVariable("modelos") String modelos, @PathVariable("anos") String anos) {

        Caracteristicas caracteristica = fipeService.buscaInfo(marca, modelos, anos);
        System.out.println(caracteristica.getValor());
        return caracteristica != null ? ResponseEntity.ok().body(caracteristica) : ResponseEntity.notFound().build();
    }

}
