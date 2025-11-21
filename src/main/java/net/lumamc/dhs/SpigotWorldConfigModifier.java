package net.lumamc.dhs;

import net.minecraft.server.level.ServerLevel;
import org.bukkit.World;
import org.bukkit.craftbukkit.CraftWorld;

public class SpigotWorldConfigModifier {

    private final ServerLevel serverLevel;


    public SpigotWorldConfigModifier(World world) {
        this.serverLevel = ((CraftWorld) world).getHandle();
    }


    public void setHopperTransferCooldown(int newCooldown) {
        if (getHopperTransferCooldown() != newCooldown) {
            serverLevel.spigotConfig.hopperTransfer = newCooldown;
        }
    }

    public void setHopperCheckSpeed(int newSpeed) {
        if (getHopperCheckSpeed() != newSpeed) {
            serverLevel.spigotConfig.hopperCheck = newSpeed;
        }
    }

    public void setHopperTransferItemStackAmount(int newAmount) {
        if (getHopperTransferItemStackAmount() != newAmount) {
            serverLevel.spigotConfig.hopperAmount = newAmount;
        }
    }

    public void setHoppersCanLoadChunks(boolean newValue) {
        if (getHoppersCanLoadChunks() != newValue) {
            serverLevel.spigotConfig.hopperCanLoadChunks = newValue;
        }
    }

    public int getHopperTransferCooldown() {
        return serverLevel.spigotConfig.hopperTransfer;
    }

    public int getHopperCheckSpeed() {
        return serverLevel.spigotConfig.hopperCheck;
    }

    public int getHopperTransferItemStackAmount() {
        return serverLevel.spigotConfig.hopperAmount;
    }

    public boolean getHoppersCanLoadChunks() {
        return serverLevel.spigotConfig.hopperCanLoadChunks;
    }
}
