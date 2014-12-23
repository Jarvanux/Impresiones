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
    
    public Usuarios iniciarSesion(String usuario, String correo, String clave){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where (apodo = ? or email = ?) and contrasena = ?",Usuarios.class);
            q.setParameter(1, usuario);
            q.setParameter(2, correo);
            q.setParameter(3, clave);
            return (Usuarios)q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Usuarios> soporteConectado(){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where id_rol = 1 and estado = 1",Usuarios.class);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Usuarios consultarUsuarioPorID(int idUsuario){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from usuarios where id_usuario = ?",Usuarios.class);
            q.setParameter(1, idUsuario);
            return (Usuarios)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Se ha producido un error en la consulta dao por id de usuario.");
            return null;
        }
    }
}
