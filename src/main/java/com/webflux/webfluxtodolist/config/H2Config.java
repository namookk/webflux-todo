package com.webflux.webfluxtodolist.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Slf4j
@Configuration
public class H2Config {
    private Server webServer;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException{
        log.info("starting h2 console");
        this.webServer = Server.createWebServer("-webPort","8081", "-tcpAllowOthers").start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("stopping h2 console");
        this.webServer.stop();
    }
}
