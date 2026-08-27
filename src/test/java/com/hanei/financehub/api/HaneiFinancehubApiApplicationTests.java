package com.hanei.financehub.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class HaneiFinancehubApiApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getConnectionString);
        registry.add("hanei.security.jwt.secret",
                () -> "test-secret-key-not-for-production-use-only-256-bits-minimum");
    }

    @Test
    void contextLoads() {
        // Verifica que todo o contexto Spring sobe corretamente,
        // incluindo conexao com um MongoDB real rodando em container Docker (Testcontainers).
    }
}