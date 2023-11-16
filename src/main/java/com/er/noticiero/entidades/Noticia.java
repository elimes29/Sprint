
package com.er.noticiero.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity(name="noticias")
public class Noticia implements Serializable {
    
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
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_periodista")
    private Periodista periodista;

    public Noticia() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public Periodista getPeriodista() {
        return periodista;
    }

    public void setPeriodista(Periodista periodista) {
        this.periodista = periodista;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
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
