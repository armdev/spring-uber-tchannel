package io.project.app.client.tchannel;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private TchanelClient tchanelClient;

    @PostMapping(path = "/send") 
    @CrossOrigin
    public ResponseEntity<?> post(
            @Valid @RequestBody(required = true) ImportantInfo userModel
    ) {
        tchanelClient.sendData(userModel);
        return ResponseEntity.status(HttpStatus.OK).body("Data Sent");

    }

}
