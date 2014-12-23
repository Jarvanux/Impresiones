/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.delegado;

import co.com.rempe.impresiones.negocio.constantes.ECodigoRespuesta;
import co.com.rempe.impresiones.negocio.respuesta.Respuesta;
import co.com.rempe.impresiones.persistencia.dao.TipoImpresionDAO;
import co.com.rempe.impresiones.persistencia.entidades.TipoImpresion;
import co.com.rempe.impresiones.persistencia.entidades.conexion.BDConexion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author hrey
 */
public class TipoImpresionDelegado {

    private static TipoImpresionDelegado instancia = new TipoImpresionDelegado();

    private TipoImpresionDelegado() {
    }

    public static TipoImpresionDelegado getInstancia() {
        return instancia;
    }

    public Respuesta consultarTiposImpresion() {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            TipoImpresionDAO dao = new TipoImpresionDAO(em);
            List<TipoImpresion> listaTiposImpresion = dao.buscarTodos();
            int codigo = (listaTiposImpresion.isEmpty()) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(listaTiposImpresion);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TipoImpresionDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consultar los tipos de impresión");
        }
        return respuesta;
    }

    public Respuesta consultarTipoImprsionPorID(Integer id) {
        EntityManager em;
        Respuesta respuesta = new Respuesta();
        try {
            em = BDConexion.getEntityManager();
            TipoImpresionDAO dao = new TipoImpresionDAO(em);
            TipoImpresion tipo = dao.consultarTipoPorID(id);
            int codigo = (tipo == null) ? ECodigoRespuesta.VACIO.getCodigo() : ECodigoRespuesta.CORRECTO.getCodigo();
            respuesta.setCodigo(codigo);
            respuesta.setDatos(tipo);
            respuesta.setMensaje(ECodigoRespuesta.CORRECTO.getDescripcion());
            return respuesta;
        } catch (Exception ex) {
            Logger.getLogger(TipoImpresionDelegado.class.getName()).log(Level.SEVERE, null, ex);
            respuesta.setCodigo(ECodigoRespuesta.ERROR.getCodigo());
            respuesta.setMensaje("Error al consutar tipo de impresión por id");
            return respuesta;
        }
    }
}
