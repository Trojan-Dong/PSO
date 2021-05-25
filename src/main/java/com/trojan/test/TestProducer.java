package com.trojan.test;

//import util.properties packages
import java.util.Properties;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @describe 生产者测试
 * @author dgj
 * @date  2019年9月19日
 */
public class TestProducer {

	public static void main(String[] args) throws Exception{
	      
	      // Check arguments length value
//	      if(args.length == 0){
//	         System.out.println("Enter topic name");
//	         return;
//	      }
	      
	      //Assign topicName to string variable
	      String topicName ="testTopic";
	      
	      // create instance for properties to access producer configs   
	      Properties props = new Properties();
	      
	      //Assign localhost id
	      props.put("bootstrap.servers", "127.0.0.1:9092");
	      
	      //Set acknowledgements for producer requests.
	      //生产者请求确认信息
	      props.put("acks","all");
	      
	      //If the request fails, the producer can automatically retry,
	      //自动重试
	      props.put("retries", 0);
	      
	      //Specify buffer size in config
	      //缓冲区大小
	      props.put("batch.size", 16384);
	      
	      //Reduce the no of requests less than 0   
	      props.put("linger.ms", 1);
	      
	      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
	      props.put("buffer.memory", 33554432);
	      
	      props.put("key.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	         
	      props.put("value.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	      
	      Producer<String, String> producer = new KafkaProducer
	         <String, String>(props);
	            
	      for(int i = 0; i < 10; i++)
	         producer.send(new ProducerRecord<String, String>(topicName, 
	           "hello kafka"+i, Integer.toString(i)));
	               System.out.println("Message sent successfully");
	               producer.close();
	   }
}

