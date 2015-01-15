/*
 * To change this template, choose Tools | Templates
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hrey
 */
@Entity
@Table(name = "chat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c"),
    @NamedQuery(name = "Chat.findByIdChat", query = "SELECT c FROM Chat c WHERE c.idChat = :idChat"),
    @NamedQuery(name = "Chat.findByFecha", query = "SELECT c FROM Chat c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Chat.findByIdUsuarioOrigen", query = "SELECT c FROM Chat c WHERE c.idUsuarioOrigen = :idUsuarioOrigen"),
    @NamedQuery(name = "Chat.findByIdUsuarioDestino", query = "SELECT c FROM Chat c WHERE c.idUsuarioDestino = :idUsuarioDestino")})
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat")
    private Integer idChat;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Lob
    @Column(name = "mensaje")
    private String mensaje;
    @Column(name = "id_usuario_origen")
    private Long idUsuarioOrigen;
    @Column(name = "id_usuario_destino")
    private Long idUsuarioDestino;    
    @Column(name = "id_conversacion")
    private Long idConversacion;   
    @Column(name = "accion")
    private int accion;

    public Chat() {
    }

    public Chat(Integer idChat) {
        this.idChat = idChat;
    }

    public int getAccion() {
        return accion;
    }

    public void setAccion(int accion) {
        this.accion = accion;
    }        

    public Chat(Integer idChat, Date fecha) {
        this.idChat = idChat;
        this.fecha = fecha;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdConversacion() {
        return idConversacion;
    }

    public void setIdConversacion(Long idConversacion) {
        this.idConversacion = idConversacion;
    }    

    public Long getIdUsuarioOrigen() {
        return idUsuarioOrigen;
    }

    public void setIdUsuarioOrigen(Long idUsuarioOrigen) {
        this.idUsuarioOrigen = idUsuarioOrigen;
    }

    public Long getIdUsuarioDestino() {
        return idUsuarioDestino;
    }

    public void setIdUsuarioDestino(Long idUsuarioDestino) {
        this.idUsuarioDestino = idUsuarioDestino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChat != null ? idChat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
        if ((this.idChat == null && other.idChat != null) || (this.idChat != null && !this.idChat.equals(other.idChat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.progredi.impresiones.persistencia.entidades.Chat[ idChat=" + idChat + " ]";
    }
    
}
