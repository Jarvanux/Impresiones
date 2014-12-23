/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.TipoImpresionDAO;
import co.com.rempe.impresiones.persistencia.dao.TipoPapelDAO;
import co.com.rempe.impresiones.persistencia.entidades.TipoImpresion;
import co.com.rempe.impresiones.persistencia.entidades.TipoPapel;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class TipoPapelDelegado {

    private static TipoPapelDelegado instancia = new TipoPapelDelegado();

    private TipoPapelDelegado() {
    }

    public static TipoPapelDelegado getInstancia() {
        return instancia;
    }

    public Respuesta consultarTipoPapel() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            TipoPapelDAO dao = new TipoPapelDAO(em);
            List<TipoPapel> listaTiposPapel = dao.buscarTodos();
            int codigo = (listaTiposPapel.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listaTiposPapel);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TipoImpresionDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los tipos de papel");
        }
        return respuesta;
    }
}
