package vn.needy.ecommerce.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@SuppressWarnings("serial")
public class DomainBase implements Serializable {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
