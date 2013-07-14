package com.smiling.setup;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPow;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;

/**
 * @author Smiling
 * @version 2013-6-4 下午7:46:12 TODO
 */
public class CPHVEKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
	private CPHVEKeyGenerationParameters params;

	@Override
	public void init(KeyGenerationParameters param) {
		this.params = (CPHVEKeyGenerationParameters) param;

	}

	@Override
	public AsymmetricCipherKeyPair generateKeyPair() {
		CPHVEParameters parameters = params.getParams();
		parameters.preProcess();// 预处理

		Pairing pairing = PairingFactory.getPairing(parameters
				.getCurveParameters());
		// Element g1 = parameters.getG1();
		ElementPow powG1 = parameters.getElementPowG1();
		int n = parameters.getN();

		List<List<Element>> T = new ArrayList<List<Element>>(n);
		List<List<Element>> t = new ArrayList<List<Element>>(n);

		List<List<Element>> V = new ArrayList<List<Element>>(n);
		List<List<Element>> v = new ArrayList<List<Element>>(n);

		for (int i = 0; i < n; i++) {

			int howMany = parameters.getAttributeNumAt(i);// howmany
															// 表示属性向量的每一个元素有多少个值。
			List<Element> T_i = new ArrayList<Element>();
			List<Element> t_i = new ArrayList<Element>();

			List<Element> V_i = new ArrayList<Element>();
			List<Element> v_i = new ArrayList<Element>();

			for (int j = 0; j < howMany; j++) {// 元素的每一个值都对应一个公钥
				Element t_j = pairing.getZr().newElement().setToRandom();
				T_i.add(powG1.powZn(t_j.duplicate().invert()).getImmutable());// t_j
																				// --(t_j.duplicate().invert()
				t_i.add(t_j.getImmutable());

				Element v_j = pairing.getZr().newElement().setToRandom();
				V_i.add(powG1.powZn(v_j.duplicate().invert()).getImmutable());
				v_i.add(v_j.getImmutable());
			}

			T.add(T_i);
			t.add(t_i);

			V.add(V_i);
			v.add(v_i);
		}

		return new AsymmetricCipherKeyPair(new CPHVEPublicKeyParameters(
				parameters, T, V), new CPHVESecretKeyParameters(parameters, t,
				v));
	}

}
