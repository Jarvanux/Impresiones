/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Contacto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class ContactoDAO extends GenericoDAO<Contacto> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactoDAO(EntityManager em) {
        super(Contacto.class);
        this.em = em;
    }

    public List<Contacto> consultarMensajes(int inicio, int fin, boolean leido) throws Exception {
        fin = (fin - inicio);
        Query q = em.createNativeQuery("select c.* from contacto c where leido=? limit ?,? ");
        q.setParameter(1, leido);
        q.setParameter(2, inicio);
        q.setParameter(3, fin);
        return q.getResultList();
    }

    public Contacto consultarPorID(int idContacto) {
        Query q = null;
        try {
            q = em.createNativeQuery("select c.* from contacto c where id_contacto = ?", Contacto.class);
            q.setParameter(1, idContacto);
            return (Contacto) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Se ha producido un error.");
            return null;
        }
    }
}
