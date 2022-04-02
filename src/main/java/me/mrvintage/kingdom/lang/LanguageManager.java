package me.mrvintage.kingdom.lang;

import me.mrvintage.kingdom.event.OnSpellcastAttemptCallback;
import me.mrvintage.kingdom.magic.parser.SpellToken;
import me.mrvintage.kingdom.magic.parser.SpellTokenList;
import me.mrvintage.kingdom.magic.parser.SpellTokenType;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class LanguageManager {

    public static final LanguageMap<ElvenWord> ELVEN = new LanguageMap<>(ElvenWord.class);

    public static boolean onPlayerChat(ServerPlayerEntity player, String message) {
        if(message.startsWith(":E ")) {
            message = message.substring(3);
            String[] split = message.split(" ");
            String result = "";
            var tokenTypes = new SpellTokenList();
            if(split.length > 1)
            for(int i = 1; i < split.length; i++) {
                String word = split[i].toLowerCase();
                var other = ELVEN.fromEnglishToLanguage(word).orElse("");
                var type = ELVEN.getTokenType(word).orElse(SpellTokenType.NONE);
                tokenTypes.add(new SpellToken(type, word));
                result += other;
            }

            player.getServer().getPlayerManager().broadcast(Text.of(result), MessageType.CHAT, player.getUuid());
            OnSpellcastAttemptCallback.EVENT.invoker().onSpellcastAttempt(player, tokenTypes, message);
            return false;
        }

        return true;
    }

}
