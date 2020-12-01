package email;

/*
 * Goto icon->Manage Your Google Account->Security->Less secure app access (ON)
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class SendEmailUsingGMailSMTP {

	// -- set the gmail host URL
	final static private String host = "smtp.gmail.com";
	final static private String port = "587";

	// -- You must have a valid gmail username/password pair to use
	// gmail as a SMTP service
    // changed password...
	final static private String gmailusername = "guacamole.chipolte@gmail.com";
	final static private String gmailpassword = "hthCXquw366szN9";
	
	public static void main(String[] args) {
        // -- comma separated values of to email addresses
        String to = "acoppa@callutheran.edu, elizabethgonzalez@callutheran.edu, tmcpeters@callutheran.edu, ianrelecker1@gmail.com";
		sendMail(to);
	}
	
    public static void sendMail(String to) {
        // -- Configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // -- Create a session with required user details
        //    this is basically logging into the gmail account
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(gmailusername, gmailpassword);
            }
        });
        try {
            //-- create the Message to be sent
            MimeMessage msg = new MimeMessage(session);

            // -- get the internet addresses for the recipients
            InternetAddress[] address = InternetAddress.parse(to, true);
            
            // -- set the recipients
            msg.setRecipients(Message.RecipientType.TO, address);
            
            // -- set the subject line (time stamp)
            Calendar cal = Calendar.getInstance();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HH:mm:ss").format(cal.getTime());
            msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSentDate(new Date());
                        
            // -- set the message text
            msg.setText("Here is your new password: Adlwod332d");
            msg.setHeader("XPriority", "1");
            
            // -- send the message
            Transport.send(msg);
            
            System.out.println("Mail has been sent successfully");
        } catch (MessagingException e) {
            System.out.println("Unable to send an email" + e);
        }
    }
}
