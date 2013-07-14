package com.smiling.encrypt;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPow;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import com.smiling.setup.CPHVEPublicKeyParameters;

/**
 * @author Smiling
 * @version 2013-6-4 下午10:08:12 TODO
 */
public class CPHVEEncrptEngine {
	private CPHVEEncryptionGenerationParameters param;
	private int[] attribute;

	public void init(CPHVEEncryptionGenerationParameters param) {
		this.param = param;
		this.attribute = this.param.getAttribute();
		if (this.attribute == null)
			throw new IllegalArgumentException("attribute cannot be null.");
		int n = this.param.getPublicKey().getParameters().getN();
		if (attribute.length != n)
			throw new IllegalArgumentException("attribute length not valid.");
	}

	public CPHVECtParameters generateCT() {
		CPHVEPublicKeyParameters publicKey = this.param.getPublicKey();
		if (param.isAllStar())
			throw new IllegalArgumentException(
					"attritbue could not be all star.");
		Pairing pairing = PairingFactory.getPairing(publicKey.getParameters()
				.getCurveParameters());

		int n = publicKey.getParameters().getN();
		int numNonStar = this.param.getNumNonStar();

		// a_i
		Element[] ai = new Element[numNonStar];
		Element sum = pairing.getZr().newElement().setToZero();

		for (int i = 0; i < numNonStar - 1; i++) {
			ai[i] = pairing.getZr().newElement().setToRandom();
			sum.add(ai[i]);
		}
		ai[numNonStar - 1] = sum.negate();

		// Generate CT X and W
		ElementPow g1 = publicKey.getParameters().getElementPowG1();
		Element[] X = new Element[n];
		Element[] W = new Element[n];

		for (int i = 0, j = 0; i < n; i++) {
			if (this.param.getAttributeAt(i) < 0) {
				X[i] = null;
				W[i] = null;
			} else {
				X[i] = publicKey.getElementPowTAt(i,
						this.param.getAttributeAt(i)).powZn(ai[j]);
				//System.out.println(X[i]);
				W[i] = publicKey.getElementPowVAt(i,
						this.param.getAttributeAt(i)).powZn(ai[j]);
				j++;
			}
		}

		return new CPHVECtParameters( attribute, X, W);
	}
}
