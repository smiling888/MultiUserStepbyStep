package com.smiling.encrypt;

import it.unisa.dia.gas.crypto.jpbc.utils.ElementUtils;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.jpbc.PairingPreProcessing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.Serializable;
import java.util.Arrays;

import org.bouncycastle.crypto.CipherParameters;

import com.smiling.setup.CPHVEParameters;

/**
 * @author Smiling
 * @version 2013-6-4 下午10:08:46 TODO
 */
// 对应HVEIP08SecretKeyParameters 不需要K 不能所有的维数都是*
// deleted  CPHVEParameters
public class CPHVECtParameters implements CipherParameters,Serializable {
	private int[] attributePattern;
	
	private Element[] X, W;
	private boolean allStar;
	private PairingPreProcessing[] preX, preW;
	private boolean preProcessed = false;

	public CPHVECtParameters( int[] attribute,
			Element[] X, Element[] W) {
		
		this.attributePattern = new int[attribute.length];
		for (int i = 0; i < attribute.length; i++) {
			if (attribute[i] < 0)
				attributePattern[i] = attribute[i];
			else
				attributePattern[i] = 0;
		}
		this.X = ElementUtils.cloneImmutable(X);
		this.W = ElementUtils.cloneImmutable(W);
		this.allStar = false;
//		preProcess();
	}

	public boolean isStar(int index) {
		return attributePattern[index] < 0;
	}

	public boolean isAllStar() {
		return allStar;
	}

	public Element getXAt(int index) {
		return X[index];
	}

	public Element getWAt(int index) {
		return W[index];
	}

	public PairingPreProcessing getPreXAt(int index) {
		return preX[index];
	}

	public PairingPreProcessing getPreWAt(int index) {
		return preW[index];
	}

	public int[] getAttributePattern() {
		return Arrays.copyOf(attributePattern, attributePattern.length);
	}

//	public void preProcess() {
//		if (preProcessed)
//			return;
//		Pairing pairing = PairingFactory.getPairing(parameters
//				.getCurveParameters());
//		int n = parameters.getN();
//
//		preX = new PairingPreProcessing[n];
//		preW = new PairingPreProcessing[n];
//		for (int i = 0; i < n; i++) {
//			Element X = getXAt(i);
//			Element W = getWAt(i);
//			preX[i] = X != null ? pairing.pairing(X) : null;
//			preW[i] = W != null ? pairing.pairing(W) : null;
//		}
//		preProcessed = true;
//	}

	public boolean isPreProcessed() {
		return preProcessed;
	}

	public Element[] getXs() {
		return Arrays.copyOf(X, X.length);
	}

	public Element[] getWs() {
		return Arrays.copyOf(W, W.length);
	}

	// public CPHVEParameters getParameters() {
	// return parameters;
	// }
}
