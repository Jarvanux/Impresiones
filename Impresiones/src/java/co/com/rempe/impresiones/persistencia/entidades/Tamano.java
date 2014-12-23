/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "tamano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tamano.findAll", query = "SELECT t FROM Tamano t"),
    @NamedQuery(name = "Tamano.findByIdTamano", query = "SELECT t FROM Tamano t WHERE t.tamanoPK.idTamano = :idTamano"),
    @NamedQuery(name = "Tamano.findByNumeroHojas", query = "SELECT t FROM Tamano t WHERE t.numeroHojas = :numeroHojas"),
    @NamedQuery(name = "Tamano.findByPrecioHoja", query = "SELECT t FROM Tamano t WHERE t.precioHoja = :precioHoja"),
    @NamedQuery(name = "Tamano.findByIdTipoPapel", query = "SELECT t FROM Tamano t WHERE t.tamanoPK.idTipoPapel = :idTipoPapel"),
    @NamedQuery(name = "Tamano.findByIdTipoTamano", query = "SELECT t FROM Tamano t WHERE t.tamanoPK.idTipoTamano = :idTipoTamano")})
public class Tamano implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TamanoPK tamanoPK;
    @Column(name = "numero_hojas")
    private Integer numeroHojas;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_hoja")
    private BigDecimal precioHoja;
    @JoinColumn(name = "id_tipo_tamano", referencedColumnName = "id_tipo_tamano", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoTamano tipoTamano;
    @JoinColumn(name = "id_tipo_papel", referencedColumnName = "id_tipo_papel", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoPapel tipoPapel;

    public Tamano() {
    }

    public Tamano(TamanoPK tamanoPK) {
        this.tamanoPK = tamanoPK;
    }

    public Tamano(int idTamano, int idTipoPapel, int idTipoTamano) {
        this.tamanoPK = new TamanoPK(idTamano, idTipoPapel, idTipoTamano);
    }

    public TamanoPK getTamanoPK() {
        return tamanoPK;
    }

    public void setTamanoPK(TamanoPK tamanoPK) {
        this.tamanoPK = tamanoPK;
    }

    public Integer getNumeroHojas() {
        return numeroHojas;
    }

    public void setNumeroHojas(Integer numeroHojas) {
        this.numeroHojas = numeroHojas;
    }

    public BigDecimal getPrecioHoja() {
        return precioHoja;
    }

    public void setPrecioHoja(BigDecimal precioHoja) {
        this.precioHoja = precioHoja;
    }

    public TipoTamano getTipoTamano() {
        return tipoTamano;
    }

    public void setTipoTamano(TipoTamano tipoTamano) {
        this.tipoTamano = tipoTamano;
    }

    public TipoPapel getTipoPapel() {
        return tipoPapel;
    }

    public void setTipoPapel(TipoPapel tipoPapel) {
        this.tipoPapel = tipoPapel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tamanoPK != null ? tamanoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tamano)) {
            return false;
        }
        Tamano other = (Tamano) object;
        if ((this.tamanoPK == null && other.tamanoPK != null) || (this.tamanoPK != null && !this.tamanoPK.equals(other.tamanoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.progredi.impresiones.persistencia.entidades.Tamano[ tamanoPK=" + tamanoPK + " ]";
    }
    
}
