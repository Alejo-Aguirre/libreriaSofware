package co.edu.uniquinidio.proyecto.bean;


import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Scope("session")
@Component
public class SeguridadBean {

    @Getter @Setter
    private String rol;

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    Moderador moderadorSesion;
    @Autowired
    private ModeradorServicio moderadorServicio;

    @Getter @Setter
    private String username,password;


    public String getNombreUsuarioSesion() {
        return usuarioSesion != null ? usuarioSesion.getNombre() : "";
    }



    /**
     * Inicio de sesion del cliente
     * @return
     */
    public String iniciarSesion() {
        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                // Intentar iniciar sesión como moderador
                moderadorSesion = moderadorServicio.iniciarSesion(username, password);
                if (moderadorSesion != null) {
                    rol = "MODERADOR";
                    autenticado = true;
                    return "index?faces-redirect=true";
                }
            } catch (Exception eModerador) {
                // Manejar excepción para el inicio de sesión del moderador
                eModerador.printStackTrace();
            }

            try {
                // Si no es moderador, intentar como usuario normal
                usuarioSesion = usuarioServicio.iniciarSesion(username, password);
                if (usuarioSesion != null) {
                    rol = "USUARIO";
                    autenticado = true;
                    return "index?faces-redirect=true";
                }
            } catch (Exception eUsuario) {
                // Manejar excepción para el inicio de sesión del usuario
                eUsuario.printStackTrace();
            }
        }
        return null;
    }



    public String cerrarSesion() {
        autenticado = false;
        usuarioSesion = null;
        username = null;
        password = null;

        // Invalidar la sesión actual
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "index?faces-redirect=true";
    }

}
