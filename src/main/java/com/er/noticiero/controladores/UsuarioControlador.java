package com.er.noticiero.controladores;

import com.er.noticiero.entidades.Noticia;
import com.er.noticiero.servicios.NoticiaServicio;
import com.er.noticiero.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;
/*
    @GetMapping("/inicio")
    public String inicioUsuario(ModelMap modelo) {
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        return "inicio.html";
    }
*/

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String correo, @RequestParam String clave, @RequestParam String clave2, ModelMap modelo) {

        try {
            usuarioServicio.registrar(nombre, correo, clave, clave2);
            modelo.put("exito", "Usuario registrado exitosamente!!!");
            List<Noticia> noticias = noticiaServicio.listarNoticias();
            modelo.addAttribute("noticias", noticias);
            return "index-afuera.html";
        } catch (Exception ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("correo", correo);

            return "inicio.html";
        }
    }

}
