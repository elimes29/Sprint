
package com.er.noticiero.servicios;

import com.er.noticiero.entidades.Noticia;
import com.er.noticiero.repositorios.NoticiaRepositorio;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {
    
    @Autowired
    private NoticiaRepositorio noticiaRepositorio;
    

    public void crearNoticia(String titulo, String cuerpo) throws Exception {
        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        guardarNoticia(noticia);
    }
    
    @Transactional
    public Noticia guardarNoticia(Noticia noticia) throws Exception {
        validarNoticia(noticia);
        return noticiaRepositorio.save(noticia);
    }
    
    public void validarNoticia(Noticia noticia) throws Exception {

        if (noticia.getTitulo().isEmpty() || noticia.getTitulo() == null) {
            throw new Exception("El título NO puede estar vacío");
        }

        if (noticia.getCuerpo().isEmpty() || noticia.getCuerpo() == null) {
            throw new Exception("El cuerpo NO puede estar vacío");
        }

    }

        @Transactional
    public void eliminarNoticia(String id) {
        noticiaRepositorio.delete(noticiaRepositorio.getById(id));
    }

        public List<Noticia> listarNoticias() {
        List<Noticia> noticias = noticiaRepositorio.findAll();
        System.out.println("Imprime algo, please");
        //Ordenano noticias por fecha de creacion
       Collections.sort(noticias, (Noticia n1, Noticia n2) -> n2.getFechaCreacion().compareTo(n2.getFechaCreacion()));
        return noticias;
    }
    
        @Transactional
        public void modificarNoticia(String id, String titulo, String cuerpo) throws Exception {
        Noticia noticia = buscarNoticiaPorId(id);
        if (titulo.isEmpty() || titulo == null) {
            throw new Exception("El título NO puede estar vacío");
        }
        
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new Exception("El cuerpo NO puede estar vacío");
        }
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticiaRepositorio.save(noticia);
    }
        
        public Noticia buscarNoticiaPorId(String id) {
        Noticia noticia = new Noticia();
        Optional<Noticia> respuesta = noticiaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            noticia = respuesta.get();
        }
        return noticia;
    }
        
}
