package com.api.productos.mypackages.repositories.interfaces.usuario;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.productos.mypackages.entities.usuario.Usuario;

@Repository("I_UsuarioRepository")
public interface IUsuarioRepository extends JpaRepository<Usuario, Serializable>{

	public abstract Usuario findById(int id);

	public abstract Usuario findByUsuario(String usuario);

}
