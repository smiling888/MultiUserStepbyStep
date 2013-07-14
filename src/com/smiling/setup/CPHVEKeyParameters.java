package com.smiling.setup;

import java.io.Serializable;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;

/**
 * @author Smiling
 * @version 2013-6-4 下午8:51:18 TODO
 */
public class CPHVEKeyParameters extends AsymmetricKeyParameter {
	private CPHVEParameters parameters;

	

	public CPHVEKeyParameters(boolean privateKey, CPHVEParameters parameters) {
		super(privateKey);
		this.parameters = parameters;
	}

	public CPHVEParameters getParameters() {
		return parameters;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CPHVEKeyParameters))
			return false;
		CPHVEKeyParameters that = (CPHVEKeyParameters) o;

		if (parameters != null ? !parameters.equals(that.parameters)
				: that.parameters != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return parameters != null ? parameters.hashCode() : 0;
	}
}
