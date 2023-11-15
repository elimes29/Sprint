
package com.er.noticiero.entidades;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(name = "titulo", columnDefinition = "TEXT")
    private String titulo;
    @Column(name = "cuerpo", columnDefinition = "LONGTEXT")
    private String cuerpo;
    @Column(name = "cuerpo_corto")
    private String cuerpoCorto;
    @Column(name = "fecha_creacion", columnDefinition = "DATETIME")
    private LocalDateTime fechaCreación;

    public Noticia() {
        this.fechaCreación = LocalDateTime.now();
    }

    public LocalDateTime getFechaCreación() {
        return fechaCreación;
    }

    public String getCuerpoCorto() {
        return cuerpoCorto;
    }

    public void setCuerpoCorto(String cuerpoCorto) {
        this.cuerpoCorto = cuerpoCorto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
        this.cuerpoCorto = cuerpo.substring(0, Math.min(cuerpo.length(), 100)) + "...";
    }

    
}
