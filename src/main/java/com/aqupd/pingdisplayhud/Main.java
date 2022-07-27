package com.aqupd.pingdisplayhud;

import com.aqupd.pingdisplayhud.commands.PingHUDCommand;
import com.aqupd.pingdisplayhud.listeners.EventListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.aqupd.pingdisplayhud.config.Configuration.loadOptions;

@Mod(modid = Main.MODID)
public class Main {
    public static final String MODID = "pingdisplayhud";
    public static final Logger LOGGER = LogManager.getLogger("AqUpd's Ping on HUD");
    private final EventListener eventListener;

    public Main() {
        this.eventListener = new EventListener();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
		// some example code
        loadOptions();
        MinecraftForge.EVENT_BUS.register(eventListener);
        ClientCommandHandler.instance.registerCommand(new PingHUDCommand());
    }
}
