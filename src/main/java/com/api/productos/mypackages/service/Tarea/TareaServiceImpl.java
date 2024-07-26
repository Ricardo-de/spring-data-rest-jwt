package com.api.productos.mypackages.service.Tarea;


import java.util.List;
import java.util.stream.Collectors;

import com.api.productos.mypackages.entities.tarea.Tarea;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.productos.mypackages.repositories.interfaces.tarea.ITareaRepository;


@Service
public class TareaServiceImpl implements ITareaService 	{
	private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(TareaServiceImpl.class);

	private final ITareaRepository iTareaRepository;

    public TareaServiceImpl(ITareaRepository iTareaRepository) {
        this.iTareaRepository = iTareaRepository;
	}

	@Override
	public List<Tarea> listadoTareas(Pageable pageable) {
		Page<Tarea> tareasPage = iTareaRepository.findAll(pageable);
		return tareasPage.stream()
				.map(tarea -> new Tarea(tarea.getId(), tarea.getDescripcion(), tarea.getFecha()))
				.collect(Collectors.toList());
	}

	@Override
	public boolean agregarTarea(Tarea tarea) {
		try {
			if (tarea == null) {
				logger.error("ERROR AGREGAR_TAREA: LA TAREA ES NULA!");
				return false;				
			}
			else {
				iTareaRepository.save(tarea);
				return true;
			}
		}catch(Exception e) {
			logger.error("ERROR AGREGAR_TAREA: LA TAREA NO SE HA GUARDADO!");
			return false;
		}
	}

	@Override
	public boolean actualizarTarea(Tarea tarea) {
			try {
				if ((tarea == null) || (tarea.getId() == 0)) {
					logger.error("ERROR EDITAR_TAREA:  LA TAREA ES NULA O EL ID ES 0!");
					return false;
				}
				else {
					iTareaRepository.save(tarea);
					return true;
				}
			}catch(Exception e) {
				logger.error("ERROR EDITAR_TAREA: LA TAREA NO SE HA EDITADO!");
				return false;
			}
		}
	
	@Override
	public boolean eliminarTarea(int id) {
			try {
				if ((id == 0)) {
					logger.error("ERROR ELIMINAR_TAREA: EL ID DE LA TAREA ES 0!");
					return false;
				}
				else {
					Tarea idTarea = iTareaRepository.findById(id);
					iTareaRepository.delete(idTarea);
					return true;
					}
				}catch(Exception e) {
					logger.error("ERROR ELIMINAR_TAREA: LA TAREA NO SE HA ELIMINADO!");
					return false;
				} 
	}
}
