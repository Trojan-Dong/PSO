package com.trojan.test;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.LoggerFactory;

/**
 * @describe 消费者测试
 * @author dgj
 * @date  2019年9月19日
 */
public class TestConsumer {

	private final org.slf4j.Logger log = LoggerFactory.getLogger(getClass());
	/*
	 * @KafkaListener(topics = { "zhisheng" }) public void
	 * listen(ConsumerRecord<?, ?> record) { Optional<?> kafkaMessage =
	 * Optional.ofNullable(record.value()); if (kafkaMessage.isPresent()) {
	 * 
	 * Object message = kafkaMessage.get();
	 * 
	 * log.info("----------------- record =" + record);
	 * log.info("------------------ message =" + message); }
	 * 
	 * }
	 */

	public static void main(String[] args) throws Exception {
		// if(args.length < 2){
		// System.out.println("Usage: consumer <topic> <groupname>");
		// return;
		// }

//		String topic = args[0].toString();
//		String group = args[1].toString();
		String topic = "testTopic";
		String group = "myGroup";
		Properties props = new Properties();
		
		//设置属性
		props.put("bootstrap.servers", "127.0.0.1:9092");
		props.put("group.id", group);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

		consumer.subscribe(Arrays.asList(topic));
		System.out.println("Subscribed to topic " + topic);
		int i = 0;

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records)
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
		}
	}
}
