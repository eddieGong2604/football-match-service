package com.example.footballleague;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FootballMatchServiceApplicationTests {

    @BeforeAll
    public static void setup() {
        System.setProperty("DB_URL", "jdbc:h2:mem:matches");
        System.setProperty("DB_USER", "sa");
        System.setProperty("DB_PASS", "");
        System.setProperty("REDIS_HOST", "localhost");
        System.setProperty("REDIS_PORT", "6379");
    }

    @AfterAll
    public static void tearDown() {
        System.clearProperty("DB_URL");
        System.clearProperty("DB_USER");
        System.clearProperty("DB_PASS");
        System.clearProperty("REDIS_HOST");
        System.clearProperty("REDIS_PORT");    }

    @Test
    void contextLoads() {
    }

}
