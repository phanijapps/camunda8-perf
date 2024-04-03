package com.example.camundademo;

import java.util.Map;

import org.springframework.stereotype.Service;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DemoWorker {
    

    @JobWorker(type = "call-dummy-worker", autoComplete = false)
    public void handleMultiInstanceJob(ActivatedJob job, JobClient client){
        log.info("Inside Multinstance");
        Map<String,Object> vars = job.getVariablesAsMap();
        client.newCompleteCommand(job.getKey()).variables(vars).send();
    }
}
