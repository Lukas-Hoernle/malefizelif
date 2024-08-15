public class Board {
    private Field[][] fields;

    public Board() {
        fields = new Field[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fields[i][j] = new Field(i, j);
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Field field = fields[i][j];
                if (field.isObstacle()) {
                    System.out.print("X ");
                } else if (field.getPlayer()!= null) {
                    System.out.print("P ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public Field getField(int fieldNumber) {
        int x = fieldNumber / 10;
        int y = fieldNumber % 10;
        return fields[x][y];
    }
}