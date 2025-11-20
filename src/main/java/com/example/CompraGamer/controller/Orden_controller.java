package com.example.CompraGamer.controller;

import com.example.CompraGamer.entity.Orden;
import com.example.CompraGamer.iservice.i_Orden_service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
public class Orden_controller {

    private final i_Orden_service service;

    public Orden_controller(i_Orden_service service) {
        this.service = service;
    }

    @GetMapping
    public List<Orden> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Orden buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Orden crear(@RequestBody Orden orden) {
    	
        return service.guardar(orden);
    }

    @PutMapping("/{id}")
    public Orden actualizar(@PathVariable Long id, @RequestBody Orden orden) {
        orden.setId(id);
        return service.guardar(orden);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
