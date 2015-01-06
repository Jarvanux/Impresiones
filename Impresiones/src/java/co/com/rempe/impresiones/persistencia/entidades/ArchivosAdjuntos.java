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
@Table(name = "archivos_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ArchivosAdjuntos.findAll", query = "SELECT a FROM ArchivosAdjuntos a"),
    @NamedQuery(name = "ArchivosAdjuntos.findByIdArchivo", query = "SELECT a FROM ArchivosAdjuntos a WHERE a.idArchivo = :idArchivo"),
    @NamedQuery(name = "ArchivosAdjuntos.findByNombreArchivo", query = "SELECT a FROM ArchivosAdjuntos a WHERE a.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "ArchivosAdjuntos.findByRutaArchivo", query = "SELECT a FROM ArchivosAdjuntos a WHERE a.rutaArchivo = :rutaArchivo"),
    @NamedQuery(name = "ArchivosAdjuntos.findByTamanioArchivo", query = "SELECT a FROM ArchivosAdjuntos a WHERE a.tamanioArchivo = :tamanioArchivo")})
public class ArchivosAdjuntos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_archivo")
    private Integer idArchivo;
    @Size(max = 100)
    @Column(name = "nombre_archivo")
    private String nombreArchivo;
    @Size(max = 300)
    @Column(name = "ruta_archivo")
    private String rutaArchivo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "tamanio_archivo")
    private Long tamanioArchivo;

    public ArchivosAdjuntos() {
    }

    public ArchivosAdjuntos(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Long getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(Long tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivo != null ? idArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArchivosAdjuntos)) {
            return false;
        }
        ArchivosAdjuntos other = (ArchivosAdjuntos) object;
        if ((this.idArchivo == null && other.idArchivo != null) || (this.idArchivo != null && !this.idArchivo.equals(other.idArchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.ArchivosAdjuntos[ idArchivo=" + idArchivo + " ]";
    }
    
}
