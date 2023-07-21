package io.github.simplexdev.quickcoordscopy.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "quickcoordscopy")
public class QuickCoordsCopyConfig implements ConfigData {
    @Comment("The format of the string that gets copied. Use $x, $y and $z as placeholders.")
    public String format = "$x, $y, $z";
}
