package com.aqupd.pingdisplayhud.utils;

public class Methods {
  public static int getDecFromColor(int red, int green, int blue){
    return red * 65536 + green * 256 + blue;
  }
}
