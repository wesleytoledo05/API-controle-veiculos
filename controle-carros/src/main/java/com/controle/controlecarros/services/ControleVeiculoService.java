package com.controle.controlecarros.services;

import java.util.Optional;

import com.controle.controlecarros.entidades.Usuario;
import com.controle.controlecarros.entidades.Veiculo;
import com.controle.controlecarros.repositorio.UsuarioRepositorio;
import com.controle.controlecarros.repositorio.VeiculoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControleVeiculoService {

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Autowired
    private VeiculoRepositorio veiculoRepo;

    public Veiculo postCar(Veiculo veiculo) throws Exception {

        Optional<Usuario> usuarioExistente = usuarioRepo.findByCpf(veiculo.getUsuario().getCpf());

        if (usuarioExistente.isPresent()) {
            Veiculo veiculoNovo = new Veiculo();
            veiculoNovo.setUsuario(usuarioExistente.get());
            veiculoNovo.setAno(veiculo.getAno());
            veiculoNovo.setMarca(veiculo.getMarca());
            veiculoNovo.setModelo_veiculo(veiculo.getModelo_veiculo());
            //veiculoNovo.setValor(calculavalor()); ////Aqui entrara  a função que calcula o valor de acordo com a tabela fipe

            veiculoRepo.save(veiculoNovo);
            usuarioExistente.get().getVeiculo().add(veiculoNovo);

            return veiculoNovo;
        } else {
            
            throw new Exception();
        }
    }
}
