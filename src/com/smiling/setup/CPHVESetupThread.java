package com.smiling.setup;

import it.unisa.dia.gas.jpbc.CurveParameters;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.security.SecureRandom;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;

/**
 * @author Smiling
 * @version 2013-6-17 下午5:22:00 TODO
 */
public class CPHVESetupThread implements Runnable {
	// 输入每一维向量的比特数 如 {2,3,4,5} 则对应公钥有{4,8,16 ,32}
	private int[] attributeLengths;

	public CPHVESetupThread(AsymmetricCipherKeyPair key,
			int... attributeLengths) {
		this.attributeLengths = attributeLengths;
	}

	public AsymmetricCipherKeyPair setup(int... attributeLengths) {

		CPHVEParameters cphveParamters = genCPHVEParameters(attributeLengths);
		CPHVEKeyPairGenerator generator = new CPHVEKeyPairGenerator();
		generator.init(new CPHVEKeyGenerationParameters(new SecureRandom(),
				cphveParamters));
		return generator.generateKeyPair();
	}

	public CPHVEParameters genCPHVEParameters(int... attributeLengths) {
		CPHVEParametersGenerator generator = new CPHVEParametersGenerator();
		CurveParameters curveParams = getCurveParameters();

		generator.init(curveParams, attributeLengths);
		return generator.generateParameters();
	}

	public CurveParameters getCurveParameters() {
		return PairingFactory.getInstance().loadCurveParameters(
				"d159.properties");
	}

	@Override
	public void run() {

	}
}
