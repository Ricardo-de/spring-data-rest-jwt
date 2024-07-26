package com.api.productos.mypackages.entities.tarea;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tareas")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Tarea implements Serializable{
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id")
	private int id;

	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="fecha")
	private LocalDateTime fecha;
	

	public Tarea(String descripcion, LocalDateTime fecha) {
		super();
		this.descripcion = descripcion;
		this.fecha = fecha;
	}

	public Tarea(int id) {
		super();
		this.id = id;
	}
}
