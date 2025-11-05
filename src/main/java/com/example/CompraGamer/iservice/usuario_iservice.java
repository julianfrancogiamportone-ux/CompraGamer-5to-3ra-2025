package com.example.CompraGamer.iservice;

import java.util.List;
import java.util.Optional;

import com.example.CompraGamer.entity.Usuario;

public interface usuario_iservice {

    List<Usuario> findAllUsuario();

    Usuario saveUsuario(Usuario usuario);

    Optional<Usuario> findAllUsuarioById(Long id);
}