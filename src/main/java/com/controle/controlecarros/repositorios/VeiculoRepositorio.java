package com.controle.controlecarros.repositorios;

import com.controle.controlecarros.entidades.Veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
                                   //herança
}
