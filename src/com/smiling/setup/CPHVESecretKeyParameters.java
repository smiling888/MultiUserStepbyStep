package com.smiling.setup;

import it.unisa.dia.gas.jpbc.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Smiling
 * @version 2013-6-4 下午9:38:37 TODO
 */
// 对应listV
public class CPHVESecretKeyParameters extends CPHVEKeyParameters {
	private CPHVEParameters parameters;
	private List<List<Element>> t, v;
	private List<List<Element>> pret, prev;
	private boolean preProcessed = false;

	public CPHVESecretKeyParameters(CPHVEParameters parameters,
			List<List<Element>> t, List<List<Element>> v) {
		super(true, parameters);
		this.parameters = parameters;
		this.t = t;
		this.v = v;
	}

	public Element getTAt(int row, int col) {
		return t.get(row).get(col);
	}

	public Element getVAt(int row, int col) {
		return v.get(row).get(col);
	}

	public Element getPreTAt(int row, int col) {
		return preProcessed ? pret.get(row).get(col) : t.get(row).get(col);
	}

	public Element getPreVAt(int row, int col) {
		return preProcessed ? prev.get(row).get(col) : v.get(row).get(col);
	}

	public void preProcess() {
		if (preProcessed)
			return;
		getParameters().preProcess();

		int n = getParameters().getN();
		pret = new ArrayList<List<Element>>();
		prev = new ArrayList<List<Element>>();
		
		
		for (int i = 0; i < n; i++) {
			int attributeNum = getParameters().getAttributeNumAt(i);

			List<Element> listt = new ArrayList<Element>();
			List<Element> listv = new ArrayList<Element>();
			for (int j = 0; j < attributeNum; j++) {
				listt.add(getTAt(i, j));
				listv.add(getVAt(i, j));// 元getVAt(i, j).invert() ---
										// 改后getVAt(i,j)
			}
			pret.add(listt);
			prev.add(listv);
		}
		preProcessed = true;
	}

	public boolean isPreProcessed() {
		return preProcessed;
	}

}
