package com.controle.controlecarros.repositorio;


import java.util.Optional;

import com.controle.controlecarros.entidades.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);

}

