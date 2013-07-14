package com.smiling.genToken;

import org.junit.runners.Parameterized.Parameters;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import com.smiling.setup.CPHVESecretKeyParameters;

/**
 * @author Smiling
 * @version 2013-6-5 下午3:16:51 TODO
 */
public class CPHVETokenGenerator {
	public int n;
	private Pairing pairing;
	private CPHVESecretKeyParameters sk;
	private CPHVETokenGenerationParameters parameters;
	private Element g2;

	public void init(CPHVETokenGenerationParameters parameters) {
		this.parameters = parameters;
		this.n = parameters.getSk().getParameters().getN();
		this.pairing = PairingFactory.getPairing(parameters.getSk()
				.getParameters().getCurveParameters());
		this.sk = parameters.getSk();
		this.g2 = sk.getParameters().getG2();
	}

	public CPHVETokenParameters generateToken() {
		Element[] Y = new Element[n];
		Element[] L = new Element[n];
		Element s = pairing.getZr().newRandomElement().getImmutable();
		for (int i = 0; i < n; i++) {
			Element si = pairing.getZr().newElement().setToRandom();
			Element sMinusSi = s.sub(si);

			int j = parameters.getTokenPatternAt(i);
			Y[i] = g2.powZn(sMinusSi.duplicate().mul(sk.getTAt(i, j)));
			L[i] = g2.powZn(si.duplicate().mul(sk.getVAt(i, j)));
		}
		if (0 < n )
			if (Y[2] == null)
				System.out.println("Y==null");
		return new CPHVETokenParameters(sk.getParameters(), Y, L);
	}
}
