package com.smiling.genToken;

import com.smiling.setup.CPHVESecretKeyParameters;

/**
 * @author Smiling
 * @version 2013-6-5 下午3:57:03 TODO
 */
public class CPHVEGenToken {

	public CPHVETokenParameters genToken(CPHVESecretKeyParameters sk,
			int[] tokenPattern) {
		CPHVETokenGenerator generator = new CPHVETokenGenerator();
		generator.init(new CPHVETokenGenerationParameters(sk, tokenPattern));
		CPHVETokenParameters token = generator.generateToken();
//		if (token.getYs() != null) {
//			System.out.println("null");
//			;
//		}
		return token;
	}
}
