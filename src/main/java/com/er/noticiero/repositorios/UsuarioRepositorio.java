
package com.er.noticiero.repositorios;

import com.er.noticiero.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio  extends JpaRepository<Usuario, String>{
    
    @Query("SELECT u FROM usuarios u WHERE u.correo = :correo")
    public Usuario buscarPorEmail(@Param("correo") String correo);
}
