package com.controle.controlecarros.repositorio;

import com.controle.controlecarros.entidades.Caracteristicas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicasRepositorio extends JpaRepository <Caracteristicas, Long> {
    
}
