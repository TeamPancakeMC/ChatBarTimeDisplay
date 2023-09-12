package com.pancake.chat_bar_time_display;


import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


import java.util.Calendar;
import java.util.UUID;


@Mod(ChatBarTimeDisplay.MOD_ID)
public class ChatBarTimeDisplay {
    public static final String MOD_ID = "chat_bar_time_display";

    public ChatBarTimeDisplay() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        Calendar cal = Calendar.getInstance();

        String timeString;
        int hours;
        if (Config.TIME_FORMAT.get()) {
            hours = cal.get(Calendar.HOUR_OF_DAY);
        }
        else {
            hours = cal.get(Calendar.HOUR);
        }
        if (Config.SHOW_SECONDS.get()) {
            timeString = String.format("[%02d:%02d:%02d", hours, cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        }
        else {
            timeString = String.format("[%02d:%02d", hours, cal.get(Calendar.MINUTE));
        }
        if (!Config.TIME_FORMAT.get() && Config.SHOW_AM_PM.get()) {
            timeString += " " + (cal.get(Calendar.AM_PM) == Calendar.PM ? "PM" : "AM");
        }
        timeString += "]";


        MutableComponent finalString = Component.translatable(timeString);


        TextColor color;

        if (Config.NAME_BASED.get()) {
            color = getColorFromUUID(event.getSender());
        }
        else {
            color = TextColor.fromRgb(Config.COLOR.get());
        }

        finalString.setStyle(finalString.getStyle().withColor(color));

        if (Config.SHOW_BEFORE.get()) {
            event.setMessage(Component.translatable("").append(finalString).append(" ").append(event.getMessage()));
        }
        else {
            event.setMessage(Component.translatable("").append(event.getMessage()).append(" ").append(finalString));
        }
    }

    private TextColor getColorFromUUID(UUID senderUUID) {
        return TextColor.fromRgb(senderUUID.hashCode());
    }
}
