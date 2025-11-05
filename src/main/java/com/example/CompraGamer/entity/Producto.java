package com.example.CompraGamer.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.List;
@Entity
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long nombre;
	private Long descripcion;
	private float precio;
	private int stock;
	private String categotria;
	private String marca;
	private Long imagenURL;
	
	@ManyToMany(mappedBy = "productos")
	private List<CarritoDeCompra> carritos;
	
	public Producto(Long id, Long nombre, Long descripcion, float precio, int stock, String categotria, String marca,
			Long imagenURL) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.categotria = categotria;
		this.marca = marca;
		this.imagenURL = imagenURL;
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
	public Long getNombre() {
		return nombre;
	}
	public void setNombre(Long nombre) {
		this.nombre = nombre;
	}
	public Long getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(Long descripcion) {
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
	public String getCategotria() {
		return categotria;
	}
	public void setCategotria(String categotria) {
		this.categotria = categotria;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Long getImagenURL() {
		return imagenURL;
	}
	public void setImagenURL(Long imagenURL) {
		this.imagenURL = imagenURL;
	}
	
}

