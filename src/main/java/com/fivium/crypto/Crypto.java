package com.fivium.crypto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

/**
 * Date : 27-04-2017 Created by Fivium
 *
 */
public class Crypto {
	public Cipher cipher;

	public Crypto() {
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public PrivateKey getPrivate(byte[] keyBytes) throws Exception {
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePrivate(spec);
	}

	public PublicKey getPublic(byte[] keyBytes) throws Exception {
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}

	public String encryptString(String msg, PublicKey key) {
		try {
			this.cipher.init(Cipher.ENCRYPT_MODE, key);
			this.outputFile(new File("C:\\Arpit\\Dump\\output.txt"),
					Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8"))));
			return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return msg;

	}

	public byte[] getFileInBytes(File f) throws IOException {
		FileInputStream fis = new FileInputStream(f);
		byte[] fbytes = new byte[(int) f.length()];
		fis.read(fbytes);
		fis.close();
		return fbytes;
	}

	public void decryptString(byte[] msg, PrivateKey key) {
		try {
			this.cipher.init(Cipher.DECRYPT_MODE, key);
			System.out.println(new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8"));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public void outputFile(File output, String encrytedData) {
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("C:\\Arpit\\Dump\\output.txt"), "utf-8"))) {
			writer.write(encrytedData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		KeyGen keyGen = new KeyGen(1024);
		Crypto ac = new Crypto();
		PrivateKey privateKey;
		try {
			privateKey = ac.getPrivate(keyGen.getaPrivateKey());
			PublicKey publicKey = ac.getPublic(keyGen.getaPublicKey());
			String encryptedmsg = ac.encryptString("Hello", publicKey);
			ac.decryptString(ac.getFileInBytes(new File("C:\\Arpit\\Dump\\output.txt")), privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
