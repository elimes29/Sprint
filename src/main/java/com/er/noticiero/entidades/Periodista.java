
package com.er.noticiero.entidades;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity(name = "periodistas")
public class Periodista extends Usuario implements Serializable {
    @OneToMany(fetch=FetchType.LAZY, mappedBy="periodista", cascade=CascadeType.ALL)
    private List<Noticia> noticias;
    @Column(name = "sueldo_mensual")
    private Integer sueldoMensual;

    public Periodista() {
    }

    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }
}
