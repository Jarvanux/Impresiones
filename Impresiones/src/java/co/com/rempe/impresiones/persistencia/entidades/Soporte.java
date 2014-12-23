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
@Table(name = "soporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Soporte.findAll", query = "SELECT s FROM Soporte s"),
    @NamedQuery(name = "Soporte.findByIdSoporte", query = "SELECT s FROM Soporte s WHERE s.idSoporte = :idSoporte"),
    @NamedQuery(name = "Soporte.findByIdUsuarioAdmin", query = "SELECT s FROM Soporte s WHERE s.idUsuarioAdmin = :idUsuarioAdmin"),
    @NamedQuery(name = "Soporte.findByNumAtendidos", query = "SELECT s FROM Soporte s WHERE s.numAtendidos = :numAtendidos"),
    @NamedQuery(name = "Soporte.findByFecha", query = "SELECT s FROM Soporte s WHERE s.fecha = :fecha")})
public class Soporte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_soporte")
    private Integer idSoporte;
    @Column(name = "id_usuario_admin")
    private Integer idUsuarioAdmin;
    @Column(name = "num_atendidos")
    private Integer numAtendidos;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Soporte() {
    }

    public Soporte(Integer idSoporte) {
        this.idSoporte = idSoporte;
    }

    public Integer getIdSoporte() {
        return idSoporte;
    }

    public void setIdSoporte(Integer idSoporte) {
        this.idSoporte = idSoporte;
    }

    public Integer getIdUsuarioAdmin() {
        return idUsuarioAdmin;
    }

    public void setIdUsuarioAdmin(Integer idUsuarioAdmin) {
        this.idUsuarioAdmin = idUsuarioAdmin;
    }

    public Integer getNumAtendidos() {
        return numAtendidos;
    }

    public void setNumAtendidos(Integer numAtendidos) {
        this.numAtendidos = numAtendidos;
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
        hash += (idSoporte != null ? idSoporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Soporte)) {
            return false;
        }
        Soporte other = (Soporte) object;
        if ((this.idSoporte == null && other.idSoporte != null) || (this.idSoporte != null && !this.idSoporte.equals(other.idSoporte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.Soporte[ idSoporte=" + idSoporte + " ]";
    }
    
}
