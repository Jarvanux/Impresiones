package co.com.impresiones.negocio.managed;

import co.com.impresiones.negocio.utilidades.Utilidades;
import co.com.impresiones.persistencia.dao.PedidoFacadeLocal;
import co.com.impresiones.persistencia.entidades.Adjunto;
import co.com.impresiones.persistencia.entidades.DetallePedido;
import co.com.impresiones.persistencia.entidades.EstadoPedido;
import co.com.impresiones.persistencia.entidades.Pedido;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author JuanCamilo
 */
@ManagedBean
@RequestScoped
public class CdDvdManaged implements Serializable {

    
    public CdDvdManaged() {
    }
    
    private String tipoDisco;
    private String tipoServicio;
    private int numCopias;
    private int totalImpresiones;
    private String tipoGrabacion;
    private String tipoEmpaque;
    private String detalles;
    private String diseñoCd;
    private String linkDiseñoCd;
    private String archivoGrabacionCd;
    private String linkGrabacionCd;
    private String observaciones;
    
    @EJB
    private PedidoFacadeLocal pedidoFLocal;
    
    Utilidades utilidades = new Utilidades();
    
    public void guardarPedido() {
        Pedido pedido = null;
        Adjunto adjunto = null;
        DetallePedido detallePedido = null;
        EstadoPedido estadoPedido = null;
        
        try {
            pedido = new Pedido();
            adjunto = new Adjunto();
            detallePedido = new DetallePedido();
            estadoPedido = new EstadoPedido();
            
            pedido.setNumero(utilidades.getCadenaAlfanumAleatoria(numCopias));
            
            //ADJUNTO ES EL ARCHIVO O LINK
            //Ej: Cd-Impresion o DVD-Grabar
            adjunto.setNombre(tipoDisco+"-"+tipoServicio);
            //Ruta del archivo
            adjunto.setNombreArchivo(diseñoCd+linkDiseñoCd+archivoGrabacionCd+linkGrabacionCd);
            //Nota
            adjunto.setObservaciones(observaciones);
            adjunto.setHojasColor(null);
            adjunto.setHojasNegro(null);
            //Número de copias o impresiones de diseño
            adjunto.setNumeroHojas(numCopias);
            adjunto.setNumeroHojasColor(null);
            adjunto.setNumeroHojasNegro(null);
            adjunto.setIdPedido(pedido);
            
        } catch (Exception e) {
        }
    }
    

    public String getTipoDisco() {
        return tipoDisco;
    }

    public void setTipoDisco(String tipoDisco) {
        this.tipoDisco = tipoDisco;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getNumCopias() {
        return numCopias;
    }

    public void setNumCopias(int numCopias) {
        this.numCopias = numCopias;
    }

    public int getTotalImpresiones() {
        return totalImpresiones;
    }

    public void setTotalImpresiones(int totalImpresiones) {
        this.totalImpresiones = totalImpresiones;
    }

    public String getTipoGrabacion() {
        return tipoGrabacion;
    }

    public void setTipoGrabacion(String tipoGrabacion) {
        this.tipoGrabacion = tipoGrabacion;
    }

    public String getTipoEmpaque() {
        return tipoEmpaque;
    }

    public void setTipoEmpaque(String tipoEmpaque) {
        this.tipoEmpaque = tipoEmpaque;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getDiseñoCd() {
        return diseñoCd;
    }

    public void setDiseñoCd(String diseñoCd) {
        this.diseñoCd = diseñoCd;
    }

    public String getLinkDiseñoCd() {
        return linkDiseñoCd;
    }

    public void setLinkDiseñoCd(String linkDiseñoCd) {
        this.linkDiseñoCd = linkDiseñoCd;
    }

    public String getArchivoGrabacionCd() {
        return archivoGrabacionCd;
    }

    public void setArchivoGrabacionCd(String archivoGrabacionCd) {
        this.archivoGrabacionCd = archivoGrabacionCd;
    }

    public String getLinkGrabacionCd() {
        return linkGrabacionCd;
    }

    public void setLinkGrabacionCd(String linkGrabacionCd) {
        this.linkGrabacionCd = linkGrabacionCd;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
