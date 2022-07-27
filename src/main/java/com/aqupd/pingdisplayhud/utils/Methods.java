package com.aqupd.pingdisplayhud.utils;

public class Methods {
  public static int getDecFromColor(int red, int green, int blue){
    return red * 65536 + green * 256 + blue;
  }

  public static boolean isMouseOver(int bmouseX, int bmouseY, int bx, int by, int bwidth, int bheight) {
    return ((bmouseX > bx) && (bmouseX < bx + bwidth) && (bmouseY > by) && (bmouseY < by + bheight));
  }
}
