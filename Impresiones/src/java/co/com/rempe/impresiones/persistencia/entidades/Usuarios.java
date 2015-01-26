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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByApodo", query = "SELECT u FROM Usuarios u WHERE u.apodo = :apodo"),
    @NamedQuery(name = "Usuarios.findByNombres", query = "SELECT u FROM Usuarios u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuarios.findByApellidos", query = "SELECT u FROM Usuarios u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuarios.findByEmail", query = "SELECT u FROM Usuarios u WHERE u.email = :email"),
    @NamedQuery(name = "Usuarios.findByCedula", query = "SELECT u FROM Usuarios u WHERE u.cedula = :cedula"),
    @NamedQuery(name = "Usuarios.findByCelular", query = "SELECT u FROM Usuarios u WHERE u.celular = :celular"),
    @NamedQuery(name = "Usuarios.findByTelefono", query = "SELECT u FROM Usuarios u WHERE u.telefono = :telefono"),
    @NamedQuery(name = "Usuarios.findByCiudad", query = "SELECT u FROM Usuarios u WHERE u.ciudad = :ciudad"),
    @NamedQuery(name = "Usuarios.findByContrasena", query = "SELECT u FROM Usuarios u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuarios.findByZona", query = "SELECT u FROM Usuarios u WHERE u.zona = :zona"),
    @NamedQuery(name = "Usuarios.findByIdRol", query = "SELECT u FROM Usuarios u WHERE u.idRol = :idRol"),
    @NamedQuery(name = "Usuarios.findByEstado", query = "SELECT u FROM Usuarios u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuarios.findByFechaNacimiento", query = "SELECT u FROM Usuarios u WHERE u.fechaNacimiento = :fechaNacimiento")})
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Size(max = 50)
    @Column(name = "apodo")
    private String apodo;
    @Size(max = 50)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 50)
    @Column(name = "apellidos")
    private String apellidos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 20)
    @Column(name = "cedula")
    private String cedula;
    @Size(max = 20)
    @Column(name = "celular")
    private String celular;
    @Size(max = 20)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "ciudad")
    private Integer ciudad;
    @Size(max = 300)
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "zona")
    private Integer zona;
    @Column(name = "id_rol")
    private Integer idRol;
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "conectado")
    private boolean conectado;    
    @Column(name = "comprando")
    private boolean comprando;    
    @Column(name = "sesion_cerrada_admin")
    private boolean sesionAdminCerrada;    
    @Column(name = "ultima_conexion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ultimaConexion;    
    @Column(name = "visitante_temporal")
    private boolean visitanteTemporal;
    @Column(name = "fecha_registro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRegistro;
    

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }        

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApodo() {
        return apodo;
    }

    public boolean getConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public boolean getComprando() {
        return comprando;
    }

    public void setComprando(boolean comprando) {
        this.comprando = comprando;
    }
        
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public boolean isVisitanteTemporal() {
        return visitanteTemporal;
    }

    public void setVisitanteTemporal(boolean visitanteTemporal) {
        this.visitanteTemporal = visitanteTemporal;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getCiudad() {
        return ciudad;
    }

    public void setCiudad(Integer ciudad) {
        this.ciudad = ciudad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getZona() {
        return zona;
    }

    public void setZona(Integer zona) {
        this.zona = zona;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public boolean getSesionAdminCerrada() {
        return sesionAdminCerrada;
    }

    public void setSesionAdminCerrada(boolean sesionAdminCerrada) {
        this.sesionAdminCerrada = sesionAdminCerrada;
    }
    
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.Usuarios[ idUsuario=" + idUsuario + " ]";
    }
    
}
