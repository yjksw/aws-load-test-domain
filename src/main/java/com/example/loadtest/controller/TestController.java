package com.example.loadtest.controller;

import com.example.loadtest.domain.Comment;
import com.example.loadtest.domain.Post;
import com.example.loadtest.domain.PostRepository;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final PostRepository postRepository;

    public TestController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/saveData")
    public ResponseEntity<Void> save() {
        for (int i = 0; i < 5; i++) {
            Post newPost = new Post("내용내용" + i, "글쓴이" + i, "카테코리" + i);
            Post savedPost = postRepository.save(newPost); //flush

            Comment comment = new Comment("댓글", "글쓴이" + i);
            savedPost.addComment(comment);
        }

        return ResponseEntity.noContent().build();
    }

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
