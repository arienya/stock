package com.bimt.thesisquery.common.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by yixiaolin on 4/6/16.
 */
public class IDWrapper {

    public String encode(String originalID) {
        try {
            return toHexString(encryptCipher.doFinal(originalID.getBytes()));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String decode(String wrappedID) {
        try {
            return new String(decryptCipher.doFinal(fromHexString(wrappedID)));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";

    private static byte[] KEY = "b327098jhk7pjjui".getBytes();
    private static Cipher encryptCipher = null;
    private static Cipher decryptCipher = null;

    static {
        try {
            encryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
            encryptCipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY, KEY_ALGORITHM));
            decryptCipher = Cipher.getInstance(CIPHER_ALGORITHM);
            decryptCipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(KEY, KEY_ALGORITHM));
        } catch (Exception e) {
        }
    }

    private static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length);
        String sTemp;
        for (int i = 0; i < b.length; i++) {
            sTemp = Integer.toHexString(0xFF & b[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    private static byte[] fromHexString(String hex) {
        byte[] bts = new byte[hex.length() / 2];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bts;
    }

    public static void main(String[] args) {
        IDWrapper idWrapper = new IDWrapper();
        System.out.println(idWrapper.decode("7F6AA119481AD6BF39B69587F35CD485"));
        System.out.println(idWrapper.decode("DD539243E22540EB24E18194CD8B1577"));
        System.out.println(idWrapper.decode("D9B6A7917AB5D33DFC91C71C2835BB39"));
        System.out.println(idWrapper.encode("57947d5ffc5f5432564c9268"));
        System.out.println(idWrapper.decode("8C565505C38077C22A7D9A95A4266F2F777672CF9DCB2E8E242D0F36837F8080"));
    }
}
