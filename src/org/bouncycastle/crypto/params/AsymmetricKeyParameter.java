package org.bouncycastle.crypto.params;

import java.io.Serializable;

import org.bouncycastle.crypto.CipherParameters;

/**
 * @author Smiling
 * @version 2013-6-16 下午4:37:12 TODO
 */
public class AsymmetricKeyParameter implements CipherParameters, Serializable {
	boolean privateKey;

	public AsymmetricKeyParameter() {

	}

	public AsymmetricKeyParameter(boolean privateKey) {
		this.privateKey = privateKey;
	}

	public boolean isPrivate() {
		return privateKey;
	}
}
