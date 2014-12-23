/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Usuario;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class UsuarioDAO extends GenericoDAO<Usuario> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDAO(EntityManager em) {
        super(Usuario.class);
        this.em = em;
    }
}
