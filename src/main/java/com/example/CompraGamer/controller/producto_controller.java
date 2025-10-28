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

import com.example.CompraGamer.iservice.i_producto_service;
import com.example.CompraGamer.entity.Producto;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "*")
public class producto_controller {
@Autowired
private i_producto_service i_producto_service;

@GetMapping
public List<Producto>getAllProducto(){
return i_producto_service.findAllProducto();
}

@GetMapping("/CompraGamer/{id}")
public Optional<Producto> getProductoById(@PathVariable Long id) {
return i_producto_service.findAllProductoById(id);
}

@PostMapping
public Producto createProducto(@RequestBody Producto producto) {
return i_producto_service.saveProducto(producto);
}
}