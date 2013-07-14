package com.smiling.cphve;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;

import com.smiling.encrypt.CPHVECtParameters;
import com.smiling.encrypt.CPHVEEncrypt;
import com.smiling.genToken.CPHVEGenToken;
import com.smiling.genToken.CPHVETokenParameters;
import com.smiling.oneTest.CPHVETest;
import com.smiling.setup.CPHVEPublicKeyParameters;
import com.smiling.setup.CPHVESecretKeyParameters;
import com.smiling.setup.CPHVESetup;

/**
 * @author Smiling
 * @version 2013-6-4 下午11:21:07 TODO
 */
public class CphveTest2 {
	private CPHVEPublicKeyParameters pk;
	private CPHVESecretKeyParameters sk;

	public void setup(int... attributeLengths) {
		// setup input attributeLengths
		// output 公私钥
		CPHVESetup setup = new CPHVESetup();
		AsymmetricCipherKeyPair keyPair = setup.setup(attributeLengths);
		pk = (CPHVEPublicKeyParameters) keyPair.getPublic();
		sk = (CPHVESecretKeyParameters) keyPair.getPrivate();
	}

	public CPHVECtParameters encrypt(CPHVEPublicKeyParameters pk,
			int[] attribute) {
		return new CPHVEEncrypt().encrypt(pk, attribute);
	}

	public CPHVETokenParameters genToken(CPHVESecretKeyParameters sk,
			int[] tokenPattern) {
		return new CPHVEGenToken().genToken(sk, tokenPattern);
	}

	public boolean test(CPHVECtParameters CT, CPHVETokenParameters token) {
		return new CPHVETest().test(CT, token);
	}

	public static void main(String[] args) {
		CphveTest2 c = new CphveTest2();

		int[] attributeLengths = { 2, 3, 4, 5 };// 即{2^2,3^2,4^2，5^2}={4,8，16，32}
		c.setup(attributeLengths);
		// ecnrypt
		int[] attribute = { 3, 5, 6, 9 };
		CPHVECtParameters CT = c.encrypt(c.pk, attribute);

		// genToken
		int[] tokenPattern1 = { 3, 5, 6, 9 };
		int[] tokenPattern2 = { 3,1, 2, 9 };
		int[] tokenPattern3 = { 1, 1, 1, 1 };
		int[] tokenPattern4 = { 2, 4, 5, 2 };
		// test1
		CPHVETokenParameters token = c.genToken(c.sk, tokenPattern1);

	System.out.println(c.test(CT, token));

		// test 2
		token = c.genToken(c.sk, tokenPattern2);

		System.out.println(c.test(CT, token));

		// test 3
		token = c.genToken(c.sk, tokenPattern3);

		System.out.println(c.test(CT, token));

		// test 4
//		long startTime = System.currentTimeMillis();
		token = c.genToken(c.sk, tokenPattern4);
//		long TokenTime = System.currentTimeMillis();
//		System.out.println(TokenTime - startTime+"ms");

		System.out.println(c.test(CT, token));
//		long TestTime = System.currentTimeMillis();
//		System.out.println(TestTime - TokenTime+"ms");

	}
}
