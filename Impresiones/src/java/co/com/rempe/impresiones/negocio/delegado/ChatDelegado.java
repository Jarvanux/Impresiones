package co.com.rempe.impresiones.negocio.delegado;

import co.com.progredi.impresiones.persistencia.entidades.Chat;
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.BuscarEmoticones;
import co.com.rempe.impresiones.persistencia.dao.ChatDAO;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.ArrayList;
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
            respuesta.setDatos(chat.getIdConversacion());
            respuesta.setMensaje("Chat enviado.");
        } catch (Exception ex) {
            Logger.getLogger(ChatDelegado.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error enviando chat.");
        }
        return respuesta;
    }

    public Respuesta editarUnRegistro(Chat chat) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            ChatDAO chatDAO = new ChatDAO(em);            
            chatDAO.editar(chat);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(chat.getIdConversacion());
            respuesta.setMensaje("Registro editado");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al editar el registro");
        }
        return respuesta;
    }

    public Respuesta leerChat(int accion, int idUsuario, int idUsuarioContacto, long idConversacion) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            Chat chat = new Chat();
            ChatDAO dao = new ChatDAO(em);
            int codigo = 0;
            List<Object> listObjetc = new ArrayList<Object>();
            List<Chat> listaMensajes = dao.chatsConversacion(idUsuario, idUsuarioContacto, idConversacion);
            listObjetc.add(listaMensajes);

            codigo = (listaMensajes.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();

            //Inicialmente consultamos el último chat enviado por el usuario emisor.
            //con el fin de editar ese registro para evaluar nuestro estado 
            //es decir que el otro usuario pueda ver si estamos leyendo, o escribiendo etc.
            chat = dao.consultarUltimoChatUOrigen(idUsuario, idConversacion);
            if (chat != null) {
                chat.setAccion(accion);
                 editarUnRegistro(chat);                                 
            }

            chat = dao.consultarUltimoChatUOrigen(idUsuarioContacto, idConversacion);
            int estadoUsuarioDestino = 0;
            System.out.println("SDFJLAFJL");
            if (chat != null) {
                estadoUsuarioDestino = chat.getAccion();
                System.out.println("El está: "+estadoUsuarioDestino);
            }
            listObjetc.add(estadoUsuarioDestino);
                       
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listObjetc);
            respuesta.setMensaje("Chats consultados..");            
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar mensajes.");            
        }
       
        return respuesta;        
    }
}
