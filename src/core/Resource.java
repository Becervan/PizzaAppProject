package core;


import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import gui.Main;

public class Resource {
	private ClassLoader loader;
	private String path;
	
	public Resource(String path) {
		this.loader = Main.class.getClassLoader();
		this.path = path;
		
	}
	public URL getURL() {
		return loader.getResource(path);
	}
	public String getRawPath() {
		try {
			return getURL().toURI().getRawPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}
	public InputStream getStream() {
		return loader.getResourceAsStream(path);
	}
}
