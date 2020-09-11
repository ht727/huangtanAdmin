package com.huangtan.p2p;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.text.ParseException;

@SpringBootApplication
public class P2PAdminApplication {

    public static void main(String[] args) throws ParseException {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(P2PAdminApplication.class);
        ConfigurableApplicationContext context = springApplicationBuilder.run(args);
    }
}
