package us.cnlist.rzhd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PacketEmitter {

    public static void main(String... args) {
        SpringApplication.run(PacketEmitter.class);
    }

}
