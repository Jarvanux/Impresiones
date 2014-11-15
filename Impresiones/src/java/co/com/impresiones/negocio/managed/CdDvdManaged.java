package co.com.impresiones.negocio.managed;

import co.com.impresiones.persistencia.dao.PedidoFacadeLocal;
import co.com.impresiones.persistencia.entidades.Pedido;
import java.io.File;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author JuanCamilo
 */
@ManagedBean
@RequestScoped
public class CdDvdManaged {

    
    public CdDvdManaged() {
    }
    
    private String tipoDisco;
    private String tipoServicio;
    private int numCopias;
    private int totalImpresiones;
    private String tipoGrabacion;
    private String tipoEmpaque;
    private File diseño;
    private String linkDiseño;
    private File archivoGrabacion;
    private String linkGrabacion;
    
    @EJB
    private PedidoFacadeLocal pedidoFLocal;
    
    public void guardarPedido() {
        Pedido pedido = null;
        try {
            pedido = new Pedido();
            
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

    public File getDiseño() {
        return diseño;
    }

    public void setDiseño(File diseño) {
        this.diseño = diseño;
    }

    public String getLinkDiseño() {
        return linkDiseño;
    }

    public void setLinkDiseño(String linkDiseño) {
        this.linkDiseño = linkDiseño;
    }

    public File getArchivoGrabacion() {
        return archivoGrabacion;
    }

    public void setArchivoGrabacion(File archivoGrabacion) {
        this.archivoGrabacion = archivoGrabacion;
    }

    public String getLinkGrabacion() {
        return linkGrabacion;
    }

    public void setLinkGrabacion(String linkGrabacion) {
        this.linkGrabacion = linkGrabacion;
    }
    
    
}
