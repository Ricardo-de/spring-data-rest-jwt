package com.api.productos.mypackages.repositories.interfaces.tarea;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.api.productos.mypackages.entities.tarea.Tarea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITareaRepository extends JpaRepository<Tarea, Serializable>, PagingAndSortingRepository<Tarea, Serializable>{
public abstract Tarea findById(int id);

public abstract List<Tarea> findByDescripcion(String descripcion);

public abstract List<Tarea> findByFecha(LocalDateTime fecha);

public abstract Page<Tarea> findAll(Pageable pageable);

	
}
