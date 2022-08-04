package com.aqupd.pingdisplayhud.utils;

import net.minecraft.client.network.OldServerPinger;

public class Variables {
  public static boolean isThreadRunning = false;
  public static boolean shouldCloseThread = false;
  public static long opengui = -1L;
  public static long ping = 0L;

  public static OldServerPinger pinger = new OldServerPinger();
}
