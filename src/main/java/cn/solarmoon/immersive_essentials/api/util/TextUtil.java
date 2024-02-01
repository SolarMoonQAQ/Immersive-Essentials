package cn.solarmoon.immersive_essentials.api.util;

import cn.solarmoon.immersive_essentials.Core;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class TextUtil {

    /**
     * @param num 任意数字
     * @return 返回罗马字符形式
     */
    public static String toRoman(int num) {
        String[] m = {"", "M", "MM", "MMM"};
        String[] c = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] x = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] i = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        String thousands = m[num/1000];
        String hundreds = c[(num%1000)/100];
        String tens = x[(num%100)/10];
        String ones = i[num%10];

        return thousands + hundreds + tens + ones;
    }

    /**
     * 翻译键
     * @param key1 翻译键·前
     * @param key2 翻译键·后
     * @param objects 特殊内容
     */
    public static Component translation(String key1, String key2, Object... objects) {
        return Component.translatable(key1 + "." + Core.MOD_ID + "." + key2, objects);
    }

    /**
     * 翻译键
     * @param key1 翻译键·前
     * @param key2 翻译键·后
     * @param format 颜色
     * @param objects 特殊内容
     */
    public static Component translation(String key1, String key2, ChatFormatting format, Object... objects) {
        return Component.translatable(key1 + "." + Core.MOD_ID + "." + key2, objects)
                .withStyle(format);
    }

    /**
     * 基本的发送带翻译键聊天栏提示方法
     * @param player 发送对象
     * @param key1 翻译键·前
     * @param key2 翻译键·后
     * @param objects 特殊内容
     */
    public static void sendTranslatableMessage(Player player, String key1, String key2, Object... objects) {
        player.sendSystemMessage(TextUtil.translation(key1, key2, objects));
    }

    /**
     * 基本的发送带翻译键聊天栏提示方法
     * @param player 发送对象
     * @param key1 翻译键·前
     * @param key2 翻译键·后
     * @param format 颜色
     * @param objects 特殊内容（可为空）
     */
    public static void sendTranslatableMessage(Player player, String key1, String key2, ChatFormatting format, Object... objects) {
        player.sendSystemMessage(TextUtil.translation(key1, key2, format, objects));
    }

    public static void debug(Player player, String string) {
        player.sendSystemMessage(Component.literal(string));
    }

}
