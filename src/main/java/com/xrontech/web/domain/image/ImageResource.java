package com.xrontech.web.domain.image;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/images")
@SecurityRequirement(name = "ecom")
@Slf4j
public class ImageResource {

    private final Path productImagePath = Paths.get("uploads/product");

    private final Path categoryImagePath = Paths.get("uploads/category");
    private final Path userImagePath = Paths.get("uploads/user");

    @GetMapping("/products/{filename:.+}")
    public ResponseEntity<Resource> getProductImage(@PathVariable String filename) {
        try {
            Path file = productImagePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found " + filename, e);
        }
    }

    @GetMapping("/categories/{filename:.+}")
    public ResponseEntity<Resource> getCategoryImage(@PathVariable String filename) {
        try {
            Path file = categoryImagePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found " + filename, e);
        }
    }
    @GetMapping("/users/{filename:.+}")
    public ResponseEntity<Resource> getUserImage(@PathVariable String filename) {
        try {
            Path file = userImagePath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                        .body(resource);
            } else {
                throw new RuntimeException("File not found " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found " + filename, e);
        }
    }
}
