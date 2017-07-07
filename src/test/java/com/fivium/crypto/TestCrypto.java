package com.fivium.crypto;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

import org.junit.Test;

public class TestCrypto {
	
	@Test
	public void testCrypto(){
		KeyGen keyGen = new KeyGen(1024);
		Crypto ac = new Crypto();
		PrivateKey privateKey;
		try {
			privateKey = ac.getPrivate(keyGen.getaPrivateKey());
			PublicKey publicKey = ac.getPublic(keyGen.getaPublicKey());
			ac.encryptString("The Quick Brown Fox Jumped Over The Lazy Dog.", publicKey);
			assertEquals("The Quick Brown Fox Jumped Over The Lazy Dog.", ac.decryptString(ac.getFileInBytes(new File(System.getProperty("user.home")+"\\output.txt")), privateKey));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
