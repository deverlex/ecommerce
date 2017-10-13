package vn.needy.ecommerce.model; 

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ModelBase implements Serializable {

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
