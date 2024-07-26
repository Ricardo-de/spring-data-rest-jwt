package com.api.productos.mypackages.service.Tarea;

import com.api.productos.mypackages.entities.tarea.Tarea;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITareaService {
    List<Tarea> listadoTareas(Pageable pageable);

    boolean agregarTarea(Tarea tarea);

    boolean actualizarTarea(Tarea tarea);

    boolean eliminarTarea(int id);
}
