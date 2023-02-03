/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.logic;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import client.logic.exception.EncriptionException;
import com.google.common.io.ByteStreams;
import java.io.InputStream;
import static org.apache.poi.util.IOUtils.toByteArray;

/**
 *
 * @author Henri
 */
public class EncryptDecrypt {

    /**
     * Encrypt text with {@code RSA} in mode {@code ECB} with padding
     * {@code PKCS1Padding} asymmetric
     *
     * @param mensaje The message to encrypt
     * @return The message encrypted
     * @throws EncriptionException When something went wrong when trying to
     * encrypt
     */
    public static String cifrarTextoAsimetrico(String mensaje) throws EncriptionException {
        byte[] encodedMessage;
        try {
            // Read public key from file
            InputStream is = EncryptDecrypt.class.getResourceAsStream("Public.key");
            byte[] fileContent = toByteArray(is);
            byte fileKey[] = fileContent;

            // Generate KeyFactory with RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            // Cipher the text
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encodedMessage = cipher.doFinal(mensaje.getBytes());

            return Base64.getEncoder().encodeToString(encodedMessage);
        } catch (Exception e) {
            throw new EncriptionException(e.getMessage());
        }
    }

    

}
