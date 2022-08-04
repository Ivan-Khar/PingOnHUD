package com.aqupd.pingdisplayhud.config;

import com.aqupd.pingdisplayhud.Main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {
  private static String prefix = "[Ping: ";
  private static String suffix = "ms]";
  private static String pingAccuracy = "0";
  private static boolean visible = true;
  private static boolean xAxis = true;
  private static boolean yAxis = false;
  private static float guiXPercentage = 1;
  private static float guiYPercentage = 9;
  private static int redColor = 255;
  private static int greenColor = 255;
  private static int blueColor = 255;
  private static int bgOpacity = 63;

  private static final File configFile = new File("./config/AqMods/PingOnHUD.properties");

  public static float getGuiXPercentage() { return guiXPercentage; }
  public static float getGuiYPercentage() { return guiYPercentage; }
  public static boolean isVisible() { return visible; }
  public static boolean isxAxis() { return xAxis; }
  public static boolean isyAxis() { return yAxis; }
  public static int getBgOpacity() { return bgOpacity; }
  public static int getRedColor() { return redColor; }
  public static int getGreenColor() { return greenColor; }
  public static int getBlueColor() { return blueColor; }
  public static String getPrefix() { return prefix; }
  public static String getSuffix() { return suffix; }
  public static String getPingAccuracy() { return pingAccuracy; }

  public static void setGuiXPercentage(float guiXPercentage) { Configuration.guiXPercentage = guiXPercentage; saveOptions(); }
  public static void setGuiYPercentage(float guiYPercentage) { Configuration.guiYPercentage = guiYPercentage; saveOptions(); }
  public static void setVisible(boolean visible) { Configuration.visible = visible; saveOptions(); }
  public static void setXAxis(boolean xAxis) { Configuration.xAxis = xAxis; saveOptions(); }
  public static void setYAxis(boolean yAxis) { Configuration.yAxis = yAxis; saveOptions(); }
  public static void setBgOpacity(int bgOpacity) { Configuration.bgOpacity = bgOpacity; saveOptions(); }
  public static void setRedColor(int redColor) { Configuration.redColor = redColor; saveOptions(); }
  public static void setGreenColor(int greenColor) { Configuration.greenColor = greenColor; saveOptions(); }
  public static void setBlueColor(int blueColor) { Configuration.blueColor = blueColor; saveOptions(); }
  public static void setPrefix(String prefix) { Configuration.prefix = prefix; saveOptions(); }
  public static void setSuffix(String suffix) { Configuration.suffix = suffix; saveOptions(); }
  public static void setPingAccuracy(String pingAccuracy) { Configuration.pingAccuracy = pingAccuracy; saveOptions(); }


  public static void loadOptions() {
    try {
      if (!configFile.exists() || configFile.length() == 0) saveOptions();

      BufferedReader bufferedreader = new BufferedReader(new FileReader(configFile));
      String s;

      while ((s = bufferedreader.readLine()) != null) {
        String[] astring = s.split(":");
        String[] strstring = s.split(":=");
        if (astring[0].equals("xPercentage")) guiXPercentage = Float.parseFloat(astring[1]);
        if (astring[0].equals("yPercentage")) guiYPercentage = Float.parseFloat(astring[1]);

        if (astring[0].equals("visible")) visible = Boolean.parseBoolean(astring[1]);
        if (astring[0].equals("xAxis")) xAxis = Boolean.parseBoolean(astring[1]);
        if (astring[0].equals("yAxis")) yAxis = Boolean.parseBoolean(astring[1]);

        if (astring[0].equals("backgroundOpacity")) bgOpacity = Integer.parseInt(astring[1]);
        if (astring[0].equals("red")) redColor = Integer.parseInt(astring[1]);
        if (astring[0].equals("green")) greenColor = Integer.parseInt(astring[1]);
        if (astring[0].equals("blue")) blueColor = Integer.parseInt(astring[1]);

        if (astring[0].equals("prefix")) try { prefix = strstring[1]; } catch(ArrayIndexOutOfBoundsException ex) { prefix = ""; }
        if (astring[0].equals("suffix")) try { suffix = strstring[1]; } catch(ArrayIndexOutOfBoundsException ex) { suffix = ""; }
        if (astring[0].equals("pingAccuracy")) try { pingAccuracy = strstring[1]; } catch(ArrayIndexOutOfBoundsException ex) { pingAccuracy = "0"; }
      }
    } catch (IOException ex) {
      Main.LOGGER.error("Configuration exception: " + ex);
    }
  }

  public static void saveOptions() {
    try {
      Files.createDirectories(Paths.get("./config/AqMods/"));

      if (!configFile.exists()) configFile.createNewFile();
      if (configFile.exists()) {
        PrintWriter printwriter = new PrintWriter(new FileWriter(configFile));
        printwriter.println("xPercentage:" + guiXPercentage);
        printwriter.println("yPercentage:" + guiYPercentage);

        printwriter.println("visible:" + visible);
        printwriter.println("xAxis:" + xAxis);
        printwriter.println("yAxis:" + yAxis);

        printwriter.println("backgroundOpacity:" + bgOpacity);
        printwriter.println("red:" + redColor);
        printwriter.println("green:" + greenColor);
        printwriter.println("blue:" + blueColor);

        printwriter.println("prefix:=" + prefix);
        printwriter.println("suffix:=" + suffix);
        printwriter.println("pingAccuracy:=" + pingAccuracy);

        printwriter.close();
      }
    } catch (IOException ex) {
      Main.LOGGER.error("Configuration exception: " + ex);
    }
  }
}
