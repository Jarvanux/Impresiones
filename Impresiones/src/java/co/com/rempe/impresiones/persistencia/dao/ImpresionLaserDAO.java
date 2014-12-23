/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.ImpresionLaser;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class ImpresionLaserDAO extends GenericoDAO<ImpresionLaser>{
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ImpresionLaserDAO(EntityManager em){
        super(ImpresionLaser.class);
        this.em = em;
    }    
}
