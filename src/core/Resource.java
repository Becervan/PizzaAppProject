package core;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class Resource {
	private URL path;
	private InputStream stream;
	
	public Resource(String path) {
		ClassLoader loader = Resource.class.getClassLoader();
		this.path = loader.getResource(path);
		this.stream = loader.getResourceAsStream(path);
	}
	public URL getURL() {
		return path;
	}
	public String getPath() {
		try {
			return path.toURI().getRawPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return "";
		}
	}
	public InputStream getStream() {
		return stream;
	}
}
