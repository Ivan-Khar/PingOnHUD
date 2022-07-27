package com.aqupd.pingdisplayhud.listeners;

import com.aqupd.pingdisplayhud.client.config.ConfigGUIRenderer;
import com.aqupd.pingdisplayhud.client.PingOnHUDRenderer;
import com.aqupd.pingdisplayhud.threads.PingThread;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.aqupd.pingdisplayhud.config.Configuration.isVisible;
import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class EventListener {
  private String address;
  private boolean shouldStartThread;

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPlayerJoinServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
    if (!event.isLocal) {
      address = event.manager.getRemoteAddress().toString().substring(event.manager.getRemoteAddress().toString().indexOf("/")+1);
      shouldStartThread = true;
    }
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPlayerLeaveServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
    shouldCloseThread = true;
    shouldStartThread = false;
    ping = 0L;
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    if (event.player instanceof EntityPlayerSP) {
      if (!isThreadRunning && shouldStartThread) {
        shouldCloseThread = false;
        shouldStartThread = false;
        new PingThread(address.split(":", 2));
      }
    }
    if(opengui != -1 && (System.currentTimeMillis() - opengui) > 250) {
      opengui = -1L;
      Minecraft.getMinecraft().displayGuiScreen(new ConfigGUIRenderer());
    }
  }

  @SideOnly(Side.CLIENT)
  @SubscribeEvent
  public void onGuiRenderEvent(RenderGameOverlayEvent.Pre event) {
    if (event.type == RenderGameOverlayEvent.ElementType.BOSSHEALTH && isVisible()) {
      PingOnHUDRenderer.render();
    }
  }
}
