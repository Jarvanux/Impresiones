/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.dao;

import co.com.rempe.impresiones.persistencia.entidades.Adjunto;
import co.com.rempe.impresiones.persistencia.entidades.RelacionesPapel;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author hrey
 */
public class RelacionesPapelDAO extends GenericoDAO<RelacionesPapel> {

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelacionesPapelDAO(EntityManager em) {
        super(RelacionesPapel.class);
        this.em = em;
    }         
    
    public void eliminarRelacionesPorId(int idPapel){
        Query q = null;
        try {
            q = em.createNativeQuery("delete from relaciones_papel where tipo_papel = ?");
            q.setParameter(1, idPapel);
        } catch (Exception e) {
            System.err.println("Se ha producido un error al eliminar las relaciones.");
        }
    }
    
    
    public List<RelacionesPapel> listTiposImpresion(int idPapel){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from relaciones_papel where tipo_impresion > 0 and tipo_papel = ?",RelacionesPapel.class);
            q.setParameter(1, idPapel);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }        
    }
    
    public List<RelacionesPapel> listModosImpresion(int idPapel){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from relaciones_papel where modo_impresion > 0 and tipo_papel = ? ",RelacionesPapel.class);
            q.setParameter(1, idPapel);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }        
    }
    
    public List<RelacionesPapel> listTamanosImpresion(int idPapel){
        Query q = null;
        try {
            q = em.createNativeQuery("select * from relaciones_papel where tamano_papel > 0 and tipo_papel = ? ",RelacionesPapel.class);
            q.setParameter(1, idPapel);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }        
    }
            
            
}
