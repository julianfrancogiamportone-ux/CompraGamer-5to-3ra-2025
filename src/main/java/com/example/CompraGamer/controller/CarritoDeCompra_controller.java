package com.example.CompraGamer.controller;

import com.example.CompraGamer.entity.CarritoDeCompra;
import com.example.CompraGamer.iservice.i_CarritoDeCompra_service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoDeCompra_controller {

    private final i_CarritoDeCompra_service service;

    public CarritoDeCompra_controller(i_CarritoDeCompra_service service) {
        this.service = service;
    }

    @GetMapping
    public List<CarritoDeCompra> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CarritoDeCompra buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarritoDeCompra crear(@RequestBody CarritoDeCompra carrito) {
        return service.guardar(carrito);
    }

    @PutMapping("/{id}")
    public CarritoDeCompra actualizar(@PathVariable Long id, @RequestBody CarritoDeCompra carrito) {
        carrito.setId(id);
        return service.guardar(carrito);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
