package com.citi.reghub;

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;

import com.citi.reghub.model.Student;
import com.citi.reghub.serializer.StudentSchema;


/**
 * Skeleton for a Flink Streaming Job.
 *
 * For a full example of a Flink Streaming Job, see the SocketTextStreamWordCount.java
 * file in the same package/directory or have a look at the website.
 *
 * You can also generate a .jar file that you can submit on your Flink
 * cluster.
 * Just type
 * 		mvn clean package
 * in the projects root directory.
 * You will find the jar in
 * 		target/flink-metrics-1.0.jar
 * From the CLI you can then run
 * 		./bin/flink run -c com.citi.reghub.StreamingJob target/flink-metrics-1.0.jar
 *
 * For more information on the CLI see:
 *
 * http://flink.apache.org/docs/latest/apis/cli.html
 */
public class KafkaStreamingJob {

	public static void main(String[] args) throws Exception {
		
		
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.setProperty("auto.offset.reset", "earliest");
		
		ParameterTool params = ParameterTool.fromArgs(args);
		String topicName = params.get("tname");
		
		// set up the streaming execution environment
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		// start a checkpoint every 1000 ms
		env.enableCheckpointing(1000);

		// set mode to exactly-once (this is the default)
		env.getCheckpointConfig().setCheckpointingMode(CheckpointingMode.EXACTLY_ONCE);

		env.setRestartStrategy(
				  RestartStrategies.fixedDelayRestart(
				    6,                            // 6 retries
				    Time.of(10, TimeUnit.SECONDS) // 10 secs delay
				  ));
		DataStream <Student> stream = env.addSource(new FlinkKafkaConsumer09<>(topicName, new StudentSchema(), properties));
				
		stream.print();
		
		// execute program
		env.execute("Student Logger");
	}
}
