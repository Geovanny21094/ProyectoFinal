package appdis.ProyectoFinal.dao;

import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Stateless
public class EnviarCorreo {

	
	public void enviarMail(String asunto, String mensaje, String correoDestino) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("coopdmr0@gmail.com",
                        "3694compac");  //Correo y contraseña que se va a utilizar 
                                            //para enviar el correo.
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("coopdmr0@gmail.com"));  //Correo del que envia
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correoDestino)); //Correo al que se enviará el mensaje.
            message.setSubject(asunto);   //Asunto
            message.setText(mensaje); //Mensaje

            Transport.send(message);

            

        } catch (MessagingException e) {

        }
    }
	
}
