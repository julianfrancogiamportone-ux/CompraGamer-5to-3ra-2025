package com.example.CompraGamer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Orden> ordenes;


    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<CarritoDeCompra> itemsCarrito;


    public Usuario() {
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Orden> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<Orden> ordenes) {
		this.ordenes = ordenes;
	}

	public List<CarritoDeCompra> getItemsCarrito() {
		return itemsCarrito;
	}

	public void setItemsCarrito(List<CarritoDeCompra> itemsCarrito) {
		this.itemsCarrito = itemsCarrito;
	}

	public Usuario(Long id, String nombre, String apellido, String email, String direccion, LocalDate fechaRegistro) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }
}