package com.example.loadtest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/was-cpu")
    public ResponseEntity<Void> cpu() {
        String a = "a";
        for (int i = 0; i < 100000; i++) {
            a += i;
            if (i % 10000 == 0) {
                System.out.println(i);
            }
        }
        System.out.println("완료");
        return ResponseEntity.noContent().build();
    }
}
