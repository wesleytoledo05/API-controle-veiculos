package com.controle.controlecarros.services;

import java.util.Optional;

import com.controle.controlecarros.entidades.Caracteristicas;
import com.controle.controlecarros.entidades.Usuario;
import com.controle.controlecarros.entidades.Veiculo;
import com.controle.controlecarros.error.ResourceNotFoundException;
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

    @Autowired
    private FipeService fipeService;

    public Veiculo postCar(Veiculo veiculo) throws Exception {

        Optional<Usuario> usuarioExistente = usuarioRepo.findByCpf(veiculo.getUsuario().getCpf());

        if (usuarioExistente.isPresent()) {
            Veiculo veiculoNovo = new Veiculo();
            Caracteristicas caracteristicas = fipeService.buscaValor(veiculo.getMarca(), veiculo.getModelo_veiculo(),
                    veiculo.getAno());

            veiculoNovo.setUsuario(usuarioExistente.get());
            veiculoNovo.setAno(caracteristicas.getAno());
            veiculoNovo.setMarca(caracteristicas.getMarca());
            veiculoNovo.setModelo_veiculo(caracteristicas.getModelo());
            veiculoNovo.setValor(caracteristicas.getValor());

            veiculoRepo.save(veiculoNovo);

            usuarioExistente.get().getVeiculo().add(veiculoNovo);

            return veiculoNovo;
        } else {

            throw new Exception();
        }
    }

    public Usuario postUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> usuarioCpfExistente = usuarioRepo.findByCpf(usuario.getCpf());
        Optional<Usuario> usuarioEmailExistente = usuarioRepo.findByEmail(usuario.getEmail());

        if (usuarioCpfExistente.isPresent() || usuarioEmailExistente.isPresent()) {
            throw new Exception("CPF ou EMAIL duplicado");
        } else {
            return usuarioRepo.save(usuario);
        }
    }

    public Usuario buscaPorCpf(String cpf) throws Exception {
       
        return usuarioRepo.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Nao existe um usuário com o CPF = " + cpf));

    }

}
