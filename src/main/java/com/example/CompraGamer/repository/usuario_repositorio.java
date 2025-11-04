package com.example.CompraGamer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CompraGamer.entity.Usuario;

public interface usuario_repositorio extends JpaRepository<Usuario, Long> {

}