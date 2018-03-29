package vn.needy.ecommerce.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@SuppressWarnings("serial")
public abstract class BaseDomain implements Serializable {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
