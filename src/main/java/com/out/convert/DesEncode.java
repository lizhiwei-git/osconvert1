package com.out.convert;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DesEncode {
	public DesEncode() {
	}

    /**
     * 解密
     * @param src
     * @param password
     * @return
     */
	public static String decrypt(String src, String password) {
		byte[] decryResult = null;
		try {
			decryResult = decrypt(parseHexStr2Byte(src), password);
			String rs = new String(decryResult,"UTF-8") ;
			return rs ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}


	public static byte[] decrypt(byte[] src, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		SecureRandom random = new SecureRandom();
		DESKeySpec desKey = null;
		try {
			desKey = new DESKeySpec(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		return cipher.doFinal(src);
	}

   public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
