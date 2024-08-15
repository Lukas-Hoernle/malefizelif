import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currentPlayer;
    private int currentPlayerIndex;

    public Game() {
        board = new Board();
        players = new ArrayList<>();
        currentPlayer = null;
        currentPlayerIndex = 0;
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
        // Pseudozufallszahlengenerator für den Würfelwurf
        return (int) (Math.random() * 6) + 1;
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
        System.out.println("Spieler " + currentPlayer.getName() + " ist an der Reihe.");
    }

    public void loadBoardFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#")) {
                    // Kommentarzeile, ignorieren
                    continue;
                }
                String[] fields = line.split(" ");
                for (int i = 0; i < fields.length; i++) {
                    Field field = new Field(lineNumber, i);
                    field.setObstacle(fields[i].equals("X"));
                    field.setPlayer(fields[i].equals("S")? currentPlayer : null);
                    board.setField(field, lineNumber, i);
                }
                lineNumber++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Datei nicht gefunden: " + filename);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.loadBoardFromFile("board.txt");
        game.startGame();
    }
}
		