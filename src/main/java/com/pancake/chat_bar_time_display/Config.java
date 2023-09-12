package com.pancake.chat_bar_time_display;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ChatBarTimeDisplay.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    public static final String CATEGORY_GENERAL = "general";

    public static ForgeConfigSpec CLIENT_CONFIG;

    public static ForgeConfigSpec.BooleanValue SHOW_SECONDS;
    public static ForgeConfigSpec.BooleanValue SHOW_BEFORE;
    public static ForgeConfigSpec.BooleanValue TIME_FORMAT;
    public static ForgeConfigSpec.BooleanValue SHOW_AM_PM;
    public static ForgeConfigSpec.BooleanValue NAME_BASED;
    public static ForgeConfigSpec.IntValue COLOR;

    static {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        NAME_BASED = CLIENT_BUILDER.comment("Use a different color for every player").define("name_based", false);
        SHOW_SECONDS = CLIENT_BUILDER.comment("Show seconds").define("show_seconds", true);
        SHOW_BEFORE = CLIENT_BUILDER.comment("Show timestamp before text. (false: show after)").define("show_before", true);
        TIME_FORMAT = CLIENT_BUILDER.comment("24 hour or 12 hour format. (true: 24, false: 12)").define("time_format", true);
        SHOW_AM_PM = CLIENT_BUILDER.comment("Show AM/PM", "Requires TIME_FORMAT to be false").define("show_period", false);
        COLOR = CLIENT_BUILDER.comment("Decimal color code", "https://www.mathsisfun.com/hexadecimal-decimal-colors.html", "Default: 11184810 (gray)").defineInRange("color_code", 11184810, 0, 16777215);

        CLIENT_BUILDER.pop();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

}

