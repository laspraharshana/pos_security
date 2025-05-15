package com.university.pos.posbackend.controller;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class ImageController {

    private static final String IMAGE_DIR = "src/main/resources/static/product-image/"; // Make sure this is correct

    @GetMapping("/product-image/thumb/{filename}")
    public ResponseEntity<byte[]> getThumbnail(@PathVariable String filename) {
        try {
            // 1.  Create a Path to the original image.
            Path originalImagePath = Paths.get(IMAGE_DIR, filename);

            // 2.  Generate a thumbnail.  Adjust the size (200, 200) as needed.
            Path thumbnailPath = Paths.get(IMAGE_DIR, "thumb_" + filename); // Store thumbnail
            Thumbnails.of(originalImagePath.toFile())
                    .size(200, 200)
                    .toFile(thumbnailPath.toFile());

            // 3.  Read the thumbnail file into a byte array.
            byte[] imageBytes = Files.readAllBytes(thumbnailPath);

            // 4.  Determine the content type.
            MediaType contentType = MediaType.IMAGE_JPEG; //  Assume JPEG, adjust if needed.
            if (filename.toLowerCase().endsWith(".png")) {
                contentType = MediaType.IMAGE_PNG;
            }

            // 5.  Return the image data as a ResponseEntity.
            return ResponseEntity.ok()
                    .contentType(contentType)
                    .body(imageBytes);

        } catch (IOException e) {
            e.printStackTrace(); //  Log the error!
            return ResponseEntity.notFound().build(); //  Handle the error.
        }
    }
}

    