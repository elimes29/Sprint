package com.er.noticiero.controladores;

import com.er.noticiero.entidades.Noticia;
import com.er.noticiero.entidades.Usuario;
import com.er.noticiero.servicios.NoticiaServicio;
import com.er.noticiero.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    NoticiaServicio noticiaServicio;
    
    @Autowired
    UsuarioServicio usuarioServicio;


    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN', 'ROLE_USER', 'ROLE_PERIODISTA')")
    @GetMapping("/inicio")
    public String inicioUsuario(ModelMap modelo, HttpSession sesion) {
        Usuario logueado = (Usuario) sesion.getAttribute("usuariosesion");
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        
        if (logueado.getRol().toString().equals("ADMIN")){
            return "redirect:/admin/dashboard";
        }
        return "inicio.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {        
        if (error != null){
            modelo.put("error", "Correo o clave invalida");
        }
        return "login.html";
    }
}
