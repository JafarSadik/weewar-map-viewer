package com.github.weewar.mapviewer.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class MapImageController {
    @GetMapping(value = "/images/maps/{map_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> renderWeewarMap(@PathVariable("map_id") Integer mapId) throws IOException {
        Image plain = ImageIO.read(getClass().getResource("/terrain/plain.png"));
        BufferedImage weewarMapImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        Graphics g = weewarMapImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 200, 200);
        int hexWidth = 32, hexHeight = 34;
        for (int column = 0; column < 10; column++) {
            for (int row = 0; row < 10; row++) {
                double xOffset = row % 2 != 0 ? 0.5 : 0;
                double x = hexWidth * (column + xOffset);
                double y = 0.75 * hexHeight * row;
                g.drawImage(plain, (int) x, (int) y, null);
            }
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(weewarMapImage, "png", byteArrayOutputStream);
        byte[] imageData = byteArrayOutputStream.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(imageData.length)
                .body(imageData);
    }
}
