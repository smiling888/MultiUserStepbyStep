package com.smiling.setup;

import java.security.SecureRandom;

import org.bouncycastle.crypto.KeyGenerationParameters;

/**
 * @author Smiling
 * @version 2013-6-4 下午7:49:01 TODO
 */
public class CPHVEKeyGenerationParameters extends KeyGenerationParameters {
	private CPHVEParameters params;

	public CPHVEKeyGenerationParameters(SecureRandom random,
			CPHVEParameters params) {
		super(random, params.getG1().getField().getLengthInBytes());
		this.params = params;
	}

	public CPHVEParameters getParams() {
		return params;
	}
}
