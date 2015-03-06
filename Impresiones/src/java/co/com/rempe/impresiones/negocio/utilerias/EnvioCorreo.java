/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.rempe.impresiones.negocio.utilerias;

/**
 *
 * @author jhonjaider1000
 */
/*
 /*Se importan las librerias de javax.Mail para poder enviar correos
 esta libreria la descargue y la agregue 
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

public class EnvioCorreo {    


    //Este metodo se usará cuando se desee enviar un correo con un archivo.
    public void envioCorreo(String correo, String asunto, String mensaje, String archivo, String ruta) {
        /* Se agregan las propiedades creando un objeto de la clase Properties*/
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(propiedades);
        session.setDebug(true);

        try {

            /*se crea un BodyPart para el texto*/
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            /*se crea un BodyPart para el archivo adjunto*/
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta + archivo)));
            adjunto.setFileName(archivo);

            /*Se agregan el texto y el archivo adjunto a multiparte*/
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            //Se abre la sesion y se agregan los elementos del correo
            MimeMessage message = new MimeMessage(session);

            //este sera el correo destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));

            //este es el asunto
            message.setSubject(asunto);
            //se agrega la multiparte donde esta el texto y el archivo al mensaje
            message.setContent(multiParte);

            Transport transport = session.getTransport("smtp");
            //correo que realiza el envio con su contraseña
            transport.connect("sistemaopline@gmail.com", "sistemaopline2014");
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Mensaje enviado");            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //Este método se puede usar cuando se desee enviar solo un correo de texto.
    public Boolean envioCorreo(String titulo, String correo, String correoEmpresa, String claveCorreo, String asunto, String mensaje, String firma, String ruta, String archivo) {        
        /* Se agregan las propiedades creando un objeto de la clase Properties*/        
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(propiedades);
        session.setDebug(true);

        try {

            MimeMultipart multipart = new MimeMultipart("related");

            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = titulo
                    + mensaje + firma;
            messageBodyPart.setContent(htmlText, "text/html");

            // add it
            multipart.addBodyPart(messageBodyPart);

            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            DataSource fds = new FileDataSource(ruta+archivo);
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");

            // add it
            multipart.addBodyPart(messageBodyPart);

            // put everything together            
            //Se abre la sesion y se agregan los elementos del correo
            MimeMessage message = new MimeMessage(session);

            //este sera el correo destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));

            //este es el asunto
            message.setSubject(asunto);
            //se agrega la multiparte donde esta el texto y el archivo al mensaje
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            //correo que realiza el envio con su contraseña
            transport.connect(correoEmpresa,claveCorreo);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Mensaje enviado");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }     
    public Boolean envioCorreoSimple(String titulo, String correo, String correoEmpresa, String claveCorreo, String asunto, String mensaje) {        
        /* Se agregan las propiedades creando un objeto de la clase Properties*/        
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        Session session = Session.getDefaultInstance(propiedades);
        session.setDebug(true);

        try {

            MimeMultipart multipart = new MimeMultipart("related");

            // first part  (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = titulo
                    + mensaje;
            messageBodyPart.setContent(htmlText, "text/html");

            // add it
            multipart.addBodyPart(messageBodyPart);

            // put everything together            
            //Se abre la sesion y se agregan los elementos del correo
            MimeMessage message = new MimeMessage(session);

            //este sera el correo destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo));

            //este es el asunto
            message.setSubject(asunto);
            //se agrega la multiparte donde esta el texto y el archivo al mensaje
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            //correo que realiza el envio con su contraseña
            transport.connect(correoEmpresa,claveCorreo);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Mensaje enviado");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }     
}
