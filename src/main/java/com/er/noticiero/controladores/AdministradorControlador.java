
package com.er.noticiero.controladores;

import com.er.noticiero.entidades.Noticia;
import com.er.noticiero.entidades.Usuario;
import com.er.noticiero.servicios.NoticiaServicio;
import com.er.noticiero.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdministradorControlador {
    
    @Autowired
    NoticiaServicio noticiaServicio;
    
    @Autowired
    UsuarioServicio usuarioServicio;
    
    
    @GetMapping("/dashboard")
    public String inicioAdmin(ModelMap modelo, HttpSession sesion){
        Usuario logueado = (Usuario) sesion.getAttribute("usuariosesion");
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        List<Usuario> usuarios = usuarioServicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        
        return "inicio.html";
    }
    
}
