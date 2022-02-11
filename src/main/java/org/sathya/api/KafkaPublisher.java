package org.sathya.api;

import org.sathya.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class KafkaPublisher {

    private static final Logger logger = LoggerFactory.getLogger(KafkaPublisher.class);
    private static final String TOPIC = "users";

    private KafkaProducer kafkaProducer;

    @Autowired
    public KafkaPublisher(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/topic/{topic}")
    public String publish(@PathVariable("topic") String topic, @RequestBody String message) {
        kafkaProducer.sendMessage(topic, message);
        return topic;
    }
}
