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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jhonjaider1000
 */
@Entity
@Table(name = "direcciones_usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DireccionesUsuarios.findAll", query = "SELECT d FROM DireccionesUsuarios d"),
    @NamedQuery(name = "DireccionesUsuarios.findByIdDireccion", query = "SELECT d FROM DireccionesUsuarios d WHERE d.idDireccion = :idDireccion"),
    @NamedQuery(name = "DireccionesUsuarios.findByIdUsuario", query = "SELECT d FROM DireccionesUsuarios d WHERE d.idUsuario = :idUsuario"),
    @NamedQuery(name = "DireccionesUsuarios.findByDireccion", query = "SELECT d FROM DireccionesUsuarios d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "DireccionesUsuarios.findByFechaRegistro", query = "SELECT d FROM DireccionesUsuarios d WHERE d.fechaRegistro = :fechaRegistro")})
public class DireccionesUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_direccion")
    private Integer idDireccion;
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 140)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    public DireccionesUsuarios() {
    }

    public DireccionesUsuarios(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Integer getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Integer idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDireccion != null ? idDireccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DireccionesUsuarios)) {
            return false;
        }
        DireccionesUsuarios other = (DireccionesUsuarios) object;
        if ((this.idDireccion == null && other.idDireccion != null) || (this.idDireccion != null && !this.idDireccion.equals(other.idDireccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.DireccionesUsuarios[ idDireccion=" + idDireccion + " ]";
    }
    
}
