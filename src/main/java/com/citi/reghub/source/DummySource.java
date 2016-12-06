package com.citi.reghub.source;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.citi.reghub.model.Student;

public class DummySource {

	public static void main(String[] args) {

		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");

		properties.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");

		properties.put("value.serializer",
				"com.citi.reghub.serializer.KafkaStudentSerializer");
		
		KafkaProducer<String, Student> producer = new KafkaProducer<>(
				properties);
		try {
			for(int i = 452; i < 10000; i++) {
				Student st = new Student("x_" + 0, i);
				Thread.sleep(1000);
				producer.send(new ProducerRecord<String, Student>("st3", st));
				producer.flush();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}

	}

}
