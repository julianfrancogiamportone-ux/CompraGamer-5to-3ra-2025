package com.example.CompraGamer.service;

import com.example.CompraGamer.entity.CarritoDeCompra;
import com.example.CompraGamer.iservice.i_CarritoDeCompra_service;
import com.example.CompraGamer.repository.CarritoDeCompra_repositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoDeCompra_service implements i_CarritoDeCompra_service {

    private final CarritoDeCompra_repositorio repositorio;

    public CarritoDeCompra_service(CarritoDeCompra_repositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CarritoDeCompra> listar() {
        return repositorio.findAll();
    }

    @Override
    public Optional<CarritoDeCompra> buscarPorId(Long id) {
        return repositorio.findById(id);
    }

    @Override
    public CarritoDeCompra guardar(CarritoDeCompra carrito) {
        return repositorio.save(carrito);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }
}
