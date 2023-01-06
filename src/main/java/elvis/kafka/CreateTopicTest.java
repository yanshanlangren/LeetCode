package elvis.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.kafka.common.security.JaasUtils;
import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;

public class CreateTopicTest extends Thread {

    static int n = 10000;

    public static final String bootstrapURL = "localhost:2181";

    private String topicName;

    public CreateTopicTest(int i) {
        this.topicName = "test_topic_" + i;
    }

    @Override
    public void run() {
        KafkaTopicBean bean = new KafkaTopicBean();
        bean.setTopicName(topicName);
        bean.setPartition(1);
        bean.setReplication(1);
        createKafkaTopic(bootstrapURL, bean);
    }

    public static void main(String[] args) throws FileNotFoundException {
        long t1 = System.currentTimeMillis();
        System.out.println(LocalDateTime.now() + ": start to create " + n + " topics");
        for (int i = 0; i < n; i++) {
            new CreateTopicTest(i).start();
        }
        long t2 = System.currentTimeMillis();
        System.out.println(LocalDateTime.now() + ": finished creating, creating  topics cost: " + (t2 - t1) / 1000 + " seconds");
        for (int i = 0; i < n; i++) {
            String topicName = "test_topic_" + i;
            KafkaTopicBean bean = new KafkaTopicBean();
            bean.setTopicName(topicName);
            bean.setPartition(1);
            bean.setReplication(1);
            deleteKafkaTopic(bootstrapURL, bean);
        }
        System.out.println(LocalDateTime.now() + ": finished deleting");
    }

    public static void createKafkaTopic(String ZkStr, KafkaTopicBean topic) {
        ZkUtils zkUtils = ZkUtils.
                apply(ZkStr, 30000, 30000, JaasUtils.isZkSecurityEnabled());

        AdminUtils.createTopic(zkUtils, topic.getTopicName(), topic.getPartition(),
                topic.getReplication(), new Properties(), new RackAwareMode.Enforced$());
        zkUtils.close();
    }

    public static void deleteKafkaTopic(String ZkStr, KafkaTopicBean topic) {
        ZkUtils zkUtils = ZkUtils.
                apply(ZkStr, 30000, 30000, JaasUtils.isZkSecurityEnabled());

        AdminUtils.deleteTopic(zkUtils, topic.getTopicName());
        zkUtils.close();
    }

    public static Properties newConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapURL);
        props.put("group.id", "temp1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("auto.offset.reset", "earliest");
        props.put("isolation.level", "read_committed");
//        props.put("auto.offset.reset", "earliest");
//        props.put("isolation.level", "read_committed");
        return props;
    }


}
