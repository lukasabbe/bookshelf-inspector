package com.lukasabbe;

import com.lukasabbe.handlers.OnPlayerDisconnectEvent;
import com.lukasabbe.handlers.OnPlayerJoinEvent;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

import java.io.*;
import java.nio.file.Path;

//? if fabric {
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.FabricLoader;
//?} elif neoforge {
/*import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.network.PacketDistributor;
import com.lukasabbe.entry.neoforge.EventHandler;
//?if >= 1.21.10{

/^import net.neoforged.neoforge.client.network.ClientPacketDistributor;
^///?}
*///?}

public sealed interface ModLoaderAccess {
    ModLoaderAccess INSTANCE =
        /*? if fabric{*/new FabricLoaderAccess();
        /*?} elif neoforge *///new NeoForgeLoaderAccess();

    Path getConfigPath(String file);
    Path getFileOrCopyInModContainer(String mod, String fileName);
    void sendPacketFromServer(ServerPlayer player, CustomPacketPayload payload);
    void sendPacketFromClient(CustomPacketPayload payload);
    void registerOnPlayerJoinEvent(OnPlayerJoinEvent event);
    void registerOnPlayerDisconnect(OnPlayerDisconnectEvent event);


    //? if fabric {
    final class FabricLoaderAccess implements ModLoaderAccess {
        private final FabricLoader loader = FabricLoader.getInstance();

        @Override
        public Path getConfigPath(String file) {
            return loader.getConfigDir().resolve(file);
        }

        @Override
        public Path getFileOrCopyInModContainer(String mod, String fileName) {
            if(loader.getModContainer(mod).isEmpty()) return null;
            ModContainer modContainer = loader.getModContainer(mod).get();
            return modContainer.findPath(fileName).orElseThrow();
        }

        @Override
        public void sendPacketFromServer(ServerPlayer player, CustomPacketPayload payload) {
            ServerPlayNetworking.send(player, payload);

        }

        @Override
        public void sendPacketFromClient(CustomPacketPayload payload) {
            ClientPlayNetworking.send(payload);
        }

        @Override
        public void registerOnPlayerJoinEvent(OnPlayerJoinEvent event) {
            ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> event.onPlayerJoin(handler.getPlayer(), server));

        }

        @Override
        public void registerOnPlayerDisconnect(OnPlayerDisconnectEvent event) {
            ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> event.onDisconnect());

        }
    }
    //?} elif neoforge {
    /*final class NeoForgeLoaderAccess implements ModLoaderAccess {

        @Override
        public Path getConfigPath(String file) {
            return FMLPaths.CONFIGDIR.get().resolve(file);
        }

        @Override
        public Path getFileOrCopyInModContainer(String mod, String fileName) {
            if(ModList.get().getModContainerById(mod).isEmpty()) return null;
            ModContainer container = ModList.get().getModContainerById(mod).get();
            //?if >= 1.21.10{
            
            /^try {
                InputStream inputStream = container.getModInfo().getOwningFile().getFile().getContents().get(fileName).open();
                File targetFile = new File(getConfigPath("bookshelfinspector-config.yml").toUri());
                try(OutputStream outputStream = new FileOutputStream(targetFile)){
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while((bytesRead = inputStream.read(buffer)) != -1){
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }catch (IOException ignore){}
            }catch (IOException ignore){}
            return null;
            ^///?}else {
            return container.getModInfo().getOwningFile().getFile().findResource(fileName);
            //?}
        }

        @Override
        public void sendPacketFromServer(ServerPlayer player, CustomPacketPayload payload) {
            try{
                PacketDistributor.sendToPlayer(player, payload);
            }catch (Exception ignored) {}
        }

        @Override
        public void sendPacketFromClient(CustomPacketPayload payload) {
            //?if >= 1.21.10{
            
            /^ClientPacketDistributor.sendToServer(payload);
            ^///?}else{
            PacketDistributor.sendToServer(payload);
            //?}
        }

        @Override
        public void registerOnPlayerJoinEvent(OnPlayerJoinEvent event) {
            EventHandler.playerJoinEvents.add(event);
        }

        @Override
        public void registerOnPlayerDisconnect(OnPlayerDisconnectEvent event) {
            EventHandler.playerDisconnectEvents.add(event);
        }
    }
    *///?}
}
