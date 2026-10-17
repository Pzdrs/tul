package bohac;

/**
 * Maven shade plugin nechce tridu co extenduje Application, tudiz tohle je takovej workaround
 */
public class TicTacToeLauncher {
    public static void main(String[] args) {
        TicTacToe.main(args);
    }
}
