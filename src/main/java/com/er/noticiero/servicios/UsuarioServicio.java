package com.er.noticiero.servicios;


import com.er.noticiero.entidades.Usuario;
import com.er.noticiero.enumeraciones.Rol;
import com.er.noticiero.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Transactional
    public void registrar(String nombre,  String clave, String clave2, String rol) throws Exception {
        validar(nombre, clave, clave2);

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setClave(new BCryptPasswordEncoder().encode(clave2));
        switch (rol){
            case "USER":
                usuario.setRol(Rol.USER);
                break;
            case "PERIODISTA":
                usuario.setRol(Rol.PERIODISTA);
                break;
            default:
                usuario.setRol(Rol.USER);
        }

        usuarioRepositorio.save(usuario);

    }

    public void validar(String nombre,  String clave, String clave2) throws Exception {

        if (nombre.isEmpty() || nombre == null) {
            throw new Exception("Debe ingresar un nombre");
        }
        if (clave.isEmpty() || clave == null) {
            throw new Exception("Debe ingresar una clave");
        } else if (clave.length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
        if (!(clave.equals(clave2))) {
            throw new Exception("Las contraseñas deben ser iguales");
        }

    }
    
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        return usuarios;
    }

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(nombre.toLowerCase());

        if (usuario != null) {

            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession sesion = attr.getRequest().getSession(true);
            sesion.setAttribute("usuariosesion", usuario);
                    
            return new User(usuario.getNombre(), usuario.getClave(), permisos);
        } else {
            return null;
        }

    }
    
}
