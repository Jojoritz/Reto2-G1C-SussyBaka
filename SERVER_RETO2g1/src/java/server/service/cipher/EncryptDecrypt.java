/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.service.cipher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import server.exception.EncriptionException;

/**
 *
 * @author Henri
 */
public class EncryptDecrypt {

    private static final String KEY = "[g#>3!tx9Xr\\eeKt";
    private static final byte[] SALT = String.valueOf(".....").getBytes();
    private static final String CREDENTIALS = "credentials.dat";
    private static final String PRIVATE = "Private.key";
    private static final String PUBLIC = "Public.key";

    /**
     * Get password for the email decrypt a encrypted file
     *
     * @return Decrypted String
     * @throws EncriptionException When something went wrong when trying to
     * decrypt
     */
    public static String getPassword() throws EncriptionException {
        KeySpec keySpec;
        SecretKeyFactory secretKeyFactory;
        try {
            // Read file
            byte[] fileContent = fileReader(CREDENTIALS);

            // Generate secret key with PBKDF2WithHmacSHA1
            keySpec = new PBEKeySpec(KEY.toCharArray(), SALT, 65536, 128);
            secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] key = secretKeyFactory.generateSecret(keySpec).getEncoded();
            SecretKey privateKey = new SecretKeySpec(key, "AES");

            // Init cipher in Decrypt mode with AES/CBC/PKCS5Padding
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParam = new IvParameterSpec(Arrays.copyOfRange(fileContent, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, privateKey, ivParam);

            // Get decrypted message in a byte array
            byte[] decodedMessage = cipher.doFinal(Arrays.copyOfRange(fileContent, 16, fileContent.length));

            // Return decrypted Message
            return new String(decodedMessage);
        } catch (Exception e) {
            throw new EncriptionException(e.getMessage());
        }
    }

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
     * Decrypt text with {@code RSA} in mode {@code ECB} with padding
     * {@code PKCS1Padding} asymmetric
     *
     * @param mensaje The message to decrypt
     * @return The message decrypted
     * @throws EncriptionException When something went wrong when trying to
     * decrypt
     */
    public static String descifrarTextoAsimetrico(String mensaje) throws EncriptionException {
        byte[] decodedMessage;
        try {
            // Read public key from file
            byte fileKey[] = fileReader(PRIVATE);

            // Generate KeyFactory with RSA
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(fileKey);
            PrivateKey privateKey = keyFactory.generatePrivate(pKCS8EncodedKeySpec);

            // Decrypt the text
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decodedMessage = cipher.doFinal(Base64.getDecoder().decode(mensaje));
            return new String(decodedMessage);
        } catch (Exception e) {
            throw new EncriptionException(e.getMessage());
        }
    }

    /**
     * Returns the file content in bytes
     *
     * @param path Path to the file
     * @return Byte array with the content of the file
     */
    private static byte[] fileReader(String path) throws IOException {
        try {
            File file = new File(path);
            byte ret[] = Files.readAllBytes(file.toPath());
            return ret;

        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

}
