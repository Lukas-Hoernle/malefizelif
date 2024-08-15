public class Board {
    private Field[][] fields;
    private int width;
    private int height;

    public Board() {
        fields = new Field[10][10];
        width = 10;
        height = 10;
    }

    public void setField(Field field, int x, int y) {
        fields[x][y] = field;
    }

    public Field getField(int x, int y) {
        return fields[x][y];
    }

    public void showBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Field field = fields[i][j];
                if (field.getObstacle()) {
                    System.out.print("X ");
                } else if (field.getPlayer()!= null) {
                    System.out.print(field.getPlayer().getName().substring(0, 2) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    public Field getField(int fieldNumber) {
        int x = fieldNumber / width;
        int y = fieldNumber % width;
        if (x >= 0 && x < height && y >= 0 && y < width) {
            return fields[x][y];
        } else {
            return null;
        }
    }
}