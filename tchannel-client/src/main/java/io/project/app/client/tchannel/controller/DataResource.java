package io.project.app.client.tchannel.controller;

import io.project.app.client.tchannel.services.PaymentsDataGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@Slf4j
@RequestMapping("/api/v2/data")
public class DataResource {

    @Autowired
    private PaymentsDataGenerator paymentsDataGenerator;

    @GetMapping(path = "/send")
    @CrossOrigin
    public ResponseEntity<?> post() {
        paymentsDataGenerator.generateSaveAndSend();
        return ResponseEntity.status(HttpStatus.OK).body("Data Sent");

    }
    
    //ab -n 100 -c 10 http://localhost:2019/api/v2/data/send

}
