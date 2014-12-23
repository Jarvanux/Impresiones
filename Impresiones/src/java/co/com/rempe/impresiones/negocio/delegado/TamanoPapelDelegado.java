package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.TipoPapelDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoTamanoDAO;
import co.com.rempe.impresiones.persistencia.entidades.Tamano;
import co.com.rempe.impresiones.persistencia.entidades.TipoPapel;
import co.com.rempe.impresiones.persistencia.entidades.TipoTamano;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EntityManager;

/**
 *
 * @author jhonjaider1000
 */
public class TamanoPapelDelegado {
    private static TamanoPapelDelegado instancia = new TamanoPapelDelegado();

    private TamanoPapelDelegado(){
        
    }
    
    public static TamanoPapelDelegado getInstancia() {
        return instancia;
    }
    
    public Respuesta consultarTamanoPapel(){
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();            
            TipoTamanoDAO dao  = new TipoTamanoDAO(em);
            List<TipoTamano> listaTamanoPapel = dao.buscarTodos();
            int codigo = (listaTamanoPapel.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listaTamanoPapel);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TamanoPapelDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los tamaños de impresión");
            return respuesta;
        }
    }
}
