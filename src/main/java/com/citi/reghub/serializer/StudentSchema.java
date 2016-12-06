package com.citi.reghub.serializer;

import java.io.IOException;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.TypeExtractor;
import org.apache.flink.streaming.util.serialization.DeserializationSchema;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import com.citi.reghub.model.Student;

public class StudentSchema implements SerializationSchema<Student>, DeserializationSchema<Student> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public TypeInformation<Student> getProducedType() {
		return TypeExtractor.getForClass(Student.class);
	}

	@Override
	public Student deserialize(byte[] arg0) throws IOException {
		return (Student) SerializationUtil.deserialize(arg0);
	}

	@Override
	public boolean isEndOfStream(Student arg0) {
		return false;
	}

	@Override
	public byte[] serialize(Student arg0) {
		return SerializationUtil.serialize(arg0);
			}
	
}
