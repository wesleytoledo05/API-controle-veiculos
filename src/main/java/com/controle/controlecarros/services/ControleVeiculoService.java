package com.controle.controlecarros.services;

import java.util.Optional;

import com.controle.controlecarros.entidades.Caracteristicas;
import com.controle.controlecarros.entidades.Usuario;
import com.controle.controlecarros.entidades.Veiculo;
import com.controle.controlecarros.repositorios.UsuarioRepositorio;
import com.controle.controlecarros.repositorios.VeiculoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

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
            throw new Exception(
                    "Não foi possivel localizar o usuario informado para o cpf: " + veiculo.getUsuario().getCpf());
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

    public Optional<Usuario> buscaPorCpf(String cpf) throws NotFoundException {
        Optional<Usuario> usuarioCpfExistente = usuarioRepo.findByCpf(cpf);
     
        if (usuarioCpfExistente.isPresent()) {
            return usuarioCpfExistente;
        } else {
            throw new NotFoundException("Nao existe um usuário com o CPF = " + cpf);
        }

    }

}