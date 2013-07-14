package com.smiling.encrypt;

import java.util.Arrays;

import org.bouncycastle.crypto.CipherParameters;

import com.smiling.setup.CPHVEPublicKeyParameters;

/**
 * @author Smiling
 * @version 2013-6-4 下午10:53:53 TODO
 */
public class CPHVEEncryptionGenerationParameters implements CipherParameters{
	private CPHVEPublicKeyParameters publicKey;
	private int[] attribute;
	private boolean allStar;
	private int numNonStar;

	public CPHVEEncryptionGenerationParameters(
			CPHVEPublicKeyParameters publicKey, int... attribute) {
		this.publicKey = publicKey;
		this.attribute = Arrays.copyOf(attribute, attribute.length);
		int numStar = 0;
		for (int i = 0; i < attribute.length; i++) {
			if (attribute[i] < 0)
				numStar++;
		}
		this.numNonStar = attribute.length - numStar;
		this.allStar = (numStar == attribute.length);
	}

	public CPHVEPublicKeyParameters getPublicKey() {
		return publicKey;
	}

	public int[] getAttribute() {
		return Arrays.copyOf(attribute, attribute.length);
	}

	public boolean isAllStar() {
		return allStar;
	}

	public int getNumNonStar() {
		return numNonStar;
	}

	public boolean isStarAt(int index) {
		return attribute[index] < 0;
	}

	public int getAttributeAt(int index) {
		return attribute[index];
	}
}
