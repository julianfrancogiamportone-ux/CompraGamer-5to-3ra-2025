package com.example.CompraGamer.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
@Entity
public class Producto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
   
    private String nombre;
    private String descripcion;
    
    private float precio;
    private int stock;
    private String categoria; 
    private String marca;
    private String imagenURL; 
    
    
    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<CarritoDeCompra> itemsEnCarritos;

	
	
	
	public Producto(String nombre, String descripcion, float precio, int stock, String categoria, String marca,
			String imagenURL, List<CarritoDeCompra> itemsEnCarritos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
		this.marca = marca;
		this.imagenURL = imagenURL;
		this.itemsEnCarritos = itemsEnCarritos;
	}
	public Producto() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getImagenURL() {
		return imagenURL;
	}
	public void setImagenURL(String imagenURL) {
		this.imagenURL = imagenURL;
	}
	public List<CarritoDeCompra> getItemsEnCarritos() {
		return itemsEnCarritos;
	}
	public void setItemsEnCarritos(List<CarritoDeCompra> itemsEnCarritos) {
		this.itemsEnCarritos = itemsEnCarritos;
	}
	
	
}

