/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "tipo_papel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPapel.findAll", query = "SELECT t FROM TipoPapel t"),
    @NamedQuery(name = "TipoPapel.findByIdTipoPapel", query = "SELECT t FROM TipoPapel t WHERE t.idTipoPapel = :idTipoPapel"),
    @NamedQuery(name = "TipoPapel.findByPapel", query = "SELECT t FROM TipoPapel t WHERE t.papel = :papel"),
    @NamedQuery(name = "TipoPapel.findByPrecioPliego", query = "SELECT t FROM TipoPapel t WHERE t.precioPliego = :precioPliego")})
public class TipoPapel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_papel")
    private Integer idTipoPapel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "papel")
    private String papel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_pliego")
    private BigDecimal precioPliego;
    @ManyToMany(mappedBy = "tipoPapelList", fetch = FetchType.LAZY)
    private List<TipoImpresion> tipoImpresionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoPapel", fetch = FetchType.LAZY)
    private List<Tamano> tamanoList;

    public TipoPapel() {
    }

    public TipoPapel(Integer idTipoPapel) {
        this.idTipoPapel = idTipoPapel;
    }

    public TipoPapel(Integer idTipoPapel, String papel, BigDecimal precioPliego) {
        this.idTipoPapel = idTipoPapel;
        this.papel = papel;
        this.precioPliego = precioPliego;
    }

    public Integer getIdTipoPapel() {
        return idTipoPapel;
    }

    public void setIdTipoPapel(Integer idTipoPapel) {
        this.idTipoPapel = idTipoPapel;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public BigDecimal getPrecioPliego() {
        return precioPliego;
    }

    public void setPrecioPliego(BigDecimal precioPliego) {
        this.precioPliego = precioPliego;
    }

    @XmlTransient
    public List<TipoImpresion> getTipoImpresionList() {
        return tipoImpresionList;
    }

    public void setTipoImpresionList(List<TipoImpresion> tipoImpresionList) {
        this.tipoImpresionList = tipoImpresionList;
    }

    @XmlTransient
    public List<Tamano> getTamanoList() {
        return tamanoList;
    }

    public void setTamanoList(List<Tamano> tamanoList) {
        this.tamanoList = tamanoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPapel != null ? idTipoPapel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPapel)) {
            return false;
        }
        TipoPapel other = (TipoPapel) object;
        if ((this.idTipoPapel == null && other.idTipoPapel != null) || (this.idTipoPapel != null && !this.idTipoPapel.equals(other.idTipoPapel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.impresiones.persistencia.entidades.TipoPapel[ idTipoPapel=" + idTipoPapel + " ]";
    }
    
}
