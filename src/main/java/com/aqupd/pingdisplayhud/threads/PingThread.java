package com.aqupd.pingdisplayhud.threads;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.net.UnknownHostException;

import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class PingThread {
  String ip;
  TickEvent.PlayerTickEvent event;
  public PingThread(String ip, TickEvent.PlayerTickEvent ev){
    this.ip = ip;
    this.event = ev;
    isThreadRunning = true;
    new RunningThread().start();
  }

  private class RunningThread extends Thread {
    @Override
    public void run() {
      long triggerTime = System.currentTimeMillis();
      ServerData server = new ServerData("test", ip, false);
      do {
        if ((System.currentTimeMillis() - triggerTime) > 5000) {
          try {
            if(server.pingToServer > 0) { ping = server.pingToServer; }
            pinger.ping(server);
            triggerTime = System.currentTimeMillis();
          } catch (UnknownHostException e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
          }
        }
      } while(!shouldCloseThread);

      isThreadRunning = false;
      ping = 0L;
    }
  }
}
