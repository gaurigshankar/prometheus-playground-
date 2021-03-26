package com.gauri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo")
public class Controller {
    @Autowired
    IncreaseCounterJob increaseCounterJob;

    @GetMapping("/status")
    public ResponseEntity<String> getDemoStatus()
    {
        String status = "Hello Spring Demo";
        increaseCounterJob.increaseCounter();
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @GetMapping("/readiness")
    public ResponseEntity<String> getReadinessProbe()
    {
        String status = "Hello Readiness";
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }

    @GetMapping("/liveness")
    public ResponseEntity<String> getLivenessProbe()
    {
        String status = "Hello Liveness";
        return new ResponseEntity<String>(status, HttpStatus.OK);
    }
}
