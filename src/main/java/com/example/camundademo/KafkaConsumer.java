package com.example.camundademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import io.camunda.zeebe.client.ZeebeClient;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private ZeebeClient zeebeClient;
    
    @KafkaListener(topics = {"camundatopic"}, groupId = "test-grp")
    public void consumeMessage(String message) {
        log.info("**** -> Consumed message -> {}", message);

        zeebeClient.newCreateInstanceCommand()
        .bpmnProcessId("Process_17d9taj")
        .latestVersion()
        .variables(message).send();
    }
}
