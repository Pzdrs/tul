# TIC-TAC-TOE
## Before you start
- Non-square grids don't check the diagonals for a win
- *Settings.STARTING_PLAYER* => {0, 1, 2}
    - 0 - random person is chosen to make the first move
    - 1/2 - player 1/2 is chosen to make the first move

## Order Of Execution
1. TicTacToeLauncher - v pripade standalone appky, IDE bere rovnou TicTacToe classu
    - inicializuje se GUI, javafx background kod
1. Controller
   1. Konstruktor tridy Controller (kod, ktery nijak nemanipuluje s vlastnim GUI)
        - Vytvori se instance objektu Game a injecte
          se do statickeho atributu tridy Logic pro pozdesi pouziti
   1. Metoda Initializable#initialize(URL, ResourceBundle)
        - Nyni mame pristup k GridPane instanci - metoda #setupGrid()
          nastavi pozadovany pocet bunek a jejich procentualni velikost (width, height)
        - Nabinduje state hry na objekty v okne - adaptace na zmenu game state
        - Nakonec metoda Game#injectGridMechanics(GridPane) do vsech bunek vlozi AnchorPane,
          ktere naslouchaji urcitym eventum a chovaji se podle toho
            - Kliknuti na bunku
            - Prejeti mysi pres bunku
            - Zmena majitele bunky
## Firing it up in an IDE
- Since the release of Java 11, the JavaFX binaries are no longer shipped with the JDK - the 
  required libraries are in the attached *lib* folder
- Add VM arguments: *--module-path "lib" --add-modules javafx.controls,javafx.fxml*
