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
import javax.persistence.Lob;
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
@Table(name = "contenteditor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contenteditor.findAll", query = "SELECT c FROM Contenteditor c"),
    @NamedQuery(name = "Contenteditor.findByIdText", query = "SELECT c FROM Contenteditor c WHERE c.idText = :idText")})
public class Contenteditor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_text")
    private Integer idText;
    @Lob
    @Size(max = 65535)
    @Column(name = "text")
    private String text;

    public Contenteditor() {
    }

    public Contenteditor(Integer idText) {
        this.idText = idText;
    }

    public Integer getIdText() {
        return idText;
    }

    public void setIdText(Integer idText) {
        this.idText = idText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idText != null ? idText.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contenteditor)) {
            return false;
        }
        Contenteditor other = (Contenteditor) object;
        if ((this.idText == null && other.idText != null) || (this.idText != null && !this.idText.equals(other.idText))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.rempe.impresiones.persistencia.entidades.Contenteditor[ idText=" + idText + " ]";
    }
    
}
