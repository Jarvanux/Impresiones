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
import javax.persistence.Lob;
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
@Table(name = "menajes_buzon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenajesBuzon.findAll", query = "SELECT m FROM MenajesBuzon m"),
    @NamedQuery(name = "MenajesBuzon.findByIdMensaje", query = "SELECT m FROM MenajesBuzon m WHERE m.idMensaje = :idMensaje"),
    @NamedQuery(name = "MenajesBuzon.findByNombre", query = "SELECT m FROM MenajesBuzon m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "MenajesBuzon.findByApellido", query = "SELECT m FROM MenajesBuzon m WHERE m.apellido = :apellido"),
    @NamedQuery(name = "MenajesBuzon.findByFecha", query = "SELECT m FROM MenajesBuzon m WHERE m.fecha = :fecha")})
public class MenajesBuzon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_mensaje")
    private Integer idMensaje;
    @Column(name = "correo")
    private String correo;
    @Column(name = "usuario_lector")
    private String usuarioLector;
    @Column(name = "leido")
    private Integer leido;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 50)
    @Column(name = "apellido")
    private String apellido;
    @Lob
    @Size(max = 65535)
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public MenajesBuzon() {
    }

    public MenajesBuzon(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Integer getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Integer idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getUsuarioLector() {
        return usuarioLector;
    }

    public void setUsuarioLector(String usuarioLector) {
        this.usuarioLector = usuarioLector;
    }

    public Integer getLeido() {
        return leido;
    }

    public void setLeido(Integer leido) {
        this.leido = leido;
    }
        
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMensaje != null ? idMensaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenajesBuzon)) {
            return false;
        }
        MenajesBuzon other = (MenajesBuzon) object;
        if ((this.idMensaje == null && other.idMensaje != null) || (this.idMensaje != null && !this.idMensaje.equals(other.idMensaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.MenajesBuzon[ idMensaje=" + idMensaje + " ]";
    }
    
}
