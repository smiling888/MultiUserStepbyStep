package com.smiling.genToken;

import it.unisa.dia.gas.jpbc.Element;

import java.io.Serializable;
import java.util.Arrays;

import org.bouncycastle.crypto.CipherParameters;

import com.smiling.setup.CPHVEParameters;

/**
 * @author Smiling
 * @version 2013-6-5 下午3:00:02 TODO
 */
// PairingPreProcessing Compute the pairing where the second argument is in2
public class CPHVETokenParameters implements CipherParameters, Serializable {
	private CPHVEParameters parameters;
	private Element[] Y, L;

	public CPHVETokenParameters(CPHVEParameters parameters, Element[] Y,
			Element[] L) {
		this.parameters = parameters;
		this.Y = Arrays.copyOf(Y, Y.length);
		this.L = Arrays.copyOf(L, L.length);

	}

	public CPHVEParameters getParameters() {
		return parameters;
	}

	public Element[] getYs() {
		return Arrays.copyOf(Y, Y.length);
	}

	public Element[] getLs() {
		return Arrays.copyOf(L, L.length);
	}

	public Element getYAt(int index) {
		return Y[index];
	}

	public Element getLAt(int index) {
		return L[index];
	}

}
