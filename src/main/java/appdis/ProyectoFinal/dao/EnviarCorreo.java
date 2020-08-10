package appdis.ProyectoFinal.dao;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Stateless
public class EnviarCorreo {

	public void enviarMail(String asunto, String mensaje, String correoDestino) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("coopdmr0@gmail.com", "mjztsghipwhtczzz"); // Correo y contraseña que se va
																						// a utilizar
																						// para enviar el correo.
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("coopdmr0@gmail.com")); // Correo del que envia
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino)); // Correo al que se
																									// enviará el
																									// mensaje.
			message.setSubject(asunto); // Asunto
			message.setText(mensaje); // Mensaje

			Transport.send(message);

		} catch (MessagingException e) {

		}
	}

	public void enviarMail1(String asunto, String mensaje, String correoDestino, String archivo) throws AddressException, MessagingException {
		String Host = "smtp.gmail.com";
		String Password = "mjztsghipwhtczzz";
		String from = "coopdmr0@gmail.com";
		String toAddress = correoDestino;
		String filename = archivo;
		// Get system properties
		Properties props = System.getProperties();
		props.put("mail.smtp.Host", Host);
		props.put("mail.smtps.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getInstance(props, null);

		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, toAddress);
		message.setSubject(asunto);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(mensaje);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		messageBodyPart = new MimeBodyPart();
		FileDataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);
		message.setContent(multipart);

		try {
			Transport tr = session.getTransport("smtps");
			tr.connect(Host, from, Password);
			tr.sendMessage(message, message.getAllRecipients());
			System.out.println("Mail Sent Successfully");
			tr.close();
		} catch (SendFailedException sfe) {
			System.out.println(sfe);
		}
	}
}


