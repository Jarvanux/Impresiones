/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.progredi.impresiones.persistencia.entidades.Chat;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class ChatDAO extends GenericoDAO<Chat> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChatDAO(EntityManager em) {
        super(Chat.class);
        this.em = em;
    }

    public Chat consultarUltimoChatUOrigen(long idUsuario, long idConversacion) {
        Query q = null;
        System.out.println("Id usuairo recibido: " + idUsuario);
        try {
            q = em.createNativeQuery("select * from chat where id_usuario_origen = ? order by fecha desc limit 1", Chat.class);
            //Falta agregarle a la conversación un ID único.
            q.setParameter(1, idUsuario);
            System.out.println("Consulta para acción realizada.");
            return (Chat) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Chat consultarUltimoChatUDestino(long idUsuario, long idConversacion) {
        Query q = null;
        System.out.println("Id usuairo recibido: " + idUsuario);
        try {
            q = em.createNativeQuery("select * from chat where id_usuario_destino = ? order by fecha desc limit 1", Chat.class);
            //Falta agregarle a la conversación un ID único.
            q.setParameter(1, idUsuario);
            System.out.println("Consulta para acción realizada.");
            Chat x = (Chat) q.getSingleResult();
            return (Chat) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Chat> chatsConversacion(long idUsuarioOrigen, long idUsuarioDestino, long idConversacion) {
        Query q = null;
        try {
            //Estoy usando un código de conversación, pero para hacer mucho más segura la consulta, le paso
            //el id de los usuarios que intervienen en la misma, el código de conversación
            //es generado por el método System.currenMillisecounds de nuestro hermoso lenguaje :D.
            System.out.println("Id usuario Origen: " + idUsuarioOrigen);
            System.out.println("Id usuario Destino: " + idUsuarioDestino);
            q = em.createNativeQuery("select * from chat where (id_usuario_origen = ?"
                    + "  and id_usuario_destino = ?)"
                    + "or (id_usuario_origen = ? and id_usuario_destino = ?) order by fecha asc", Chat.class);
            q.setParameter(1, idUsuarioOrigen);
            q.setParameter(2, idUsuarioDestino);
            q.setParameter(3, idUsuarioDestino);
            q.setParameter(4, idUsuarioOrigen);
            System.out.println("Chats conversación consultada");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuarios consultaClientes(int idAdministrador) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios u inner join chat c on u.id_usuario =  c.id_usuario_origen where id_usuario = ? group by id_usuario_destino");
            q.setParameter(1, idAdministrador);
            return (Usuarios)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
