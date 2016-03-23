package org.elsys.serializer.json;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.elsys.serializer.AbstractSerializer;
import org.elsys.serializer.Serializer;

public class JsonSerializer extends AbstractSerializer implements Serializer {

	@Override
	public String serialize(Object obj) {
		if (isArray(obj)) {
			return serializeCollection(Arrays.asList(obj));
		}
		if (isCollection(obj)) {
			return serializeCollection((Collection<?>) obj);
		}
		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer.append("{");
		List<Field> fieldsToSerialize = getFieldsToSerialize(obj.getClass());
		for (Field field : fieldsToSerialize) {
			field.setAccessible(true);
			try {
				Object value = field.get(obj);
				resultBuffer.append(field.getName());
				resultBuffer.append(" : ");
				if (isDirectlySerializable(value)) {
					resultBuffer.append('\"');
					resultBuffer.append(value);
					resultBuffer.append('\"');
				} else {
					resultBuffer.append(serialize(value));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		resultBuffer.append("}");
		return resultBuffer.toString();
	}
	
	private String serializeCollection(Collection<?> collection) {
		StringBuffer resultBuffer = new StringBuffer();
		resultBuffer.append("[");
		for (Object object : collection) {
			resultBuffer.append(serialize(object));
			resultBuffer.append(",");
		}
		if (!collection.isEmpty()) {
			resultBuffer.deleteCharAt(resultBuffer.length() - 1);
		}
		resultBuffer.append("]");
		return resultBuffer.toString();
	}
	
	public boolean areQuotedKeys() {
		// TODO
		return false;
	}
	
	public void setQuotedKeys() {
		// TODO
	}

}
