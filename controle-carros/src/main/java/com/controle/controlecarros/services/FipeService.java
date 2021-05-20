package com.controle.controlecarros.services;

import java.util.List;

import com.controle.controlecarros.entidades.Caracteristicas;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://parallelum.com.br/fipe/api/v1/carros/", name = "fipe")
public interface FipeService {
    
    @GetMapping("marcas")
	public List<Caracteristicas> buscarTodasMarcas();
	
	@GetMapping("marcas/{marca}/modelos")
	public Caracteristicas buscaModelos(@PathVariable("marca") String marca);
	
	@GetMapping("marcas/{marca}/modelos/{modelos}/anos")
	public List<Caracteristicas> buscaModeloAno(@PathVariable("marca") String marca,
											@PathVariable("modelos") String modelos);
	
	@GetMapping("marcas/{marca}/modelos/{modelos}/anos/{anos}")
	public Caracteristicas buscaInfo(@PathVariable("marca") String marca,
											@PathVariable("modelos") String modelos,
											@PathVariable("anos") String anos);
    
}
