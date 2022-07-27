package com.aqupd.pingdisplayhud.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import static com.aqupd.pingdisplayhud.utils.Methods.*;
import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class PingOnHUDRenderer {
  public static void render() {
    Minecraft mc = Minecraft.getMinecraft();
    Tessellator tes = Tessellator.getInstance();
    WorldRenderer wr = tes.getWorldRenderer();

    GlStateManager.enableBlend();
    GlStateManager.disableTexture2D();
    GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);

    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    int halfwidth = sr.getScaledWidth()/2;
    int halfheight = sr.getScaledHeight()/2;

    String text = prefix + ping + suffix;
    long guiWidth = mc.fontRendererObj.getStringWidth(text) + 3;
    long guiHeight = 11;

    int guiX = (int) ((halfwidth-guiWidth/2) * (guiXpercentage/100));
    int guiY = (int) ((halfheight-guiHeight/2) * (guiYpercentage/100));

    wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
    wr.pos(guiX, guiY,0).color(0, 0, 0, bgOpacity).endVertex();
    wr.pos(guiX, guiY + guiHeight,0).color(0, 0, 0, bgOpacity).endVertex();
    wr.pos(guiX + guiWidth, guiY + guiHeight,0).color(0, 0, 0, bgOpacity).endVertex();
    wr.pos(guiX + guiWidth, guiY,0).color(0, 0, 0, bgOpacity).endVertex();
    tes.draw();

    GlStateManager.enableTexture2D();
    GlStateManager.disableBlend();

    mc.fontRendererObj.drawString(text, guiX + 2, guiY + 2, getDecFromColor(redColor, greenColor, blueColor), false);
  }
}
