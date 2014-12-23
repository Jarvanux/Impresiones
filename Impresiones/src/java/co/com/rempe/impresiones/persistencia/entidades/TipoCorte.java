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
@Table(name = "tipo_corte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoCorte.findAll", query = "SELECT t FROM TipoCorte t"),
    @NamedQuery(name = "TipoCorte.findByIdTipoCorte", query = "SELECT t FROM TipoCorte t WHERE t.idTipoCorte = :idTipoCorte"),
    @NamedQuery(name = "TipoCorte.findByTipo", query = "SELECT t FROM TipoCorte t WHERE t.tipo = :tipo")})
public class TipoCorte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_corte")
    private Integer idTipoCorte;
    @Size(max = 50)
    @Column(name = "tipo")
    private String tipo;

    public TipoCorte() {
    }

    public TipoCorte(Integer idTipoCorte) {
        this.idTipoCorte = idTipoCorte;
    }

    public Integer getIdTipoCorte() {
        return idTipoCorte;
    }

    public void setIdTipoCorte(Integer idTipoCorte) {
        this.idTipoCorte = idTipoCorte;
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
        hash += (idTipoCorte != null ? idTipoCorte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCorte)) {
            return false;
        }
        TipoCorte other = (TipoCorte) object;
        if ((this.idTipoCorte == null && other.idTipoCorte != null) || (this.idTipoCorte != null && !this.idTipoCorte.equals(other.idTipoCorte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.TipoCorte[ idTipoCorte=" + idTipoCorte + " ]";
    }
    
}
