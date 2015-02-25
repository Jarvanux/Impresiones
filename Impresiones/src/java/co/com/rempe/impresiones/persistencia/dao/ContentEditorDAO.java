/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Adjunto;
import co.com.rempe.impresiones.persistencia.entidades.Contenteditor;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class ContentEditorDAO extends GenericoDAO<Contenteditor> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContentEditorDAO(EntityManager em) {
        super(Contenteditor.class);
        this.em = em;
    } 
}
