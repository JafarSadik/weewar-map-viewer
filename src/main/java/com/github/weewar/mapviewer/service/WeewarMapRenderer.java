package com.github.weewar.mapviewer.service;

import com.github.weewar.mapviewer.model.WeewarMap;

import java.awt.image.BufferedImage;

public interface WeewarMapRenderer {
    BufferedImage render(WeewarMap weewarMap);
}
