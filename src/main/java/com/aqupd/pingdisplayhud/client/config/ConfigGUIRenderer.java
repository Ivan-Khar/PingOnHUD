package com.aqupd.pingdisplayhud.client.config;

import com.aqupd.pingdisplayhud.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class ConfigGUIRenderer extends GuiScreen {
  GuiSlider redColorSlider, greenColorSlider, blueColorSlider, positionXSlider, positionYSlider, bgdOpacitySlider;
  GuiButton xAxisAlignButton, yAxisAlignButton, visibilityButton;
  GuiTextField prefixField, suffixField;

  @Override
  public void updateScreen() {

  }

  @Override
  public void initGui() {
    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    redColorSlider = new GuiSlider(new ConfigSliderResponder(), 101, sr.getScaledWidth()/2 - 151, sr.getScaledHeight()-128, "Red color", 0, 255, redColor, (id, name, value) -> name + ": " + (int) value);
    greenColorSlider = new GuiSlider(new ConfigSliderResponder(), 102, redColorSlider.xPosition, redColorSlider.yPosition + 21, "Green color", 0, 255, greenColor, (id, name, value) -> name + ": " + (int) value);
    blueColorSlider = new GuiSlider(new ConfigSliderResponder(), 103, greenColorSlider.xPosition, greenColorSlider.yPosition + 21 , "Blue color", 0, 255, blueColor, (id, name, value) -> name + ": " + (int) value);
    positionXSlider = new GuiSlider(new ConfigSliderResponder(), 111, redColorSlider.xPosition + 152, redColorSlider.yPosition, "Position X", 0, 100, guiXpercentage, (id, name, value) -> name + ": " + (int) value);
    positionYSlider = new GuiSlider(new ConfigSliderResponder(), 112, positionXSlider.xPosition, positionXSlider.yPosition + 21, "Position Y", 0, 100, guiYpercentage, (id, name, value) -> name + ": " + (int) value);
    bgdOpacitySlider = new GuiSlider(new ConfigSliderResponder(), 113, positionYSlider.xPosition, positionYSlider.yPosition + 21 , "BG Opacity", 0, 255, bgOpacity, (id, name, value) -> name + ": " + (int) value);

    xAxisAlignButton = new GuiButton(121, blueColorSlider.xPosition, blueColorSlider.yPosition + 22, 100, 20, "X Alignment: left");
    visibilityButton = new GuiButton(122, xAxisAlignButton.xPosition + 101, xAxisAlignButton.yPosition, 100, 20, "Visibility: true");
    yAxisAlignButton = new GuiButton(123, visibilityButton.xPosition + 101, visibilityButton.yPosition, 100, 20, "Y Alignment: top");

    prefixField = new GuiTextField(131, mc.fontRendererObj, xAxisAlignButton.xPosition + 1, xAxisAlignButton.yPosition + 23, 148, 18);
    suffixField = new GuiTextField(131, mc.fontRendererObj, prefixField.xPosition + 152, prefixField.yPosition, 148, 18);

    buttonList.add(redColorSlider);   buttonList.add(greenColorSlider);  buttonList.add(blueColorSlider);
    buttonList.add(positionXSlider);  buttonList.add(positionYSlider);   buttonList.add(bgdOpacitySlider);
    buttonList.add(xAxisAlignButton); buttonList.add(yAxisAlignButton);  buttonList.add(visibilityButton);

    prefixField.setVisible(true); prefixField.setFocused(false); prefixField.setEnabled(true); prefixField.setMaxStringLength(32); prefixField.setText("[");
    suffixField.setVisible(true); suffixField.setFocused(false); suffixField.setEnabled(true); suffixField.setMaxStringLength(32); suffixField.setText(" ms]");

    mc.mouseHelper.ungrabMouseCursor();
    super.initGui();
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Main.MODID, "textures/gui/configgui.png"));
    drawTexturedModalRect(sr.getScaledWidth()/2 - 159, sr.getScaledHeight() - 158, 0, 0, 4, 158);
    prefixField.drawTextBox(); suffixField.drawTextBox();
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    super.actionPerformed(button);

  }

  @Override
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    super.mouseClicked(mouseX, mouseY, mouseButton);

  }

  @Override
  protected void keyTyped(char typedChar, int keyCode) throws IOException {
    super.keyTyped(typedChar, keyCode);

  }
}
