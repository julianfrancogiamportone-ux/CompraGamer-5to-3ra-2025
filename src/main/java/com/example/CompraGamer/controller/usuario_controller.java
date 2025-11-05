package com.example.CompraGamer.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CompraGamer.iservice.usuario_iservice;
import com.example.CompraGamer.entity.Usuario;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class usuario_controller {

    @Autowired
    private usuario_iservice usuario_iservice;

    @GetMapping
    public List<Usuario> getAllUsuario() {
        return usuario_iservice.findAllUsuario();
    }

    @GetMapping("/CompraGamer/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        return usuario_iservice.findAllUsuarioById(id);
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuario_iservice.saveUsuario(usuario);
    }
}