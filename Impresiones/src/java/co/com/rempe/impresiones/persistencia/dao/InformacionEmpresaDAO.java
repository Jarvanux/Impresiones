/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Adjunto;
import co.com.rempe.impresiones.persistencia.entidades.InformacionEmpresa;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class InformacionEmpresaDAO extends GenericoDAO<InformacionEmpresa> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformacionEmpresaDAO(EntityManager em) {
        super(InformacionEmpresa.class);
        this.em = em;
    } 
}
