package com.api.productos.mypackages.controllers.tarea;

import java.util.List;

import com.api.productos.mypackages.entities.tarea.Tarea;
import com.api.productos.mypackages.service.Tarea.ITareaService;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/tarea")
@Api(value = "Tarea Management System", tags = "Tarea Management")
public class TareaController {

	ITareaService tareaServiceImpl;
	public TareaController(ITareaService tareaServiceImpl) {
		this.tareaServiceImpl = tareaServiceImpl;
	}

	@GetMapping("/listar")
	@ApiOperation(value = "Obtener una lista de tareas", response = List.class)
	public List<Tarea> listarTareas(@ApiParam(value = "Paginación de tareas") Pageable pageable) {
		return tareaServiceImpl.listadoTareas(pageable);
	}

	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@PostMapping("/insertar")
	@ApiOperation(value = "Agregar una nueva tarea", response = Boolean.class)
	public boolean insertarTarea(@ApiParam(value = "Tarea a agregar", required = true) @RequestBody @Validated Tarea tarea) {
		return tareaServiceImpl.agregarTarea(tarea);
	}

	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@PutMapping("/actualizar")
	@ApiOperation(value = "Editar una tarea existente", response = Boolean.class)
	public boolean actualizarTarea(@ApiParam(value = "Tarea a editar", required = true) @RequestBody @Validated Tarea tarea) {
		return tareaServiceImpl.actualizarTarea(tarea);
	}

	@PreAuthorize("hasRole('ADMINISTRADOR')")
	@DeleteMapping("/eliminar/{id}")
	@ApiOperation(value = "Eliminar una tarea por ID", response = Boolean.class)
	public boolean eliminarTarea(@ApiParam(value = "ID de la tarea a eliminar", required = true) @PathVariable("id") int id) {
		return tareaServiceImpl.eliminarTarea(id);
	}
}