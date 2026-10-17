package bohac.util;

import bohac.Settings;

import java.util.Random;

public class Utils {
    private Utils() {
        throw new AssertionError();
    }

    public static int getStartingPlayer() {
        return (Settings.STARTING_PLAYER == 0) ? (new Random().nextBoolean() ? 1 : 2) : Settings.STARTING_PLAYER;
    }
}
