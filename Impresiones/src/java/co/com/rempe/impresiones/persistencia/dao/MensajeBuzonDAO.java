/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Adjunto;
import co.com.rempe.impresiones.persistencia.entidades.MenajesBuzon;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class MensajeBuzonDAO extends GenericoDAO<MenajesBuzon> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeBuzonDAO(EntityManager em) {
        super(MenajesBuzon.class);
        this.em = em;
    }


    public MenajesBuzon consultaEstadoLeidoSMS(int idSMS) {
        Query q = null;
        try {
            q = em.createNativeQuery("select * from menajes_buzon where id_mensaje = " + idSMS, MenajesBuzon.class);
            return (MenajesBuzon) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("Se ha producido un error.");
            return null;
        }
    }

    public List<MenajesBuzon> mensajeBuzon(String fecha1, String fecha2, String desde, int codigoEstado) {
        Query q = null;
        try {
            String consulta = "";
            if (fecha1.length() > 0) {
                if (codigoEstado < 0) {
                    consulta = "select * from menajes_buzon where fecha between ? and ? order by fecha desc";
                    q = em.createNativeQuery(consulta, MenajesBuzon.class);
                    q.setParameter(1, fecha1);
                    q.setParameter(2, fecha2);
                } else {
                    consulta = "select * from menajes_buzon where fecha between ? and ? and leido = " + codigoEstado +" order by fecha desc";
                    q = em.createNativeQuery(consulta, MenajesBuzon.class);
                    q.setParameter(1, fecha1);
                    q.setParameter(2, fecha2);
                }
            } else if (desde.length() > 0) {
                if (codigoEstado < 0) {
                    consulta = "select * from menajes_buzon where fecha >= ? order by fecha desc";
                    q = em.createNativeQuery(consulta, MenajesBuzon.class);
                    q.setParameter(1, desde);
                } else {
                    consulta = "select * from menajes_buzon where fecha >= ? and leido = " + codigoEstado+ " order by fecha desc";
                    q = em.createNativeQuery(consulta, MenajesBuzon.class);
                    q.setParameter(1, desde);
                }
            } else {
                if (codigoEstado < 0) {
                    consulta = "select * from menajes_buzon order by fecha desc";
                    q = em.createNativeQuery(consulta, MenajesBuzon.class);
                }else{
                    consulta = "select * from menajes_buzon where leido = "+codigoEstado + " order by fecha desc";
                    q = em.createNativeQuery(consulta, MenajesBuzon.class);
                }
            }
            return q.getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
