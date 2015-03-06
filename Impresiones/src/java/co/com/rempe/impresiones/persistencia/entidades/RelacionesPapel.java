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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "relaciones_papel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionesPapel.findAll", query = "SELECT r FROM RelacionesPapel r"),
    @NamedQuery(name = "RelacionesPapel.findByIdRelacion", query = "SELECT r FROM RelacionesPapel r WHERE r.idRelacion = :idRelacion"),
    @NamedQuery(name = "RelacionesPapel.findByTipoImpresion", query = "SELECT r FROM RelacionesPapel r WHERE r.tipoImpresion = :tipoImpresion"),
    @NamedQuery(name = "RelacionesPapel.findByModoImpresion", query = "SELECT r FROM RelacionesPapel r WHERE r.modoImpresion = :modoImpresion"),
    @NamedQuery(name = "RelacionesPapel.findByTamanoPapel", query = "SELECT r FROM RelacionesPapel r WHERE r.tamanoPapel = :tamanoPapel"),
    @NamedQuery(name = "RelacionesPapel.findByTipoPapel", query = "SELECT r FROM RelacionesPapel r WHERE r.tipoPapel = :tipoPapel")})
public class RelacionesPapel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_relacion")
    private Integer idRelacion;
    @Column(name = "tipo_impresion")
    private Integer tipoImpresion;
    @Column(name = "modo_impresion")
    private Integer modoImpresion;
    @Column(name = "tamano_papel")
    private Integer tamanoPapel;
    @Column(name = "tipo_papel")
    private Integer tipoPapel;

    public RelacionesPapel() {
    }

    public RelacionesPapel(Integer idRelacion) {
        this.idRelacion = idRelacion;
    }

    public Integer getIdRelacion() {
        return idRelacion;
    }

    public void setIdRelacion(Integer idRelacion) {
        this.idRelacion = idRelacion;
    }

    public Integer getTipoImpresion() {
        return tipoImpresion;
    }

    public void setTipoImpresion(Integer tipoImpresion) {
        this.tipoImpresion = tipoImpresion;
    }

    public Integer getModoImpresion() {
        return modoImpresion;
    }

    public void setModoImpresion(Integer modoImpresion) {
        this.modoImpresion = modoImpresion;
    }

    public Integer getTamanoPapel() {
        return tamanoPapel;
    }

    public void setTamanoPapel(Integer tamanoPapel) {
        this.tamanoPapel = tamanoPapel;
    }

    public Integer getTipoPapel() {
        return tipoPapel;
    }

    public void setTipoPapel(Integer tipoPapel) {
        this.tipoPapel = tipoPapel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacion != null ? idRelacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionesPapel)) {
            return false;
        }
        RelacionesPapel other = (RelacionesPapel) object;
        if ((this.idRelacion == null && other.idRelacion != null) || (this.idRelacion != null && !this.idRelacion.equals(other.idRelacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.RelacionesPapel[ idRelacion=" + idRelacion + " ]";
    }
    
}
