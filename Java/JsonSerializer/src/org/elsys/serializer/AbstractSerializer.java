package org.elsys.serializer;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public abstract class AbstractSerializer implements Serializer {

	
	protected List<Field> getFieldsToSerialize(Class<?> cls) {
		// TODO
		// get all fields
		// check for annotation @Ignore
		// return all fields without @Ignore annotation
		return null;
	}

	protected boolean isArray(Object value) {
		return value.getClass().isArray();
	}
	
	protected boolean isCollection(Object value) {
		return value instanceof Collection;
	}

	protected boolean isDirectlySerializable(Object value) {
		return value instanceof Number || value instanceof String;
	}
	
	@Override
	public boolean areIncludedNullFields() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void includeNullFields(boolean includeNullFields) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isPretty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setPretty(boolean pretty) {
		// TODO Auto-generated method stub
		
	}
	
}
