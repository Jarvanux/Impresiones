/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hrey
 */
@Entity
@Table(name = "impresion_tamano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImpresionTamano.findAll", query = "SELECT i FROM ImpresionTamano i"),
    @NamedQuery(name = "ImpresionTamano.findByIdTipoTamano", query = "SELECT i FROM ImpresionTamano i WHERE i.impresionTamanoPK.idTipoTamano = :idTipoTamano"),
    @NamedQuery(name = "ImpresionTamano.findByIdTipoImpresion", query = "SELECT i FROM ImpresionTamano i WHERE i.impresionTamanoPK.idTipoImpresion = :idTipoImpresion"),
    @NamedQuery(name = "ImpresionTamano.findByCosto", query = "SELECT i FROM ImpresionTamano i WHERE i.costo = :costo")})
public class ImpresionTamano implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ImpresionTamanoPK impresionTamanoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "costo")
    private BigDecimal costo;
    @JoinColumn(name = "id_tipo_impresion", referencedColumnName = "id_tipo_impresion", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoImpresion tipoImpresion;
    @JoinColumn(name = "id_tipo_tamano", referencedColumnName = "id_tipo_tamano", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoTamano tipoTamano;

    public ImpresionTamano() {
    }

    public ImpresionTamano(ImpresionTamanoPK impresionTamanoPK) {
        this.impresionTamanoPK = impresionTamanoPK;
    }

    public ImpresionTamano(ImpresionTamanoPK impresionTamanoPK, BigDecimal costo) {
        this.impresionTamanoPK = impresionTamanoPK;
        this.costo = costo;
    }

    public ImpresionTamano(int idTipoTamano, int idTipoImpresion) {
        this.impresionTamanoPK = new ImpresionTamanoPK(idTipoTamano, idTipoImpresion);
    }

    public ImpresionTamanoPK getImpresionTamanoPK() {
        return impresionTamanoPK;
    }

    public void setImpresionTamanoPK(ImpresionTamanoPK impresionTamanoPK) {
        this.impresionTamanoPK = impresionTamanoPK;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public TipoImpresion getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(TipoImpresion tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    public TipoTamano getTipoTamano() {
        return tipoTamano;
    }

    public void setTipoTamano(TipoTamano tipoTamano) {
        this.tipoTamano = tipoTamano;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (impresionTamanoPK != null ? impresionTamanoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImpresionTamano)) {
            return false;
        }
        ImpresionTamano other = (ImpresionTamano) object;
        if ((this.impresionTamanoPK == null && other.impresionTamanoPK != null) || (this.impresionTamanoPK != null && !this.impresionTamanoPK.equals(other.impresionTamanoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.progredi.impresiones.persistencia.entidades.ImpresionTamano[ impresionTamanoPK=" + impresionTamanoPK + " ]";
    }
    
}
