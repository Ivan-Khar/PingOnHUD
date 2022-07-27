package com.aqupd.pingdisplayhud.threads;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class PingThread{
  String[] ipPort;

  public PingThread(String[] ipPort){
    this.ipPort = ipPort;
    isThreadRunning = true;
    new RunningThread().start();
  }

  private class RunningThread extends Thread {
    @Override
    public void run() {
      long triggerTime = System.currentTimeMillis();
      List<Long> pings = new LinkedList<>();
      do {
        if ((System.currentTimeMillis() - triggerTime > 1000)) {
          try {
            InetSocketAddress isa = new InetSocketAddress(ipPort[0], Integer.parseInt(ipPort[1]));
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(true);

            long pingStart = System.currentTimeMillis();
            long pingEnd = 0L;
            if(sc.connect(isa)) pingEnd = System.currentTimeMillis();

            pings.add(pingEnd - pingStart);
            long totalPing = 0;
            if(pings.size() == 11) pings.remove(0);
            for (Long ping: pings) {
              totalPing = totalPing + ping;
            }
            ping = totalPing / pings.size();
            System.out.println(pings);
          } catch (IOException ignored) {}
          triggerTime = System.currentTimeMillis();
        }
      } while(!shouldCloseThread);

      isThreadRunning = false;
      ping = 0L;
    }
  }
}
