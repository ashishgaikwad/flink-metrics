package com.citi.reghub.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import com.citi.reghub.model.Student;

public class KafkaStudentSerializer implements Serializer<Student>, Deserializer<Student>{

	@Override
	public Student deserialize(String arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		return (Student) SerializationUtil.deserialize(arg1);
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(Map<String, ?> arg0, boolean arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String arg0, Student arg1) {
		// TODO Auto-generated method stub
		return SerializationUtil.serialize(arg1);
	}
	
	
	
}
