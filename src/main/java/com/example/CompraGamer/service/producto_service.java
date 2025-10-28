package com.example.CompraGamer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CompraGamer.entity.Producto;
import com.example.CompraGamer.repository.producto_repositorio;
import com.example.CompraGamer.iservice.i_producto_service;

@Service
public class producto_service implements i_producto_service{
	
	@Autowired
	private producto_repositorio pRepository;
	
	@Override
	public List<Producto> findAllProducto(){
		return pRepository.findAll();
	}
	
	@Override
	public Producto saveProducto(Producto producto) {
		return pRepository.save(producto);
	}
	
	@Override
	public Optional<Producto> findAllProductoById(Long id){
		return pRepository.findById(id);
	}

}
