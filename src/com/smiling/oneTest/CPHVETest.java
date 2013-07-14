package com.smiling.oneTest;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import com.smiling.encrypt.CPHVECtParameters;
import com.smiling.genToken.CPHVETokenParameters;

/**
 * @author Smiling
 * @version 2013-5-16 上午10:05:56 TODO
 */
public class CPHVETest {
	// 搜索所有索引中的文件

	//
	public boolean test(CPHVECtParameters CT, CPHVETokenParameters token) {
		Pairing pairing = PairingFactory.getPairing(token.getParameters()
				.getCurveParameters());
		Element result = pairing.getGT().newOneElement();

		int n = token.getParameters().getN();
		for (int i = 0; i < n; i++) {
			boolean isStar = CT.isStar(i);
			boolean star = !isStar;
			if (star) {

				result.mul(pairing.pairing(CT.getXAt(i), token.getYAt(i))).mul(
						pairing.pairing(CT.getWAt(i), token.getLAt(i)));
			}
		}
		// if (CT.isPreProcessed()) {
		// for (int i = 0; i < n; i++) {
		// if (!CT.isStar(i)) {
		// result.mul(CT.getPreXAt(i).pairing(token.getYAt(i))).mul(
		// CT.getWAt(i).powZn(token.getLAt(i)));
		// }
		// }
		// } else {
		// for (int i = 0; i < n; i++) {
		// if (!CT.isStar(i)) {
		// result.mul(pairing.pairing(CT.getXAt(i), token.getYAt(i)))
		// .mul(pairing.pairing(CT.getWAt(i), token.getLAt(i)));
		// }
		// }
		// }

		return result.isOne() ? true : false;
	}
}
