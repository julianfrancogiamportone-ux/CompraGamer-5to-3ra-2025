package com.example.CompraGamer.iservice;

import com.example.CompraGamer.entity.CarritoDeCompra;
import java.util.List;
import java.util.Optional;

public interface i_CarritoDeCompra_service {
    List<CarritoDeCompra> listar();
    Optional<CarritoDeCompra> buscarPorId(Long id);
    CarritoDeCompra guardar(CarritoDeCompra carrito);
    void eliminar(Long id);
}
