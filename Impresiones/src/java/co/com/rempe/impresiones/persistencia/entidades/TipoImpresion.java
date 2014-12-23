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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "tipo_impresion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoImpresion.findAll", query = "SELECT t FROM TipoImpresion t"),
    @NamedQuery(name = "TipoImpresion.findByIdTipoImpresion", query = "SELECT t FROM TipoImpresion t WHERE t.idTipoImpresion = :idTipoImpresion"),
    @NamedQuery(name = "TipoImpresion.findByTipoImpresion", query = "SELECT t FROM TipoImpresion t WHERE t.tipoImpresion = :tipoImpresion")})
public class TipoImpresion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_impresion")
    private Integer idTipoImpresion;
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "tipo_impresion")
    private String tipoImpresion;    
    @JoinTable(name = "impresion_papel", joinColumns = {
        @JoinColumn(name = "id_tipo_impresion", referencedColumnName = "id_tipo_impresion")}, inverseJoinColumns = {
        @JoinColumn(name = "id_tipo_papel", referencedColumnName = "id_tipo_papel")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TipoPapel> tipoPapelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoImpresion", fetch = FetchType.LAZY)
    private List<ImpresionTamano> impresionTamanoList;

    public TipoImpresion() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    

    public TipoImpresion(Integer idTipoImpresion) {
        this.idTipoImpresion = idTipoImpresion;
    }

    public TipoImpresion(Integer idTipoImpresion, String tipoImpresion) {
        this.idTipoImpresion = idTipoImpresion;
        this.tipoImpresion = tipoImpresion;
    }

    public Integer getIdTipoImpresion() {
        return idTipoImpresion;
    }

    public void setIdTipoImpresion(Integer idTipoImpresion) {
        this.idTipoImpresion = idTipoImpresion;
    }

    public String getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(String tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    @XmlTransient
    public List<TipoPapel> getTipoPapelList() {
        return tipoPapelList;
    }

    public void setTipoPapelList(List<TipoPapel> tipoPapelList) {
        this.tipoPapelList = tipoPapelList;
    }

    @XmlTransient
    public List<ImpresionTamano> getImpresionTamanoList() {
        return impresionTamanoList;
    }

    public void setImpresionTamanoList(List<ImpresionTamano> impresionTamanoList) {
        this.impresionTamanoList = impresionTamanoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoImpresion != null ? idTipoImpresion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoImpresion)) {
            return false;
        }
        TipoImpresion other = (TipoImpresion) object;
        if ((this.idTipoImpresion == null && other.idTipoImpresion != null) || (this.idTipoImpresion != null && !this.idTipoImpresion.equals(other.idTipoImpresion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.progredi.impresiones.persistencia.entidades.TipoImpresion[ idTipoImpresion=" + idTipoImpresion + " ]";
    }
    
}
