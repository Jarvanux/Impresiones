package co.com.rempe.impresiones.negocio.delegado;

import co.com.progredi.impresiones.persistencia.entidades.Chat;
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.BuscarEmoticones;
import co.com.rempe.impresiones.persistencia.dao.ChatDAO;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
//Creado el 12/09/2014.
public class ChatDelegado {

    private static ChatDelegado instancia = new ChatDelegado();

    public ChatDelegado() {
    }

    public static ChatDelegado getInstancia() {
        return instancia;
    }

    public Respuesta insertarChat(Chat chat) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();

        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ChatDAO chatDAO = new ChatDAO(em);
            chat.setFecha(new Date());
            chatDAO.crear(chat);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Chat enviado.");
        } catch (Exception ex) {
            Logger.getLogger(ChatDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error enviando chat.");
        } finally {
            
        }
        return respuesta;
    }
    
    public Respuesta leerChat(){
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            ChatDAO dao = new ChatDAO(em);
            List<Chat> listaChat = dao.buscarTodos();
            for (int i = 0; i < listaChat.size(); i++) {                
                listaChat.get(i).setMensaje(BuscarEmoticones.buscarEmoticones(listaChat.get(i).getMensaje()));
            }
            int codigo = (listaChat.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listaChat);
            respuesta.setMensaje("Datos consultado exitosamente!.");
            return respuesta;
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar mensajes.");
            return respuesta;
        }
    }
}
