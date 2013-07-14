package com.smiling.util;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author Smiling
 * @version 2013-5-23 上午9:21:49 TODO
 */
public class SaveClass {
	private File f;
	private ObjectOutputStream oos;
	private String path;
	private OutputStream out;

	public SaveClass(String filePath) throws IOException {
		this.path = filePath;
		f = new File(filePath);

	}

	public void writeObject(Object obj) throws IOException {
		int i = 0;
		while (f.exists()) {
			System.out.println(path + i + " exist!update path");
			f = new File(path + i);
			i++;
		}
		f.createNewFile();
		this.out = new FileOutputStream(f);
		this.oos = new ObjectOutputStream(out);
		this.oos.writeObject(obj);
		this.oos.close();
		this.out.close();
	}

	public void writeObjectXMLEncoder(Object obj) throws IOException {
		try {
			BufferedOutputStream oop = new BufferedOutputStream(
					new java.io.FileOutputStream(f));
			XMLEncoder xe = new java.beans.XMLEncoder(oop);

			xe.writeObject(obj);
			xe.close();
			oop.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
