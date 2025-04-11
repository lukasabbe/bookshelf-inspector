package me.lukasabbe.bookshelfinspector;

import me.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryPayload;
import me.lukasabbe.bookshelfinspector.network.packets.BookShelfInventoryRequestPayload;
import me.lukasabbe.bookshelfinspector.network.packets.LecternInventoryRequestPayload;
import me.lukasabbe.bookshelfinspector.network.packets.ModCheckPayload;
import me.lukasabbe.bookshelfinspector.network.server.BookShelfInventoryRequestPayloadHandler;
import me.lukasabbe.bookshelfinspector.network.server.LecternInventoryRequestPayloadHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bookshelfinspector implements ModInitializer {

    public static MinecraftServer serverInstance;
    public final static String MOD_ID = "bookshelfinspector";
    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        //Registers client-to-server payloads
        PayloadTypeRegistry.playC2S().register(BookShelfInventoryRequestPayload.ID,BookShelfInventoryRequestPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(LecternInventoryRequestPayload.ID, LecternInventoryRequestPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BookShelfInventoryPayload.ID,BookShelfInventoryPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ModCheckPayload.ID, ModCheckPayload.CODEC);

        //Registers server-to-client receivers
        ServerPlayNetworking.registerGlobalReceiver(BookShelfInventoryRequestPayload.ID, new BookShelfInventoryRequestPayloadHandler());
        ServerPlayNetworking.registerGlobalReceiver(LecternInventoryRequestPayload.ID, new LecternInventoryRequestPayloadHandler());

        ServerPlayConnectionEvents.JOIN.register(((handler, sender, server) -> {
            serverInstance = server;
            ServerPlayNetworking.send(handler.player, new ModCheckPayload(true));
        }));
    }
}
