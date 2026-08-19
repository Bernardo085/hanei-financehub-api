package com.hanei.financehub.api.adapters.in.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

/**
 * Endpoint simples de verificacao de saude, usado para validar que o
 * esqueleto do projeto sobe corretamente (Sprint 1).
 */
@RestController
public class HealthController {

    @GetMapping("/api/health")
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "service", "hanei-financehub-api",
                "timestamp", Instant.now().toString()
        );
    }
}
