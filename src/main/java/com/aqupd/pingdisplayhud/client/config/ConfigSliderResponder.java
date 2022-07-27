package com.aqupd.pingdisplayhud.client.config;

import net.minecraft.client.gui.GuiPageButtonList;

import static com.aqupd.pingdisplayhud.config.Configuration.*;

public class ConfigSliderResponder implements GuiPageButtonList.GuiResponder {
  @Override
  public void func_175321_a(int id, boolean idk) {

  }

  @Override
  public void onTick(int id, float value) {
    switch (id) {
      case 101: //Red slider
        setRedColor((int) value);
        break;
      case 102: //Green slider
        setGreenColor((int) value);
        break;
      case 103: //Blue slider
        setBlueColor((int) value);
        break;
      case 111: //XPosition slider
        setGuiXPercentage((int) value);
        break;
      case 112: //YPosition slider
        setGuiYPercentage((int) value);
        break;
      case 113: //Opacity slider
        setBgOpacity((int) value);
        break;
    }
  }

  @Override
  public void func_175319_a(int id, String text) {

  }
}
