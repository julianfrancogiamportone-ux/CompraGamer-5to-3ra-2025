package com.example.CompraGamer.iservice;

import java.util.List;
import java.util.Optional;

import com.example.CompraGamer.entity.Producto;

public interface i_producto_service {

	List<Producto> findAllProducto();

	Producto saveProducto(Producto producto);

	Optional<Producto> findAllProductoById(Long id);

}
