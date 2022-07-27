package com.aqupd.pingdisplayhud;

import com.aqupd.pingdisplayhud.commands.PingHUDCommand;
import com.aqupd.pingdisplayhud.listeners.EventListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Main.MODID, version = "${version}")
public class Main {
    public static final String MODID = "pingdisplayhud";
    private final EventListener eventListener;

    public Main() {
        this.eventListener = new EventListener();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
		// some example code
        MinecraftForge.EVENT_BUS.register(eventListener);
        ClientCommandHandler.instance.registerCommand(new PingHUDCommand());
    }
}
