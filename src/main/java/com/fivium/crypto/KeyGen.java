package com.fivium.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


/**
 * Date : 27-04-2017 
 * Created by Arpit Arora
 *
 */
public class KeyGen {

	private KeyPairGenerator aGenerator;
	private KeyPair aKeyPair;
	private byte[] aPrivateKey;
	private byte[] aPublicKey;

	public KeyGen(int length) {
		try {
			this.aGenerator = KeyPairGenerator.getInstance("RSA");
			this.aGenerator.initialize(length, new SecureRandom());
			createKeys();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	public void createKeys() {
		this.aKeyPair = this.aGenerator.generateKeyPair();
		this.aPrivateKey = aKeyPair.getPrivate().getEncoded();
		this.aPublicKey = aKeyPair.getPublic().getEncoded();
	}

	public byte[] getaPrivateKey() {
		return aPrivateKey;
	}

	public void setaPrivateKey(byte[] aPrivateKey) {
		this.aPrivateKey = aPrivateKey;
	}

	public byte[] getaPublicKey() {
		return aPublicKey;
	}

	public void setaPublicKey(byte[] aPublicKey) {
		this.aPublicKey = aPublicKey;
	}


	
}
