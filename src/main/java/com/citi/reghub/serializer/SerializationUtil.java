package com.citi.reghub.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializationUtil {

	public static byte[] serialize(Object object) {
		byte[] result = null;
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
	         ObjectOutput out = new ObjectOutputStream(bos);
	        out.writeObject(object);
	        result = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Object deserialize(byte[] bytes) {
		Object result = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	         ObjectInput in = new ObjectInputStream(bis);
	        result = in.readObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
}
