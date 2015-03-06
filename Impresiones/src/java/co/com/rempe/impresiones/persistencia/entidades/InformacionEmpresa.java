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
@Table(name = "informacion_empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformacionEmpresa.findAll", query = "SELECT i FROM InformacionEmpresa i"),
    @NamedQuery(name = "InformacionEmpresa.findByIdInformacion", query = "SELECT i FROM InformacionEmpresa i WHERE i.idInformacion = :idInformacion"),
    @NamedQuery(name = "InformacionEmpresa.findByNombre", query = "SELECT i FROM InformacionEmpresa i WHERE i.nombre = :nombre"),
    @NamedQuery(name = "InformacionEmpresa.findByNit", query = "SELECT i FROM InformacionEmpresa i WHERE i.nit = :nit"),
    @NamedQuery(name = "InformacionEmpresa.findByCorreo", query = "SELECT i FROM InformacionEmpresa i WHERE i.correo = :correo"),
    @NamedQuery(name = "InformacionEmpresa.findByClaveCorreo", query = "SELECT i FROM InformacionEmpresa i WHERE i.claveCorreo = :claveCorreo"),
    @NamedQuery(name = "InformacionEmpresa.findByPaginaWeb", query = "SELECT i FROM InformacionEmpresa i WHERE i.paginaWeb = :paginaWeb"),
    @NamedQuery(name = "InformacionEmpresa.findByFacebook", query = "SELECT i FROM InformacionEmpresa i WHERE i.facebook = :facebook"),
    @NamedQuery(name = "InformacionEmpresa.findByGmail", query = "SELECT i FROM InformacionEmpresa i WHERE i.gmail = :gmail"),
    @NamedQuery(name = "InformacionEmpresa.findByTwitter", query = "SELECT i FROM InformacionEmpresa i WHERE i.twitter = :twitter"),
    @NamedQuery(name = "InformacionEmpresa.findByGooglemas", query = "SELECT i FROM InformacionEmpresa i WHERE i.googlemas = :googlemas"),
    @NamedQuery(name = "InformacionEmpresa.findByClaveGeneral", query = "SELECT i FROM InformacionEmpresa i WHERE i.claveGeneral = :claveGeneral"),
    @NamedQuery(name = "InformacionEmpresa.findByTelefono", query = "SELECT i FROM InformacionEmpresa i WHERE i.telefono = :telefono"),
    @NamedQuery(name = "InformacionEmpresa.findByDireccion", query = "SELECT i FROM InformacionEmpresa i WHERE i.direccion = :direccion"),
    @NamedQuery(name = "InformacionEmpresa.findByCelular", query = "SELECT i FROM InformacionEmpresa i WHERE i.celular = :celular")})
public class InformacionEmpresa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_informacion")
    private Integer idInformacion;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "pais")
    private String pais;
    @Column(name = "gerente")
    private String gerente;
    @Column(name = "url_sistema")
    private String urlSistema;
    @Size(max = 50)
    @Column(name = "nit")
    private String nit;
    @Size(max = 100)
    @Column(name = "correo")
    private String correo;
    @Size(max = 260)
    @Column(name = "clave_correo")
    private String claveCorreo;
    @Size(max = 260)
    @Column(name = "pagina_web")
    private String paginaWeb;
    @Size(max = 260)
    @Column(name = "facebook")
    private String facebook;
    @Size(max = 260)
    @Column(name = "gmail")
    private String gmail;
    @Size(max = 260)
    @Column(name = "twitter")
    private String twitter;
    @Size(max = 260)
    @Column(name = "googlemas")
    private String googlemas;
    @Size(max = 260)
    @Column(name = "clave_general")
    private String claveGeneral;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "admin_principal")
    private Integer adminPrincipal;
    @Size(max = 50)
    @Column(name = "celular")
    private String celular;

    public InformacionEmpresa() {
    }

    public InformacionEmpresa(Integer idInformacion) {
        this.idInformacion = idInformacion;
    }

    public Integer getIdInformacion() {
        return idInformacion;
    }

    public void setIdInformacion(Integer idInformacion) {
        this.idInformacion = idInformacion;
    }

    public String getUrlSistema() {
        return urlSistema;
    }

    public void setUrlSistema(String urlSistema) {
        this.urlSistema = urlSistema;
    }        

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGerente() {
        return gerente;
    }

    public void setGerente(String gerente) {
        this.gerente = gerente;
    }        

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getAdminPrincipal() {
        return adminPrincipal;
    }

    public void setAdminPrincipal(Integer adminPrincipal) {
        this.adminPrincipal = adminPrincipal;
    }
    
    public String getClaveCorreo() {
        return claveCorreo;
    }

    public void setClaveCorreo(String claveCorreo) {
        this.claveCorreo = claveCorreo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getGooglemas() {
        return googlemas;
    }

    public void setGooglemas(String googlemas) {
        this.googlemas = googlemas;
    }

    public String getClaveGeneral() {
        return claveGeneral;
    }

    public void setClaveGeneral(String claveGeneral) {
        this.claveGeneral = claveGeneral;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInformacion != null ? idInformacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformacionEmpresa)) {
            return false;
        }
        InformacionEmpresa other = (InformacionEmpresa) object;
        if ((this.idInformacion == null && other.idInformacion != null) || (this.idInformacion != null && !this.idInformacion.equals(other.idInformacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.InformacionEmpresa[ idInformacion=" + idInformacion + " ]";
    }
    
}
