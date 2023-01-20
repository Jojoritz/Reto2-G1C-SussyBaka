/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service.mail;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import static org.passay.AllowedCharacterRule.ERROR_CODE;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

/**
 * This class sends automatic mails to the users when password change requested
 *
 * @author ioritz
 */
public class MailSender {

    private static Properties mailProperties;
    private static String user;
    private static String password;
    private static String subject;
    private static String content;
    private static Message message;
    private static Session session;
    private static String randomPassword;
    private static Multipart multipart;
    private static MimeBodyPart mimeBodyPart;
    private static final Logger LOGGER = Logger.getLogger(MailSender.class.getName());
    
    public static String sendMail(String email) throws Exception {
        LOGGER.info("Preparing to send email, loading the data from the properties file");
        mailProperties = getPropertiesInfo();

        LOGGER.info("Preparing a session to send email");
        session = Session.getInstance(mailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);
            
            multipart = new MimeMultipart();
            randomPassword = randomPasswordGenerator();
            content = "Usted ha solicitado restaurar la contraseña, la nueva contraseña es " + randomPassword;
            
            mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html");
            multipart.addBodyPart(mimeBodyPart);
            
            message.setContent(multipart);
            
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.severe(e.getMessage());
            throw new Exception(e.getMessage());
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
            throw new Exception(ex.getMessage());
        }
        return randomPassword;
    }

    /**
     * A method to generate a random password
     *
     * @return a random password
     * @throws Exception if any exception sucedded
     */
    private static String randomPasswordGenerator() throws Exception {
        String randomPassword = null;     
        
        try {
            LOGGER.info("Generating a random password");
            PasswordGenerator gen = new PasswordGenerator();
            
            CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
            CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
            lowerCaseRule.setNumberOfCharacters(4);
            
            CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
            CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
            upperCaseRule.setNumberOfCharacters(1);
            
            CharacterData digitChars = EnglishCharacterData.Digit;
            CharacterRule digitRule = new CharacterRule(digitChars);
            digitRule.setNumberOfCharacters(3);
            
            CharacterData specialChars = new CharacterData() {
                @Override
                public String getErrorCode() {
                    return ERROR_CODE;
                }

                @Override
                public String getCharacters() {
                    return "!@#\\$%^&\\*\\?";
                }
            };   
            System.out.println(specialChars.getCharacters());
            CharacterRule specialCharRule = new CharacterRule(specialChars);
            specialCharRule.setNumberOfCharacters(1);
            
            randomPassword = gen.generatePassword(10, upperCaseRule, lowerCaseRule, digitRule, specialCharRule);
            
            
        } catch (Exception e) {
            LOGGER.info("An error happened when generating a random password");
            throw new Exception("Ane error happened when generating a random password");
        }
        return randomPassword;
    }
    
    private static Properties getPropertiesInfo() {
        Properties mailProperties = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("server.service.mail.mailConfigFile");

        mailProperties.put("mail.smtp.auth", Boolean.parseBoolean(rb.getString("mail.smtp.auth")));
        mailProperties.put("mail.smtp.starttls.enable", rb.getString("mail.smtp.starttls.enable"));
        mailProperties.put("mail.smtp.host", rb.getString("smtp_host"));
        mailProperties.put("mail.smtp.port", rb.getString("smtp_port"));
        mailProperties.put("mail.smtp.ssl.trust", rb.getString("smtp_host"));
        mailProperties.put("mail.imap.partialfetch", Boolean.parseBoolean(rb.getString("mail.imap.partialfetch")));

        user = rb.getString("user");
        password = rb.getString("password");
        subject = rb.getString("subject");

        return mailProperties;
    }
}
