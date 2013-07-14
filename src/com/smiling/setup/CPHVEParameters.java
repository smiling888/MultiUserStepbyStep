package com.smiling.setup;

import it.unisa.dia.gas.jpbc.CurveParameters;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.ElementPow;
import it.unisa.dia.gas.jpbc.ElementPowPreProcessing;

import java.io.Serializable;
import java.util.Arrays;

import org.bouncycastle.crypto.CipherParameters;

/**
 * @author Smiling
 * @version 2013-6-4 下午7:42:30 TODO
 */
// 参考HVEIP08Parameters
public class CPHVEParameters implements CipherParameters, Serializable {
	private CurveParameters curveParams;
	private Element g1;
	private Element g2;// Add
	private int[] attributeLengths;

	private ElementPowPreProcessing powG1;
	private ElementPowPreProcessing powG2;// Add
	private int[] attributeLengthsInBytes;
	private int[] attributeNums;
	private int n;
	private int attributesLengthInBytes;

	private boolean preProcessed = false;

	// 构造函数
	public CPHVEParameters() {
// for XMLEncoder
	}

	public CPHVEParameters(CurveParameters curveParams, Element g1, Element g2,
			int[] attributeLengths) {
		this.curveParams = curveParams;
		this.g1 = g1.getImmutable();
		this.g2 = g2.getImmutable();
		this.n = attributeLengths.length;// 属性向量元素的个数
		this.attributeLengths = Arrays.copyOf(attributeLengths,
				attributeLengths.length);// 各个属性的长度

		this.attributesLengthInBytes = 0;
		this.attributeLengthsInBytes = new int[n];
		this.attributeNums = new int[n];
		for (int i = 0; i < attributeLengths.length; i++) {
			int attributeLength = attributeLengths[i];

			// Optimize this...
			attributeLengthsInBytes[i] = attributeLength / 8 + 1;// 每一个属性的字节数
			attributesLengthInBytes += attributeLengthsInBytes[i];// 所有属性的字节数

			attributeNums[i] = (int) Math.pow(2, attributeLength);// 每一个属性最大值，
		}
	}

	public CurveParameters getCurveParameters() {
		return this.curveParams;
	}

	public Element getG1() {
		return this.g1;
	}

	public Element getG2() {// Added
		return this.g2;
	}

	public ElementPow getElementPowG1() {
		return (preProcessed) ? powG1 : g1;
	}

	public ElementPow getElementPowG2() {
		return (preProcessed) ? powG2 : g2;
	}

	public int getN() {
		return n;
	}

	public int[] getAttributeLengths() {
		return Arrays.copyOf(attributeLengths, attributeLengths.length);
	}

	public int getAttributesLengthInBytes() {
		return attributesLengthInBytes;
	}

	public int getAttributeLengthInBytesAt(int index) {
		return attributeLengthsInBytes[index];
	}

	// 每一维属性的个数
	public int getAttributeNumAt(int index) {
		return attributeNums[index];
	}

	public void preProcess() {
		if (preProcessed)
			return;

		this.powG1 = g1.pow();
		this.powG2 = g2.pow();
		this.preProcessed = true;
	}

	public boolean isPreProcessed() {
		return preProcessed;
	}
}
