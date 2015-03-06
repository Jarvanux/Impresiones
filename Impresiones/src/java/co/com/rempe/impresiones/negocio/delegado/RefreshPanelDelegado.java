/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.ChatDAO;
import co.com.rempe.impresiones.persistencia.entidades.Chat;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class RefreshPanelDelegado {

    private static RefreshPanelDelegado instancia = new RefreshPanelDelegado();

    public static RefreshPanelDelegado getInstancia() {
        return instancia;
    }

    public RefreshPanelDelegado() {
    }        

    public static void setInstancia(RefreshPanelDelegado instancia) {
        RefreshPanelDelegado.instancia = instancia;
    }    

    public Respuesta refrescarPanel(int idUsuario,long idUsuarioContacto,int accion) {
//        System.out.println("Acción recibida adslfjlafa: "+accion);
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            List<Object> listaObject = new ArrayList<Object>();
            //Consulto los clientes conectados que debe antender el usuario operador.
            //Este también me devolverá el número de mensajes dejados en el buzón de tickes.
            listaObject.add(ChatDelegado.getInstancia().consultaClientes(idUsuario));
            List<Usuarios> listaUsuarios = ChatDelegado.getInstancia().consultaClientesList(idUsuario);                        
            //Ahora consultaremos todos los mensajes de cada cliente conectado.            
            //Inicialmente creo el objeto dao, despues una lista de tipo objetos
            //para almacenar todos las listas encontradas y almacenadas en la lista de tipo chat            
            //dentro del for.
            
            ChatDAO dao = new ChatDAO(em);
            List<Object> listaChats = new ArrayList<Object>();                        
            List<Chat> chatsUsuario = null;
            
            //Y con este fabuloso for vamos a guardar todas las consultas realizadas por cada usuario.
            for (int i = 0; i < listaUsuarios.size(); i++) {                
                chatsUsuario = dao.chatsConversacion(idUsuario, listaUsuarios.get(i).getIdUsuario(), 0);
                listaChats.add(chatsUsuario);
            }
            
            //finalmente agregamos a la lista Object que devolveremos la lista 
            //Object que contiene nuestros chats consultados.
            listaObject.add(listaChats);
            //Modificamos nuestra acción actual, escribiendo generalmente...
            Chat chat = new Chat();
            chat = dao.consultarUltimoChatUOrigen(idUsuario, idUsuarioContacto, 0);         
//            System.err.println("ID USUARIO COS¡: "+idUsuario);
//            System.err.println("ID USUARIO DOS: "+idUsuarioContacto);
            if(chat != null){
                chat.setAccion(accion);
                ChatDelegado.getInstancia().editarUnRegistro(chat);
            }else{
//                System.err.println("CHAT NULL");
            }
            //De esta manera conocemos nuestro estado actual.
            Usuarios user = new Usuarios();                       
            user = ChatDelegado.getInstancia().usuarioConectado(idUsuario);
            listaObject.add(user.getEstado());
            listaObject.add(user.getSesionAdminCerrada());
            
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(listaObject);
            respuesta.setMensaje("Se ha concretado la petición exitosamente!."+idUsuario);
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo concretar la petición realizada."+e.getMessage());
        }
        return respuesta;
    }
    
}
