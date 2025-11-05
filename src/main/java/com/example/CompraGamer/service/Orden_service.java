package com.example.CompraGamer.service;

import com.example.CompraGamer.entity.Orden;
import com.example.CompraGamer.iservice.i_Orden_service;
import com.example.CompraGamer.repository.Orden_repositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Orden_service implements i_Orden_service {

    private final Orden_repositorio repositorio;

    public Orden_service(Orden_repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Orden> listar() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Orden> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public Orden guardar(Orden orden) {
        return repositorio.save(orden);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
