package com.example.dexloader;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Main {

	public static void main(String[] args) {
		for (int i = 0; i < 10; ++i) {
			doLoad();
		}
	}

	private static void doLoad() {
		String jarName = "jarlib.jar";
		try {
			File file = new File(jarName);
			URL url = file.toURL();
			URLClassLoader loader = new URLClassLoader(new URL[] { url },
					Main.class.getClassLoader());
			{
				Class<?> testClass = loader
						.loadClass("com.example.dexloader.lib.Test");
				System.out.println(testClass.hashCode());
				Method method = testClass.getDeclaredMethod("run", null);
				String result = (String) method.invoke(null);
				System.out.println(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
