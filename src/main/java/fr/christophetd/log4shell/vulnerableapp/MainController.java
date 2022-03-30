package fr.christophetd.log4shell.vulnerableapp;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.SerializationUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class MainController {

    private static final Logger logger = LogManager.getLogger("HelloWorld");

    @GetMapping("/")
    public String index(@RequestHeader("X-Api-Version") String apiVersion) {
        
        logger.info("Received a request for API version " + apiVersion);
        
        // A payload that is capable of triggering a Java RCE on deserialization would be able to fire here.
        Object deserialized = (Object) SerializationUtils.deserialize(SerializationUtils.serialize(apiVersion));

        return "Hello, " + deserialized;
    }

}
