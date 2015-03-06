/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class UsuariosDAO extends GenericoDAO<Usuarios> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosDAO(EntityManager em) {
        super(Usuarios.class);
        this.em = em;
    }

    public Usuarios iniciarSesion(String usuario, String correo, String clave) {
        Query q = null;
        try {
            System.out.println("Usuario: " + usuario + " Clave: " + clave);
            q = em.createNativeQuery("select * from usuarios u inner join informacion_empresa ie where (u.apodo = ? or u.email = ?) and (u.contrasena = ? or ie.clave_general = ?)", Usuarios.class);
            q.setParameter(1, usuario);
            q.setParameter(2, correo);
            q.setParameter(3, clave);
            q.setParameter(4, clave);
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuarios> soporteConectado() {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where id_rol = 1 and estado = 1", Usuarios.class);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuarios buscarSoporte() {
        Query q = null;
        try {
            q = em.createNativeQuery("select U.id_usuario,U.apodo,U.nombres,"
                    + "U.apellidos,U.email,U.cedula,U.celular,U.telefono,"
                    + "U.ciudad,U.zona,U.id_rol,U.estado,U.direccion,"
                    + "U.fecha_nacimiento,U.conectado,count(*) as num_chats "
                    + "from usuarios U \n"
                    + "inner join chat C on U.id_usuario = C.id_usuario_origen\n"
                    + "where C.id_rol = 1 and U.conectado = 1 group by "
                    + "C.id_usuario_origen order by num_chats asc limit 1",
                    Usuarios.class);

            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuarios buscarSoporteRecurso2() {
        System.out.println("Se ha accedido al recurso 2");
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where conectado = 1 and id_rol = 1 limit 1", Usuarios.class);
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuarios consultarUsuarioPorID(long idUsuario) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where id_usuario = ?", Usuarios.class);
            q.setParameter(1, idUsuario);
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Se ha producido un error en la consulta dao por id de usuario.");
            return null;
        }
    }

    public Usuarios consultarUsuarioPorID(int idUsuario) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where id_usuario = ?", Usuarios.class);
            q.setParameter(1, idUsuario);
            return (Usuarios) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Se ha producido un error en la consulta dao por id de usuario.");
            return null;
        }
    }

    public List<Usuarios> listarUsuariosPorROL(int rol) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where id_rol = ?", Usuarios.class);
            q.setParameter(1, rol);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuarios> filtrarUsuarios(int rol, String palabra) {
        Query q = null;
        try {
            String filtro = "'%" + palabra + "%'";
            String query = " select * from usuarios where id_rol = ? and (nombres like " + filtro + " or apellidos like " + filtro + " or email like " + filtro + " or cedula like " + filtro + " or celular like  " + filtro + ")";
            System.out.println("ID: " + rol + query);
            q = em.createNativeQuery(query, Usuarios.class);
            q.setParameter(1, rol);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuarios> consultaClientes(long idAdministrador) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios u inner join chat c on u.id_usuario = c.id_usuario_origen where c.id_usuario_destino = ? and u.conectado = 1 group by c.id_usuario_origen order by ultima_conexion desc", Usuarios.class);
            q.setParameter(1, idAdministrador);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
