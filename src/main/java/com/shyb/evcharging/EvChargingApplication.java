package com.shyb.evcharging;


import com.shyb.evcharging.user.config.MyBatisUserConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MyBatisUserConfig.class)
@SpringBootApplication
public class EvChargingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EvChargingApplication.class, args);
    }

}
