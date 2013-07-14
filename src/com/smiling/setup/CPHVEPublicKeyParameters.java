package com.smiling.setup;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Smiling
 * @version 2013-6-4 下午8:48:34 TODO
 */
public class CPHVEPublicKeyParameters extends CPHVEKeyParameters {

	private List<List<Element>> T, V;

	private List<List<ElementPow>> preT, preV;
	private boolean preProcessed = false;

	public CPHVEPublicKeyParameters(CPHVEParameters parameters,
			List<List<Element>> T, List<List<Element>> V) {
		super(false, parameters);
		this.T = T;
		this.V = V;
		preProcess();
	}

	public Element getTAt(int row, int col) {
		return T.get(row).get(col);
	}

	public Element getVAt(int row, int col) {
		return V.get(row).get(col);
	}

	public ElementPow getElementPowTAt(int row, int col) {
		return preProcessed ? preT.get(row).get(col) : T.get(row).get(col);
	}

	public ElementPow getElementPowVAt(int row, int col) {
		return preProcessed ? preV.get(row).get(col) : V.get(row).get(col);
	}

	public void preProcess() {
		if (preProcessed)
			return;

		getParameters().preProcess();
		int n = getParameters().getN();
		preT = new ArrayList<List<ElementPow>>(n);
		preV = new ArrayList<List<ElementPow>>(n);

		for (int i = 0; i < n; i++) {
			int attributeNum = getParameters().getAttributeNumAt(i);

			List<ElementPow> listT = new ArrayList<ElementPow>();
			List<ElementPow> listV = new ArrayList<ElementPow>();

			for (int j = 0; j < attributeNum; j++) {
				listT.add(getTAt(i, j).pow());
				listV.add(getVAt(i, j).pow());
			}
			preT.add(listT);
			preV.add(listV);
		}
		preProcessed = true;
	}

	public boolean isPreProcessed() {
		return preProcessed;
	}
}
