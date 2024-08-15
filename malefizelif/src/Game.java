import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private int currentPlayerIndex;
    private boolean hasRolled;
    private Random random;

    public Game(long seed) {
        board = new Board();
        players = new ArrayList<>();
        currentPlayer = null;
        currentPlayerIndex = 0;
        hasRolled = false;
        if (seed!= 0) {
            random = new Random(seed);
        } else {
            random = null;
        }
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
                    System.out.println("Verf�gbare Befehle: help, show, roll, move, skip");
                    break;
                case "show":
                    board.showBoard();
                    break;
                case "roll":
                    if (hasRolled) {
                        System.out.println("Sie haben bereits gew�rfelt. Bitte geben Sie einen anderen Befehl ein.");
                        break;
                    }
                    System.out.println("Bitte geben Sie den W�rfelwurf ein (optional):");
                    String diceRoll = scanner.nextLine().trim();
                    if (diceRoll.isEmpty()) {
                        diceRoll = null;
                    }
                    int roll = rollDice(diceRoll);
                    if (roll!= -1) {
                        System.out.println("W�rfelwurf: " + roll);
                        hasRolled = true;
                    }
                    break;
                case "move":
                    System.out.println("Bitte geben Sie die Feldnummer ein, auf die Sie ziehen m�chten:");
                    int fieldNumber = scanner.nextInt();
                    movePlayer(fieldNumber);
                    break;
                case "skip":
                    skipTurn();
                    break;
                default:
                    System.out.println("Unbekannter Befehl. Bitte geben Sie einen g�ltigen Befehl ein.");
            }
        }
    }

    private int rollDice(String diceRoll) {
        if (random!= null) {
            return random.nextInt(6) + 1;
        } else {
            try {
                int roll = Integer.parseInt(diceRoll);
                if (roll < 1 || roll > 6) {
                    System.out.println("Ung�ltiger W�rfelwurf. Bitte geben Sie eine Zahl zwischen 1 und 6 ein.");
                    return -1;
                }
                return roll;
            } catch (NumberFormatException e) {
                System.out.println("Ung�ltiger W�rfelwurf. Bitte geben Sie eine Zahl ein.");
                return -1;
            }
        }
    }

    private void movePlayer(int fieldNumber) {
        Field targetField = board.getField(fieldNumber);
        if (targetField!= null) {
            currentPlayer.setCurrentField(targetField);
            System.out.println("Spieler " + currentPlayer.getName() + " ist auf Feld " + fieldNumber + " gelandet.");
        } else {
            System.out.println("Feld " + fieldNumber + " existiert nicht.");
        }
    }

    private void skipTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        currentPlayer = players.get(currentPlayerIndex);
        hasRolled = false;
        System.out.println("Spieler " + currentPlayer.getName() + " ist an der Reihe.");
    }

    public static void main(String[] args) {
        long seed = 0; // 0 f�r keinen Seed, oder eine beliebige Zahl f�r einen Seed
        Game game = new Game(seed);
        game.startGame();
    }
}