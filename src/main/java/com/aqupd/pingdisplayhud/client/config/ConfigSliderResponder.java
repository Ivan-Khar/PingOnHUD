package com.aqupd.pingdisplayhud.client.config;

import net.minecraft.client.gui.GuiPageButtonList;

import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class ConfigSliderResponder implements GuiPageButtonList.GuiResponder {
  @Override
  public void func_175321_a(int id, boolean idk) {

  }

  @Override
  public void onTick(int id, float value) {
    switch (id) {
      case 101: //Red slider
        redColor = (int) value;
        break;
      case 102: //Green slider
        greenColor = (int) value;
        break;
      case 103: //Blue slider
        blueColor = (int) value;
        break;
      case 111: //XPosition slider
        guiXpercentage = (int) value;
        break;
      case 112: //YPosition slider
        guiYpercentage = (int) value;
        break;
      case 113: //Opacity slider
        bgOpacity = (int) value;
        break;
    }
  }

  @Override
  public void func_175319_a(int id, String text) {

  }
}
