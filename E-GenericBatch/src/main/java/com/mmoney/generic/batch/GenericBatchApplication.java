package com.mmoney.generic.batch;

import com.mmoney.generic.batch.pojo.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class GenericBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run (GenericBatchApplication.class, args);
        System.out.println ("Generic Batch Service Running :)");
    }

}
