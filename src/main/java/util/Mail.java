package util;

import methods.GlobalVariables;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

    public static void sendEmail() {
        Properties props = new Properties();
        //Properties for office 365 email service
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");

        //User name and password
        String username = "";
        String password = "";


        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(RecipientType.TO, InternetAddress.parse(GlobalVariables.reportReceiverEmail));
            message.setSubject("Reporte de ejemplo");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            messageBodyPart.setContent(
                    "<h1>Reporte de ejemplo</h1>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p>Buen dia,</p>\n" +
                        "<p>En el siguiente correo se encuentran adjuntos, en un archivo ZIP, los resultados obtenidos luego de correr las pruebas automatizadas.</p>\n" +
                        "<p>&nbsp;</p>\n" +
                        "<p><strong><em>Departamento de Testing</em></strong></p>"
                    ,"text/html; charset=utf-8");
            multipart.addBodyPart(messageBodyPart);


                String filename = "TestOutput/Zipped_HTML_Report.zip";
                messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(filename);
                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(filename);
                multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            Transport.send(message);
            System.out.println("Done");
        } catch (MessagingException var15) {
            var15.printStackTrace();
        }
    }
}
