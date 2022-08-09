package io.github.simplexdev.quickcoordscopy;

import io.github.simplexdev.quickcoordscopy.config.QuickCoordsCopyConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class QuickCoordsCopyClient implements ClientModInitializer {

    QuickCoordsCopyConfig config = AutoConfig.getConfigHolder(QuickCoordsCopyConfig.class).getConfig();

    private static final KeyBinding copyCoordsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.quickcoordscopy.copy",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_GRAVE_ACCENT,
            "category.quickcoordscopy.main"
    ));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (copyCoordsKey.wasPressed()) {
                if (client.player != null) {
                    var coords = config.format;
                    coords = coords.replace("$x", String.valueOf(Math.round(client.player.getX())));
                    coords = coords.replace("$y", String.valueOf(Math.round(client.player.getY())));
                    coords = coords.replace("$z", String.valueOf(Math.round(client.player.getZ())));
                    client.keyboard.setClipboard(coords);
                    client.player.sendMessage(Text.literal("Copied coordinates to keyboard!"));
                }
            }
        });
    }
}
