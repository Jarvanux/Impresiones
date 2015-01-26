/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.ImpresionLaser;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author jhonjaider1000
 */
public class ImpresionLaserDAO extends GenericoDAO<ImpresionLaser> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpresionLaserDAO(EntityManager em) {
        super(ImpresionLaser.class);
        this.em = em;
    }

    /**
     *
     * @param idUsuario
     * @return Lista de impresiones laser realizadas por un usuario;
     */
    public List<ImpresionLaser> listarImpresionesLaser(long idUsuario, int tipoImpresion) {
        Query q = null;
        try {
            if (tipoImpresion == 1) {

            } else if (tipoImpresion == 2) {

            } else if (tipoImpresion == 3) {

            } else if (tipoImpresion == 4) {

            } else if (tipoImpresion == 5) {

            }
            q = em.createNativeQuery("select * from impresion_laser  where id_usuario = ? order by guia_impresion desc;", ImpresionLaser.class);
            q.setParameter(1, idUsuario);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ImpresionLaser> filtrarImpresionesLaser(long idUsuario, String filtro) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from impresion_laser  where id_usuario = ? and guia_impresion like '%" + filtro + "%' order by guia_impresion desc", ImpresionLaser.class);
            q.setParameter(1, idUsuario);
//            q.setParameter(2, filtro);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public ImpresionLaser ultimaImpresionUsuario(int idUsuario) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from impresion_laser where id_usuario = ? order by fecha desc limit 1", ImpresionLaser.class);
            q.setParameter(1, idUsuario);
            return (ImpresionLaser) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
