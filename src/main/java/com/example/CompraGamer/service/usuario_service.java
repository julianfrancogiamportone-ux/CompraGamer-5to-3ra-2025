package com.example.CompraGamer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CompraGamer.entity.Usuario;
import com.example.CompraGamer.repository.usuario_repositorio;
import com.example.CompraGamer.iservice.usuario_iservice;

@Service
public class usuario_service implements usuario_iservice {

    @Autowired
    private usuario_repositorio uRepository;

    @Override
    public List<Usuario> findAllUsuario() {
        return uRepository.findAll();
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return uRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findAllUsuarioById(Long id) {
        return uRepository.findById(id);
    }
}