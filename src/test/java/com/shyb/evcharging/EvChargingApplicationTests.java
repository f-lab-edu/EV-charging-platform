package com.shyb.evcharging;

import com.shyb.evcharging.user.config.MyBatisUserConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(MyBatisUserConfig.class)
class EvChargingApplicationTests {

    @Test
    void contextLoads() {

    }
}
