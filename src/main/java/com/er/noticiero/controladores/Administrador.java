
package com.er.noticiero.controladores;

import com.er.noticiero.entidades.Usuario;
import com.er.noticiero.enumeraciones.Rol;
import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {

    public Administrador() {
        this.setRol(Rol.ADMIN);
    }
    
    
    
}
