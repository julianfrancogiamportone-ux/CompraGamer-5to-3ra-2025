package com.example.CompraGamer.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orden {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaCreacion;
    private BigDecimal total;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "orden") 
    @JsonIgnore
    private List<CarritoDeCompra> items;

    public Orden() { 
    	super(); 
    	}

    public Orden(Long id, LocalDateTime fechaCreacion, BigDecimal total, String estado, Usuario usuario) {
        super();
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.estado = estado;
        this.usuario = usuario;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<CarritoDeCompra> getItems() {
		return items;
	}

	public void setItems(List<CarritoDeCompra> items) {
		this.items = items;
	}


}
