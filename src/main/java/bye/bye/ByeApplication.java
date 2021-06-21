package bye.bye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication
@EnableKafka
@EnableEurekaClient
public class ByeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ByeApplication.class, args);
    }

}
