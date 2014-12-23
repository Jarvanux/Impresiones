/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hrey
 */
@Entity
@Table(name = "tipo_tamano")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoTamano.findAll", query = "SELECT t FROM TipoTamano t"),
    @NamedQuery(name = "TipoTamano.findByIdTipoTamano", query = "SELECT t FROM TipoTamano t WHERE t.idTipoTamano = :idTipoTamano"),
    @NamedQuery(name = "TipoTamano.findByTipoTamano", query = "SELECT t FROM TipoTamano t WHERE t.tipoTamano = :tipoTamano"),
    @NamedQuery(name = "TipoTamano.findByNumeroHojas", query = "SELECT t FROM TipoTamano t WHERE t.numeroHojas = :numeroHojas")})
public class TipoTamano implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_tamano")
    private Integer idTipoTamano;
    @Column(name = "tipo_tamano")
    private String tipoTamano;
    @Column(name = "numero_hojas")
    private Double numeroHojas;
    @Column(name = "costo_impresion")
    private Integer costoImpresion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTamano", fetch = FetchType.LAZY)
    private List<ImpresionTamano> impresionTamanoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoTamano", fetch = FetchType.LAZY)
    private List<Tamano> tamanoList;

    public TipoTamano() {
    }

    public TipoTamano(Integer idTipoTamano) {
        this.idTipoTamano = idTipoTamano;
    }

    public Integer getIdTipoTamano() {
        return idTipoTamano;
    }

    public void setIdTipoTamano(Integer idTipoTamano) {
        this.idTipoTamano = idTipoTamano;
    }

    public String getTipoTamano() {
        return tipoTamano;
    }

    public void setTipoTamano(String tipoTamano) {
        this.tipoTamano = tipoTamano;
    }

    public Double getNumeroHojas() {
        return numeroHojas;
    }

    public void setNumeroHojas(Double numeroHojas) {
        this.numeroHojas = numeroHojas;
    }

    public Integer getCostoImpresion() {
        return costoImpresion;
    }

    public void setCostoImpresion(Integer costoImpresion) {
        this.costoImpresion = costoImpresion;
    }    

    @XmlTransient
    public List<ImpresionTamano> getImpresionTamanoList() {
        return impresionTamanoList;
    }

    public void setImpresionTamanoList(List<ImpresionTamano> impresionTamanoList) {
        this.impresionTamanoList = impresionTamanoList;
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
        hash += (idTipoTamano != null ? idTipoTamano.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoTamano)) {
            return false;
        }
        TipoTamano other = (TipoTamano) object;
        if ((this.idTipoTamano == null && other.idTipoTamano != null) || (this.idTipoTamano != null && !this.idTipoTamano.equals(other.idTipoTamano))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.progredi.impresiones.persistencia.entidades.TipoTamano[ idTipoTamano=" + idTipoTamano + " ]";
    }
    
}
