/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service.mail;

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ejb.Stateless;
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
import server.service.cipher.EncryptDecrypt;

/**
 * This class sends automatic mails to the users when password change requested
 *
 * @author ioritz
 */
@Stateless
public class MailSender {

    private static Properties mailProperties;
    private static String mail;
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
    private static final ResourceBundle RB = ResourceBundle.getBundle("server.service.mail.mailConfigFile");

    /**
     * This method will send a email to the user who needs to reset his password
     *
     * @param email Email address where it will receive the mail
     * @return Returns the generated random password to later use
     * @throws java.lang.Exception Throws exception if there is an error when
     * creating the email message or other
     */
    public static synchronized String sendMail(String email) throws Exception {
        try {
            LOGGER.info("Loading the data from properties file....");
            mailProperties = getPropertiesInfo();

            LOGGER.info("Preparing email....");
            session = Session.getInstance(mailProperties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mail, password);
                }
            });

            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail, user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(subject);

            multipart = new MimeMultipart();
            randomPassword = randomPasswordGenerator();
            content = RB.getString("message") + randomPassword;

            mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(content, "text/html");
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
            LOGGER.info("Email sent to the user.....");
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
     * Method to generate a random password
     *
     * @return a random password
     * @throws Exception if any exception is thrown
     */
    private static String randomPasswordGenerator() throws Exception {
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
                    return "!@#$%^&*?";
                }
            };
            CharacterRule specialCharRule = new CharacterRule(specialChars);
            specialCharRule.setNumberOfCharacters(1);

            randomPassword = gen.generatePassword(10, upperCaseRule, lowerCaseRule, digitRule, specialCharRule);

        } catch (Exception e) {
            LOGGER.info("An error happened when generating a random password");
            throw new Exception("An error happened when generating a random password");
        }
        return randomPassword;
    }

    /**
     * This method is a singleton to create and set the information for the
     * mailProperties,
     *
     * @return Returns a properties object with all the mail that filled up
     * @throws If there is any exception thrown
     */
    private static synchronized Properties getPropertiesInfo() throws Exception {
        try {

            if (mailProperties != null) {
                return mailProperties;
            }

            mailProperties = new Properties();
            mailProperties.put("mail.smtp.auth", Boolean.parseBoolean(RB.getString("mail.smtp.auth")));
            mailProperties.put("mail.smtp.starttls.enable", RB.getString("mail.smtp.starttls.enable"));
            mailProperties.put("mail.smtp.host", RB.getString("smtp_host"));
            mailProperties.put("mail.smtp.port", RB.getString("smtp_port"));
            mailProperties.put("mail.smtp.ssl.trust", RB.getString("smtp_host"));
            mailProperties.put("mail.imap.partialfetch", Boolean.parseBoolean(RB.getString("mail.imap.partialfetch")));

            mail = RB.getString("mail");
            user = RB.getString("user");
            password = EncryptDecrypt.getPassword();
            subject = RB.getString("subject");

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return mailProperties;
    }

}
