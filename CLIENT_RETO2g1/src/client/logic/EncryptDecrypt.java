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

/**
 *
 * @author Henri
 */
public class EncryptDecrypt {

    private static final String PUBLIC = "Public.key";

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
            byte fileKey[] = fileReader(PUBLIC);

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

    /**
     * Returns the file content in bytes
     *
     * @param path Path to the file
     * @return Byte array with the content of the file
     * @throws IOException if any error happened while reading a file
     */
    private static byte[] fileReader(String path) throws IOException {
        try {
            File file = new File(path);
            String test = file.getAbsolutePath();
            byte ret[] = Files.readAllBytes(file.toPath());
            return ret;

        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

}
