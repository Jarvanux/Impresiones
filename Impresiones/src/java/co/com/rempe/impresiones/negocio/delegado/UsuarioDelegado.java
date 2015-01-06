/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.UsuarioDAO;
import co.com.rempe.impresiones.persistencia.dao.UsuariosDAO;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jhonjaider1000
 */
public class UsuarioDelegado {

    private static UsuarioDelegado instancia = new UsuarioDelegado();

    private UsuarioDelegado() {
    }

    public static UsuarioDelegado getInstancia() {
        return instancia;
    }

    public Respuesta insertarUsuario(Usuarios usuario) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            UsuariosDAO usuariosDAO = new UsuariosDAO(em);
            usuariosDAO.crear(usuario);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Registro insertado!.");
        } catch (Exception ex) {
            Logger.getLogger(ContactoDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al insertar registro.");
        }
        return respuesta;
    }
    
    public Respuesta ingresar(String usuario, String clave){
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.iniciarSesion(usuario, usuario, clave);
            usuarios.setContrasena("No subministrada por el sistema");
            int codigo = (usuarios == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(usuarios);
            respuesta.setMensaje("Datos consultados satisfactoriamente!.");            
            return respuesta;
        } catch (Exception e) {
            Logger.getLogger(UsuarioDelegado.class.getName()).log(Level.SEVERE, null, e);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar datos solicitados.");
            return respuesta;
        }        
    }
    
    public Usuarios consultarUsuario(int idUsuario){
        EntityManager em = null;
        try {            
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.consultarUsuarioPorID(idUsuario);
        } catch (Exception e) {
            System.out.println("Se ha producido un error en la consulta del usuario por id.");
        }
        return null;
    }
    
    public Respuesta buscarAsesores(){
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuario = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuario = dao.buscarSoporte();
            int codigo = (usuario == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            if(codigo == 0){
               usuario = dao.buscarSoporteRecurso2();
            }
            codigo = (usuario == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(usuario);
            respuesta.setMensaje("Asesor consultado satisfactoriamente");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al consultar un asesor.");
        }
        return respuesta;
    }
    
    public Respuesta consultarSession(HttpServletRequest request){
        Respuesta respuesta = new Respuesta();
        try {
            HttpSession session = request.getSession();
            Usuarios usuario = (Usuarios)session.getAttribute("usuario");
            int codigo = (usuario == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(usuario);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }
    
    public Respuesta cerrarSesion(HttpServletRequest request){
        Respuesta respuesta = new Respuesta();
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Sesión cerrada éxitosamente");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al cerrar la sesión");
        }
        return respuesta;
    }
    public Respuesta cambiarEstado(int idUsuario){
        EntityManager em = null;        
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuarios = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuarios = dao.consultarUsuarioPorID(idUsuario);
            
            em.getTransaction().begin();            
            usuarios.setEstado(1);
            dao.editar(usuarios);
            em.getTransaction().commit();            
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());            
        } catch (Exception e) {
             em.getTransaction().rollback();
             respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
             respuesta.setMensaje(ECodigoRespuesta.ERROR.getDescripcion());
        }
        return respuesta;
    }
    
       public Usuarios seleccionarAsesor(List<Usuarios> lista){
        Usuarios usuario = new Usuarios();
        
        for (int i = 0; i < lista.size(); i++) {            
            usuario = lista.get(i);
        }
        return usuario;
    }
}
