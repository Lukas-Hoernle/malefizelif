import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer;

    public Game() {
        board = new Board();
        players = new ArrayList<>();
        currentPlayer = null;
    }

    public void startGame() {
        System.out.println("Willkommen bei CampusChaos!");
        System.out.println("Bitte geben Sie die Anzahl der Spieler ein:");
        Scanner scanner = new Scanner(System.in);
        int numPlayers = scanner.nextInt();
        for (int i = 0; i < numPlayers; i++) {
            Player player = new Player("Spieler " + (i + 1));
            players.add(player);
        }
        currentPlayer = players.get(0);
        System.out.println("Das Spiel beginnt!");
        playGame();
    }

    private void playGame() {
        while (true) {
            System.out.println("Aktueller Spieler: " + currentPlayer.getName());
            System.out.println("Bitte geben Sie einen Befehl ein (help, show, roll, move, skip):");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            switch (command) {
                case "help":
                    System.out.println("Verfügbare Befehle: help, show, roll, move, skip");
                    break;
                case "show":
                    board.showBoard();
                    break;
                case "roll":
                    int diceRoll = rollDice();
                    System.out.println("Würfelwurf: " + diceRoll);
                    break;
                case "move":
                    System.out.println("Bitte geben Sie die Feldnummer ein, auf die Sie ziehen möchten:");
                    int fieldNumber = scanner.nextInt();
                    movePlayer(fieldNumber);
                    break;
                case "skip":
                    skipTurn();
                    break;
                default:
                    System.out.println("Unbekannter Befehl. Bitte geben Sie einen gültigen Befehl ein.");
            }
        }
    }

    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }

    private void movePlayer(int fieldNumber) {
        Field targetField = board.getField(fieldNumber);
        if (targetField!= null) {
            if (targetField.isObstacle()) {
                System.out.println("Feld ist ein Hindernis!");
            } else {
                currentPlayer.setCurrentField(targetField);
                System.out.println("Spieler bewegt sich auf Feld " + fieldNumber);
            }
        } else {
            System.out.println("Feld nicht gefunden!");
        }
    }

    private void skipTurn() {
        currentPlayer = players.get((players.indexOf(currentPlayer) + 1) % players.size());
        System.out.println("Zug übersprungen. Aktueller Spieler: " + currentPlayer.getName());
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}