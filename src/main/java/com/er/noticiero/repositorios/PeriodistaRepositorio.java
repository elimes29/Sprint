
package com.er.noticiero.repositorios;

import com.er.noticiero.entidades.Periodista;
import com.er.noticiero.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodistaRepositorio extends JpaRepository<Periodista, String> {
    
     @Query("SELECT p FROM periodistas p WHERE p.nombre = :nombre")
    public Usuario buscarPorUsuariol(@Param("nombre") String nombre);
    
    
}
