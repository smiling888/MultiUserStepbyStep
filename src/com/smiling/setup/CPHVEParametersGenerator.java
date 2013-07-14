package com.smiling.setup;

/** 
 * @author Smiling 
 * @version 2013-6-4 下午11:07:03 
 * TODO
 */
import it.unisa.dia.gas.jpbc.CurveParameters;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.util.Arrays;

public class CPHVEParametersGenerator {
	private CurveParameters curveParams;
	private int[] attributeLengths;

	private Pairing pairing;

	public void init(CurveParameters curveParams, int... attributeLengths) {
		this.curveParams = curveParams;
		this.attributeLengths = Arrays.copyOf(attributeLengths,
				attributeLengths.length);

		this.pairing = PairingFactory.getPairing(curveParams);

	}

	// 默认属性长度为1
	public void init(int n, CurveParameters curveParams) {
		this.init(n, 1, curveParams);
	}

	public void init(int n, int numBitsPerAttribute, CurveParameters curveParams) {
		this.curveParams = curveParams;
		this.attributeLengths = new int[n];
		for (int i = 0; i < attributeLengths.length; i++) {
			attributeLengths[i] = numBitsPerAttribute;
		}
		this.pairing = PairingFactory.getPairing(curveParams);
	}

	public CPHVEParameters generateParameters() {
		Element g1 = pairing.getG1().newElement().setToRandom();
		Element g2 = pairing.getG2().newElement().setToRandom();
		// System.out.println("HVEIP08Parameters"+g);
		return new CPHVEParameters(curveParams, g1.getImmutable(),
				g2.getImmutable(), attributeLengths);
	}
}
