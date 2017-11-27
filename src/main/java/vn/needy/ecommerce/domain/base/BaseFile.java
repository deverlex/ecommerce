package vn.needy.ecommerce.domain.base;

import java.io.Serializable;

public class BaseFile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 18422134902325L;
	private String fileName;
	private String uri;
	private String host;
	
	public BaseFile() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
