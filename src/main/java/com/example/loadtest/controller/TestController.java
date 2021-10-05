package com.example.loadtest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/was-cpu")
    public ResponseEntity<Void> cpu() {
        String a = "a";
        for (int i = 0; i < 10000; i++) {
            a += i;
            if (i % 1000 == 0) {
                System.out.println(i);
            }
        }
        System.out.println("완료");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/infinite-loop")
    public ResponseEntity<Void> infinite() {
        boolean check = true;
        int i = 0;
        while(check) {
            i += 1;
            i = 0;
        }
        System.out.println(i);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/was-io")
    public ResponseEntity<Void> wasIo() throws IOException {
        Path path = Paths.get("./src/main/resources/video.mp4");
        byte[] bytes = Files.readAllBytes(path);

        Path newPath = Paths.get("./src/main/resources/newVideo.mp4");
        Files.write(newPath, bytes);
        System.out.println("완료");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/was-network")
    public ResponseEntity<byte[]> wasNetwork() throws IOException {
        Path path = Paths.get("./src/main/resources/video.mp4");
        byte[] bytes = Files.readAllBytes(path);
        System.out.println("완료");
        return ResponseEntity.ok(bytes);
    }
}
