package com.er.noticiero.controladores;

import com.er.noticiero.entidades.Noticia;
import com.er.noticiero.servicios.NoticiaServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    NoticiaServicio noticiaServicio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/inicio")
    public String inicioUsuario(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "inicio.html";
    }

}
