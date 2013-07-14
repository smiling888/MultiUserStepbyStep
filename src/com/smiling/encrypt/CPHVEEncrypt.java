package com.smiling.encrypt;

import com.smiling.setup.CPHVEPublicKeyParameters;

/**
 * @author Smiling
 * @version 2013-6-5 下午3:12:06 TODO
 */
public class CPHVEEncrypt {
	public CPHVECtParameters encrypt(CPHVEPublicKeyParameters pk,
			int... attribute) {
		CPHVEEncrptEngine engine = new CPHVEEncrptEngine();
		engine.init(new CPHVEEncryptionGenerationParameters(pk, attribute));
		return engine.generateCT();
	}
}
