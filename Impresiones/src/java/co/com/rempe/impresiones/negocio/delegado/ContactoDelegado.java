/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.ContactoDAO;
import co.com.rempe.impresiones.persistencia.entidades.Contacto;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 *
 * @author hrey
 */
public class ContactoDelegado {

    private static ContactoDelegado instancia = new ContactoDelegado();

    private ContactoDelegado() {
    }

    public static ContactoDelegado getInstancia() {
        return instancia;
    }

    //Terminado 12/09/2014.
    public Respuesta insertarMensaje(Contacto contacto) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ContactoDAO contactoDAO = new ContactoDAO(em);
            contactoDAO.crear(contacto);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Su mensaje fue enviado");
        } catch (Exception ex) {
            Logger.getLogger(ContactoDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al enviar el mensaje");
        }
        return respuesta;
    }

    
    public Respuesta modificarMensaje(Contacto contacto) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ContactoDAO contactoDAO = new ContactoDAO(em);
            contactoDAO.editar(contacto);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se modificó el mensaje correctamente");
        } catch (Exception ex) {
            Logger.getLogger(ContactoDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al modificar el mensaje");
        }
        return respuesta;
    }

    public Respuesta consultarMensajesNoLeidos(int inicio, int fin) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ContactoDAO contactoDAO = new ContactoDAO(em);
            List<Contacto> lista = contactoDAO.buscarTodos();
            int codigo = (lista.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(lista);
            respuesta.setMensaje("Consulta correctamente");
        } catch (Exception ex) {
            Logger.getLogger(ContactoDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los mensajes");
        } 
        return respuesta;
    }
    public Respuesta consultarPorID(int idContacto) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ContactoDAO contactoDAO = new ContactoDAO(em);
            Contacto contacto = contactoDAO.consultarPorID(idContacto);
            int codigo = (contacto == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(contacto);
            respuesta.setMensaje("Consulta correctamente");
        } catch (Exception ex) {
            Logger.getLogger(ContactoDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los mensajes");
        } 
        return respuesta;
    }
}
