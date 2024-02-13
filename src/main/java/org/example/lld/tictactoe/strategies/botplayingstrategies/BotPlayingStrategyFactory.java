package org.example.lld.tictactoe.strategies.botplayingstrategies;

import org.example.lld.tictactoe.models.BotDifficultyLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel botDifficultyLevel){
        return new EasyBotPlayingStrategy();
// For this demo I am implementing only Easy bot playing Strategy.
//        return switch (botDifficultyLevel) {
//            case EASY -> new EasyBotPlayingStrategy();
//            case MEDIUM -> new MediumBotPlayingStrategy();
//            case HARD -> new HardBotPlayingStrategy();
//            // default -> new EasyBotPlayingStrategy();
//        };
    }
}
