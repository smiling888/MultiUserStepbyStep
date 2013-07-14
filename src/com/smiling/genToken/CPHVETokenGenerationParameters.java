package com.smiling.genToken;

import java.util.Arrays;

import org.bouncycastle.crypto.CipherParameters;

import com.smiling.setup.CPHVESecretKeyParameters;

/**
 * @author Smiling
 * @version 2013-6-5 下午3:16:36 TODO
 */
public class CPHVETokenGenerationParameters implements CipherParameters {
	private CPHVESecretKeyParameters sk;
	private int[] tokenPattern;
	private boolean hasStar;

	public CPHVETokenGenerationParameters(CPHVESecretKeyParameters sk,
			int[] tokenPattern) {
		this.sk = sk;
		this.tokenPattern = tokenPattern;
		int n = tokenPattern.length;
		for (int i = 0; i < n; i++)
			if (tokenPattern[i] < 0)
				throw new IllegalArgumentException("tokenPattern has Star ");
	}

	public CPHVESecretKeyParameters getSk() {
		return sk;
	}

	public int[] getTokenPattern() {
		return Arrays.copyOf(tokenPattern, tokenPattern.length);
	}

	public int getTokenPatternAt(int index) {
		return tokenPattern[index];
	}

	public int getLength() {
		return tokenPattern.length;
	}

}
