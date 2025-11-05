package com.example.CompraGamer.entity;

import jakarta.persistence.*;

@Entity
public class CarritoDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuario_id;
    private Long orden_id;
    private Long producto_id;
    private Integer cantidad;

    public CarritoDeCompra() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuario_id() { return usuario_id; }
    public void setUsuario_id(Long usuario_id) { this.usuario_id = usuario_id; }

    public Long getOrden_id() { return orden_id; }
    public void setOrden_id(Long orden_id) { this.orden_id = orden_id; }

    public Long getProducto_id() { return producto_id; }
    public void setProducto_id(Long producto_id) { this.producto_id = producto_id; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
