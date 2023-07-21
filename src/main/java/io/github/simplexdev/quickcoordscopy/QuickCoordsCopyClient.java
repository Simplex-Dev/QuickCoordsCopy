package io.github.simplexdev.quickcoordscopy;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class QuickCoordsCopyClient implements ClientModInitializer {
    final String DEFAULT_COORDS_FORMAT = "$x, $y, $z";
    final String COORDS_FORMAT_KEY = "formatting";
    final String DEFAULT_CONFIG = COORDS_FORMAT_KEY + "=" + DEFAULT_COORDS_FORMAT;

    SimpleConfig config = SimpleConfig
            .of("quickcoordscopy")
            .provider((namespace) -> DEFAULT_CONFIG)
            .request();

    private static final KeyBinding copyCoordsKey = KeyBindingHelper
            .registerKeyBinding(
                    new KeyBinding(
                            "key.quickcoordscopy.copy",
                            InputUtil.Type.KEYSYM,
                            GLFW.GLFW_KEY_GRAVE_ACCENT,
                            "category.quickcoordscopy.main"));

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (copyCoordsKey.wasPressed()) {
                if (client.player != null) {
                    final var format = config.getOrDefault(COORDS_FORMAT_KEY, DEFAULT_COORDS_FORMAT);

                    final var coords = format
                            .replace("$x", String.valueOf(Math.round(client.player.getX())))
                            .replace("$y", String.valueOf(Math.round(client.player.getY())))
                            .replace("$z", String.valueOf(Math.round(client.player.getZ())));

                    client.keyboard.setClipboard(coords);
                    client.player.sendMessage(Text.literal("Copied coordinates to keyboard!"));
                }
            }
        });
    }
}
