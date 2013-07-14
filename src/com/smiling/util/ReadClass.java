package com.smiling.util;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * @author Smiling
 * @version 2013-6-16 下午11:12:52 TODO
 */
public class ReadClass {
	private ObjectInputStream ois;
	private File f;
	private InputStream in;
	private String path;

	public ReadClass(String path) {
		this.path = path;
		f = new File(path);
	}

	public Object readObject() throws IOException, ClassNotFoundException {
		if (!f.exists()) {
			System.out.println("file"+path+" is not exist");
			return null;
		}

		this.in = new FileInputStream(f);
		this.ois = new ObjectInputStream(in);
		return ois.readObject();
	}

	public Object readObjectXMLDecoder() throws IOException,
			ClassNotFoundException {
		XMLDecoder d = new XMLDecoder(new BufferedInputStream(
				new FileInputStream(path)));
		Object result = d.readObject();
		return result;
	}
}
