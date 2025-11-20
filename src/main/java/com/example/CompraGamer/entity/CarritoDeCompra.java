package com.example.CompraGamer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CarritoDeCompra {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Orden getOrden() {
		return orden;
	}


	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	@ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
    public CarritoDeCompra() {
        super();
    }

   
    public CarritoDeCompra(Long id, Integer cantidad, Usuario usuario, Producto producto) {
        super();
        this.id = id;
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.producto = producto;
    }

    
    public CarritoDeCompra(Integer cantidad, Usuario usuario, Producto producto) {
        super();
        this.cantidad = cantidad;
        this.usuario = usuario;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
