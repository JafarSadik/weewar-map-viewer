package com.github.weewar.mapviewer.utils;

import com.github.weewar.mapviewer.exceptions.ImageIOException;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Images {
    public static byte[] toPNG(RenderedImage image) throws ImageIOException {
        try {
            ByteArrayOutputStream imageData = new ByteArrayOutputStream();
            ImageIO.write(image, "png", imageData);
            return imageData.toByteArray();
        } catch (IOException e) {
            throw new ImageIOException(e);
        }
    }
}
