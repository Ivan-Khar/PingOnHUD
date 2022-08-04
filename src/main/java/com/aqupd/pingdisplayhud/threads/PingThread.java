package com.aqupd.pingdisplayhud.threads;

import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.OldServerPinger;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class PingThread{
  String[] ipPort;
  TickEvent.PlayerTickEvent event;
  OldServerPinger pinger;
  public PingThread(String[] ipPort, TickEvent.PlayerTickEvent ev){
    this.ipPort = ipPort;
    this.event = ev;
    isThreadRunning = true;
    new RunningThread().start();
    this.pinger = new OldServerPinger();
  }

  private class RunningThread extends Thread {
    @Override
    public void run() {
      long triggerTime = System.currentTimeMillis();
      List<Long> pings = new LinkedList<>();
      ServerData server = null;
      do {
        if ((System.currentTimeMillis() - triggerTime > 750)) {
          try {
            if(server != null && server.pingToServer > 0) {
              long totalPing = 0L;
              pings.add(server.pingToServer);
              if (pings.size() == 4) pings.remove(0);
              for (Long ping : pings) {
                totalPing = totalPing + ping;
              }
              ping = totalPing / pings.size();
            }
            server = new ServerData("test", ipPort[0] + ":" + ipPort[1], false);
            pinger.ping(server);
          } catch (UnknownHostException e) {
            System.out.println("Exception: " + e);
            e.printStackTrace();
          }
          triggerTime = System.currentTimeMillis();
        }
      } while(!shouldCloseThread);

      isThreadRunning = false;
      ping = 0L;
    }
  }
}
