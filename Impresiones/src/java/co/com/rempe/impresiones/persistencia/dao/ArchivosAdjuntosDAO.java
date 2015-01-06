/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Adjunto;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class ArchivosAdjuntosDAO extends GenericoDAO<Adjunto> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArchivosAdjuntosDAO(EntityManager em) {
        super(Adjunto.class);
        this.em = em;
    } 
}
