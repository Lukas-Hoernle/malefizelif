public class Field {
    private int x;
    private int y;
    private boolean isObstacle;
    private Player player;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        isObstacle = false;
        player = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}