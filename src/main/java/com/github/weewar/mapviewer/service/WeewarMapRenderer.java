package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.exceptions.ImageNotFoundException;
import com.github.weewar.mapviewer.exceptions.ImageResizeException;
import com.github.weewar.mapviewer.model.WeewarMap;

import java.awt.image.BufferedImage;

public interface WeewarMapRenderer {
    BufferedImage render(WeewarMap weewarMap) throws ImageNotFoundException;

    BufferedImage renderThumbnail(WeewarMap weewarMap, int width, int height) throws ImageNotFoundException, ImageResizeException;
}
