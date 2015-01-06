/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.rempe.impresiones.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "visitantes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visitantes.findAll", query = "SELECT v FROM Visitantes v"),
    @NamedQuery(name = "Visitantes.findByIdVisitante", query = "SELECT v FROM Visitantes v WHERE v.idVisitante = :idVisitante"),
    @NamedQuery(name = "Visitantes.findByIpComputadora", query = "SELECT v FROM Visitantes v WHERE v.ipComputadora = :ipComputadora"),
    @NamedQuery(name = "Visitantes.findByFecha", query = "SELECT v FROM Visitantes v WHERE v.fecha = :fecha")})
public class Visitantes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_visitante")
    private Integer idVisitante;
    @Column(name = "ip_computadora")
    private Integer ipComputadora;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public Visitantes() {
    }

    public Visitantes(Integer idVisitante) {
        this.idVisitante = idVisitante;
    }

    public Integer getIdVisitante() {
        return idVisitante;
    }

    public void setIdVisitante(Integer idVisitante) {
        this.idVisitante = idVisitante;
    }

    public Integer getIpComputadora() {
        return ipComputadora;
    }

    public void setIpComputadora(Integer ipComputadora) {
        this.ipComputadora = ipComputadora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVisitante != null ? idVisitante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visitantes)) {
            return false;
        }
        Visitantes other = (Visitantes) object;
        if ((this.idVisitante == null && other.idVisitante != null) || (this.idVisitante != null && !this.idVisitante.equals(other.idVisitante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.Visitantes[ idVisitante=" + idVisitante + " ]";
    }
    
}
