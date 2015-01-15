package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.persistencia.entidades.Chat;
import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.negocio.utilerias.BuscarEmoticones;
import co.com.rempe.impresiones.persistencia.dao.ChatDAO;
import co.com.rempe.impresiones.persistencia.dao.MensajeBuzonDAO;
import co.com.rempe.impresiones.persistencia.dao.UsuariosDAO;
import co.com.rempe.impresiones.persistencia.entidades.MenajesBuzon;
import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
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
            respuesta.setDatos(new Date());
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

    public Respuesta leerChat(int accion, long idUsuario, long idUsuarioContacto, long idConversacion) {
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
            chat = dao.consultarUltimoChatUOrigen(idUsuario, idUsuarioContacto, idConversacion);
            if (chat != null) {
                chat.setAccion(accion);
                editarUnRegistro(chat);
            }

            
            //Esto es solo un pequeño código sin sentido, sucede q se estaban realizando dos peticiones que
            //pude modificar en la vista solo con los datos llevados desde aquí,
            //para no consumir recursos, lo único en lo que no me centre fue en dejar de pasar 
            //estos dos siguientes datos a la lista Objeto para no tener problemas con lo que tengo en la vista
            //y perder tiempo actualizando, talvez se hará en otra versión del código.
            int estadoUsuarioDestino = 0;                        
            listObjetc.add(estadoUsuarioDestino);
            listObjetc.add(estadoUsuarioDestino);
            
            //Consulta el estado de nuestro usuario contacto con quien estamos chateando.
            Usuarios user = new Usuarios();
            user = usuarioConectado(idUsuarioContacto);
            listObjetc.add(user.getConectado());
            listObjetc.add(user.getEstado());
            
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listObjetc);            
            respuesta.setMensaje("Chats consultados..");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar mensajes.");
        }
        return respuesta;
    }

    public Usuarios usuarioConectado(long idUsuario) {
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            Usuarios usuario = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            usuario = dao.consultarUsuarioPorID(idUsuario);                        
            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    public Respuesta consultaClientes(int idAdministrador) {
        EntityManager em = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            int codigo = 0;
            Usuarios chat = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            List<Usuarios> listUsuarios = dao.consultaClientes(idAdministrador);
            codigo = (listUsuarios.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            List<Object> list = new ArrayList<Object>();
            list.add(listUsuarios);
            Integer num = Integer.parseInt(numMensajesBuzon().getDatos().toString());
            list.add(num);
            respuesta.setCodigo(codigo);
            respuesta.setDatos(list);
            respuesta.setMensaje("Se han consultado satisfactoriaente los clientes para el aministrador.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al consultar los clientes para el administrador.");
        }
        return respuesta;
    }

    public List<Usuarios> consultaClientesList(int idAdministrador) {
        EntityManager em = null;
        List<Usuarios> listUsuarios = null;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            int codigo = 0;
            Usuarios chat = new Usuarios();
            UsuariosDAO dao = new UsuariosDAO(em);
            listUsuarios = dao.consultaClientes(idAdministrador);                                                                                                
        } catch (Exception e) {            
        }
        return listUsuarios;
    }

    public Respuesta numMensajesBuzon() {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            ChatDAO dao = new ChatDAO(em);
            int numMensajes = dao.numMensajesBuzon();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setDatos(numMensajes);
            respuesta.setMensaje("Número de mensajes de buzón consultados exitosamente!.");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo consultar el número de mensajes de buzón");
        }
        return respuesta;
    }

    public Respuesta listaMensajesBuzon(int codigoLeido, String fecha1, String fecha2, String desde) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            MenajesBuzon msbzn = new MenajesBuzon();
            MensajeBuzonDAO dao = new MensajeBuzonDAO(em);
            int codigo = 0;
            List<MenajesBuzon> lista = new ArrayList<MenajesBuzon>();
            lista = dao.mensajeBuzon(fecha1, fecha2, desde, codigoLeido);
            codigo = (lista.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(lista);
            respuesta.setMensaje("Se han consultado los mensajes de buzón exitosamente!. " + fecha1 + " " + fecha2);
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Se ha producido un error al consultar los mensajes de buzón.");
        }
        return respuesta;
    }

    public Respuesta mensajeBuzon(String nombre, String correo, String mensaje) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            MenajesBuzon sms = new MenajesBuzon();
            MensajeBuzonDAO dao = new MensajeBuzonDAO(em);
            if (nombre.contains(" ")) {
                String[] partesNombre = nombre.split(" ");
                if (partesNombre.length == 2) {
                    sms.setNombre(partesNombre[0]);
                    sms.setApellido(partesNombre[1]);
                } else if (partesNombre.length == 3) {
                    sms.setNombre(partesNombre[0] + ' ' + partesNombre[1]);
                    sms.setApellido(partesNombre[2]);
                } else if (partesNombre.length == 4) {
                    sms.setNombre(partesNombre[0] + ' ' + partesNombre[1]);
                    sms.setApellido(partesNombre[3] + ' ' + partesNombre[2]);
                } else if (partesNombre.length > 4) {
                    sms.setNombre(partesNombre[0] + ' ' + partesNombre[1]);
                    String string = "";
                    for (int i = 1; i < partesNombre.length; i++) {
                        string += partesNombre[1];
                    }
                    sms.setApellido(string);
                }
            } else {
                sms.setNombre(nombre);
            }
            sms.setMensaje(mensaje);
            sms.setCorreo(correo);
            sms.setLeido(0);
            sms.setFecha(new Date());
            dao.crear(sms);//Insertamos el mensaje.
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Mensaje enviado!");
        } catch (Exception e) {
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Mensaje no enviado.");
        }
        return respuesta;
    }

    public Respuesta actualizaMensajeBuzaon(int idSMS, int idUsuario) {
        Respuesta respuesta = new Respuesta();
        EntityManager em = null;
        try {
            em = BDConexion.getEntityManager();
            em.getTransaction().begin();
            MensajeBuzonDAO dao = new MensajeBuzonDAO(em);
            MenajesBuzon sms = dao.consultaEstadoLeidoSMS(idSMS); //He cambiado la estructura en este caso va a consultarlo.
            sms.setLeido(1);
            sms.setUsuarioLector("" + idUsuario);
            dao.editar(sms);
            em.getTransaction().commit();
            respuesta.setCodigo(ECodigoRespuesta.CORRECTO.getCodigo());
            respuesta.setMensaje("Se ha actualizado el estado(leido) para el mensaje correspondiente " + idSMS);
        } catch (Exception e) {
            em.getTransaction().rollback();
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("No se pudo actualizar el estado(leido) para el mensaje correspondiente");
        }
        return respuesta;
    }
}
