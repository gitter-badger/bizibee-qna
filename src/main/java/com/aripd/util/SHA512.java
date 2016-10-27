package com.aripd.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.log4j.Logger;

public class SHA512 {

    static final Logger LOG = Logger.getLogger(SHA512.class.getName());

    public static void main(String args[]) {
        String password = "password";
        String salt = "salt";

        if ((args.length == 1) && (args[0].length() > 0)) {
            password = args[0];
        }
        System.out.println("Password: " + password + " in SHA512 is:");
        System.out.println(hashText(password));
        System.out.println("-----");
        byte[] bytes = hashPassword(password.toCharArray(), salt.getBytes(StandardCharsets.UTF_8), 65536, 256);
        String string = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(string);
        System.out.println("-----");
        System.out.println("Password+Salt: " + password + salt + " in SHA512 is:");
        System.out.println(hashText(password + salt));
    }

    /**
     * Java implementation of PBKDF2 (Password-Based Key Derivation Function 2)
     *
     * @param password char[]
     * @param salt byte[]
     * @param iterations int
     * @param keyLength int
     * @return byte[]
     */
    public static byte[] hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            SecretKey key = skf.generateSecret(spec);
            byte[] res = key.getEncoded();
            return res;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertByteToHex(byte data[]) {
        StringBuilder hexData = new StringBuilder();
        for (int byteIndex = 0; byteIndex < data.length; byteIndex++) {
            //hexData.append(Integer.toString((data[byteIndex] & 0xff) + 0x100, 16).substring(1));
            String hex = Integer.toHexString(0xff & data[byteIndex]);
            if (hex.length() == 1) {
                hexData.append('0');
            }
            hexData.append(hex);
        }

        return hexData.toString();
    }

    public static String hashText(String textToHash) {
        try {
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            sha512.update(textToHash.getBytes());
            return convertByteToHex(sha512.digest());
        } catch (NoSuchAlgorithmException ex) {
            LOG.error(ex.getMessage());
        }
        return null;
    }
}
