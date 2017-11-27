package vn.needy.ecommerce.domain.base;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@SuppressWarnings("serial")
public class BaseDomain implements Serializable {

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
