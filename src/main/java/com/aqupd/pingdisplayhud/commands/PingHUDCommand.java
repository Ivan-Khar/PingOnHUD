package com.aqupd.pingdisplayhud.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import static com.aqupd.pingdisplayhud.utils.Variables.*;

public class PingHUDCommand extends CommandBase {
  @Override
  public int getRequiredPermissionLevel() {
    return 0;
  }

  @Override
  public String getCommandName() {
    return "pinghud";
  }

  @Override
  public String getCommandUsage(ICommandSender sender) {
    return null;
  }

  @Override
  public void processCommand(ICommandSender sender, String[] args) {
    opengui = System.currentTimeMillis();
  }
}
