package com.aqupd.pingdisplayhud.client.config;

import com.aqupd.pingdisplayhud.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import static com.aqupd.pingdisplayhud.utils.Methods.getDecFromColor;
import static com.aqupd.pingdisplayhud.utils.Methods.isMouseOver;
import static com.aqupd.pingdisplayhud.config.Configuration.*;

public class ConfigGUIRenderer extends GuiScreen {
  GuiSlider redColorSlider, greenColorSlider, blueColorSlider, positionXSlider, positionYSlider, bgdOpacitySlider;
  GuiButton xAxisAlignButton, yAxisAlignButton, visibilityButton;
  GuiTextField prefixField, suffixField, pingAccuracyField;
  long time = 0L;

  @Override
  public void updateScreen() {
    xAxisAlignButton.displayString = "X Alignment: " + (isxAxis() ? "right" : "left");
    yAxisAlignButton.displayString = "Y Alignment: " + (isyAxis() ? "bottom" : "top");
    visibilityButton.displayString = "Visibility: " + isVisible();
  }

  @Override
  public void initGui() {
    time = System.currentTimeMillis();
    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    redColorSlider = new GuiSlider(new ConfigSliderResponder(), 101, sr.getScaledWidth()/2 - 151, sr.getScaledHeight()-106, "Red color", 0, 255, getRedColor(), (id, name, value) -> name + ": " + (int) value);
    greenColorSlider = new GuiSlider(new ConfigSliderResponder(), 102, redColorSlider.xPosition, redColorSlider.yPosition + 21, "Green color", 0, 255, getGreenColor(), (id, name, value) -> name + ": " + (int) value);
    blueColorSlider = new GuiSlider(new ConfigSliderResponder(), 103, greenColorSlider.xPosition, greenColorSlider.yPosition + 21 , "Blue color", 0, 255, getBlueColor(), (id, name, value) -> name + ": " + (int) value);
    positionXSlider = new GuiSlider(new ConfigSliderResponder(), 111, redColorSlider.xPosition + 152, redColorSlider.yPosition, "Position X", 0, 100, getGuiXPercentage(), (id, name, value) -> name + ": " + (int) value);
    positionYSlider = new GuiSlider(new ConfigSliderResponder(), 112, positionXSlider.xPosition, positionXSlider.yPosition + 21, "Position Y", 0, 100, getGuiYPercentage(), (id, name, value) -> name + ": " + (int) value);
    bgdOpacitySlider = new GuiSlider(new ConfigSliderResponder(), 113, positionYSlider.xPosition, positionYSlider.yPosition + 21 , "BG Opacity", 0, 255, getBgOpacity(), (id, name, value) -> name + ": " + (int) value);

    xAxisAlignButton = new GuiButton(121, blueColorSlider.xPosition, blueColorSlider.yPosition + 22, 100, 20, "X Alignment: " + (isxAxis() ? "right" : "left"));
    visibilityButton = new GuiButton(122, xAxisAlignButton.xPosition + 101, xAxisAlignButton.yPosition, 100, 20, "Visibility: " + isVisible());
    yAxisAlignButton = new GuiButton(123, visibilityButton.xPosition + 101, visibilityButton.yPosition, 100, 20, "Y Alignment: " + (isyAxis() ? "bottom" : "top"));

    prefixField = new GuiTextField(131, mc.fontRendererObj, xAxisAlignButton.xPosition + 49, xAxisAlignButton.yPosition + 23, 50, 16);
    suffixField = new GuiTextField(132, mc.fontRendererObj, prefixField.xPosition + 102, prefixField.yPosition, 50, 16);
    pingAccuracyField = new GuiTextField(133, mc.fontRendererObj, suffixField.xPosition + 102, suffixField.yPosition, 50, 16);

    buttonList.add(redColorSlider);   buttonList.add(greenColorSlider);  buttonList.add(blueColorSlider);
    buttonList.add(positionXSlider);  buttonList.add(positionYSlider);   buttonList.add(bgdOpacitySlider);
    buttonList.add(xAxisAlignButton); buttonList.add(yAxisAlignButton);  buttonList.add(visibilityButton);

    prefixField.setVisible(true); prefixField.setFocused(false); prefixField.setEnabled(true); prefixField.setMaxStringLength(32); prefixField.setText(getSuffix());
    suffixField.setVisible(true); suffixField.setFocused(false); suffixField.setEnabled(true); suffixField.setMaxStringLength(32); suffixField.setText(getPrefix());
    pingAccuracyField.setVisible(true); pingAccuracyField.setFocused(false); pingAccuracyField.setEnabled(true); pingAccuracyField.setMaxStringLength(6); pingAccuracyField.setText(getPingAccuracy()); pingAccuracyField.setValidator(input -> input.matches("[0-9]+") || input.length() == 0);

    mc.mouseHelper.ungrabMouseCursor();
    super.initGui();
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks) {
    if (time != -1 && System.currentTimeMillis() - time > 500) { Mouse.setGrabbed(false); time = -1L; }
    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

    //background
    GlStateManager.enableBlend();
    GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Main.MODID, "textures/gui/configgui.png"));
    drawTexturedModalRect(sr.getScaledWidth()/2 - 159, sr.getScaledHeight() - 128, 0, 0, 4, 158);
    drawTexturedModalRect(sr.getScaledWidth()/2 - 155, sr.getScaledHeight() - 128, 4, 0, 62, 158);
    drawTexturedModalRect(sr.getScaledWidth()/2 - 93, sr.getScaledHeight() - 128, 4, 0, 62, 158);
    drawTexturedModalRect(sr.getScaledWidth()/2 - 31, sr.getScaledHeight() - 128, 4, 0, 62, 158);
    drawTexturedModalRect(sr.getScaledWidth()/2 + 31, sr.getScaledHeight() - 128, 4, 0, 62, 158);
    drawTexturedModalRect(sr.getScaledWidth()/2 + 93, sr.getScaledHeight() - 128, 4, 0, 62, 158);
    drawTexturedModalRect(sr.getScaledWidth()/2 + 155, sr.getScaledHeight() - 128, 66, 0, 4, 158);

    fontRendererObj.drawString("Ping customizer", (sr.getScaledWidth()/2) - (fontRendererObj.getStringWidth("Ping customizer")/2), sr.getScaledHeight() - 120, getDecFromColor(63, 63, 63));

    prefixField.drawTextBox(); suffixField.drawTextBox(); pingAccuracyField.drawTextBox();
    fontRendererObj.drawString("Prefix:", prefixField.xPosition - 40, prefixField.yPosition + 4, getDecFromColor(31, 31, 31));
    fontRendererObj.drawString("Suffix:", suffixField.xPosition - 40, suffixField.yPosition + 4, getDecFromColor(31, 31, 31));
    fontRendererObj.drawString("Ping acc.:", pingAccuracyField.xPosition - 48, pingAccuracyField.yPosition + 4, getDecFromColor(31, 31, 31));

    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    super.actionPerformed(button);
    switch(button.id){
      case 121:
        setXAxis(!isxAxis());
        break;
      case 122:
        setVisible(!isVisible());
        break;
      case 123:
        setYAxis(!isyAxis());
    }
  }

  @Override
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
    super.mouseClicked(mouseX, mouseY, mouseButton);
    if(isMouseOver(mouseX, mouseY, prefixField.xPosition, prefixField.yPosition, 100, 16)) {
      prefixField.setFocused(true);
      suffixField.setFocused(false);
      pingAccuracyField.setFocused(true);
    }
    if(isMouseOver(mouseX, mouseY, suffixField.xPosition, suffixField.yPosition, 100, 16)) {
      prefixField.setFocused(false);
      suffixField.setFocused(true);
      pingAccuracyField.setFocused(true);
    }
    if(isMouseOver(mouseX, mouseY, pingAccuracyField.xPosition, pingAccuracyField.yPosition, 100, 16)) {
      prefixField.setFocused(false);
      suffixField.setFocused(false);
      pingAccuracyField.setFocused(true);
    }
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode) throws IOException {
    super.keyTyped(typedChar, keyCode);

    if (prefixField.isFocused()) {
      prefixField.textboxKeyTyped(typedChar, keyCode);
      setPrefix(prefixField.getText());
    }
    if (suffixField.isFocused()) {
      suffixField.textboxKeyTyped(typedChar, keyCode);
      setSuffix(suffixField.getText());
    }
    if (pingAccuracyField.isFocused()) {
      pingAccuracyField.textboxKeyTyped(typedChar, keyCode);
      setPingAccuracy(pingAccuracyField.getText());
    }
  }
}
