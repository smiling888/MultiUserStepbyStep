package com.smiling.setup;

import java.security.SecureRandom;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
import it.unisa.dia.gas.jpbc.CurveParameters;

/**
 * @author Smiling
 * @version 2013-6-4 下午10:59:34 TODO
 */
// call  setup method
// input vector parameters 
// output KeyPair
public class CPHVESetup {
	// 输入每一维向量的比特数 如 {2,3,4,5} 则对应公钥有{4,8,16 ,32}
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
	
}
