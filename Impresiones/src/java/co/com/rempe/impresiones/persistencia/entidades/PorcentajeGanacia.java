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
@Table(name = "porcentaje_ganacia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PorcentajeGanacia.findAll", query = "SELECT p FROM PorcentajeGanacia p"),
    @NamedQuery(name = "PorcentajeGanacia.findByIdPorcentaje", query = "SELECT p FROM PorcentajeGanacia p WHERE p.idPorcentaje = :idPorcentaje"),
    @NamedQuery(name = "PorcentajeGanacia.findByInicio", query = "SELECT p FROM PorcentajeGanacia p WHERE p.inicio = :inicio"),
    @NamedQuery(name = "PorcentajeGanacia.findByFin", query = "SELECT p FROM PorcentajeGanacia p WHERE p.fin = :fin"),
    @NamedQuery(name = "PorcentajeGanacia.findByPorcentaje", query = "SELECT p FROM PorcentajeGanacia p WHERE p.porcentaje = :porcentaje")})
public class PorcentajeGanacia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_porcentaje")
    private Integer idPorcentaje;
    @Column(name = "inicio")
    private Integer inicio;
    @Column(name = "fin")
    private Integer fin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje")
    private Double porcentaje;

    public PorcentajeGanacia() {
    }

    public PorcentajeGanacia(Integer idPorcentaje) {
        this.idPorcentaje = idPorcentaje;
    }

    public Integer getIdPorcentaje() {
        return idPorcentaje;
    }

    public void setIdPorcentaje(Integer idPorcentaje) {
        this.idPorcentaje = idPorcentaje;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFin() {
        return fin;
    }

    public void setFin(Integer fin) {
        this.fin = fin;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPorcentaje != null ? idPorcentaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PorcentajeGanacia)) {
            return false;
        }
        PorcentajeGanacia other = (PorcentajeGanacia) object;
        if ((this.idPorcentaje == null && other.idPorcentaje != null) || (this.idPorcentaje != null && !this.idPorcentaje.equals(other.idPorcentaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.PorcentajeGanacia[ idPorcentaje=" + idPorcentaje + " ]";
    }
    
}
