/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "tipo_plastico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPlastico.findAll", query = "SELECT t FROM TipoPlastico t"),
    @NamedQuery(name = "TipoPlastico.findByIdTipoPlastico", query = "SELECT t FROM TipoPlastico t WHERE t.idTipoPlastico = :idTipoPlastico"),
    @NamedQuery(name = "TipoPlastico.findByTipo", query = "SELECT t FROM TipoPlastico t WHERE t.tipo = :tipo")})
public class TipoPlastico implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_plastico")
    private Integer idTipoPlastico;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;

    public TipoPlastico() {
    }

    public TipoPlastico(Integer idTipoPlastico) {
        this.idTipoPlastico = idTipoPlastico;
    }

    public Integer getIdTipoPlastico() {
        return idTipoPlastico;
    }

    public void setIdTipoPlastico(Integer idTipoPlastico) {
        this.idTipoPlastico = idTipoPlastico;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPlastico != null ? idTipoPlastico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPlastico)) {
            return false;
        }
        TipoPlastico other = (TipoPlastico) object;
        if ((this.idTipoPlastico == null && other.idTipoPlastico != null) || (this.idTipoPlastico != null && !this.idTipoPlastico.equals(other.idTipoPlastico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.TipoPlastico[ idTipoPlastico=" + idTipoPlastico + " ]";
    }
    
}
