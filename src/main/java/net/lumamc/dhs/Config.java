package net.lumamc.dhs;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
public class Config extends OkaeriConfig {

    private TicksPer ticksPer = new TicksPer();
    private int hopperAmount = 1;
    private boolean hoppersCanLoadChunks = false;
    private List<String> excludedWorlds = List.of(); // i'm lazy :P


    @Getter
    @Accessors(fluent = true)
    public static class TicksPer extends OkaeriConfig {
        private int hopperTransfer = 8;
        private int hopperCheck = 1;
    }

}
