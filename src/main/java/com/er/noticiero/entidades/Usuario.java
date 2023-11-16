
package com.er.noticiero.entidades;

import com.er.noticiero.enumeraciones.Rol;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;
    @Column(name="nombre_usuario")
    private String nombre;
    @Column(name="clave")
    private String clave;
    @Column(name = "fecha_alta", columnDefinition = "DATETIME")
    private LocalDateTime fechaAlta;
    @Column(name="rol")
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Column(name="activo")
    private Boolean activo;
    
    
    public Usuario() {
        this.activo = true;
        this.fechaAlta =  LocalDateTime.now();
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public Boolean getActivo() {
        return activo;
    }


    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
}
