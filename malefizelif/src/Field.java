public class Field {
    private int x;
    private int y;
    private boolean obstacle;
    private Player player;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        this.obstacle = false;
        this.player = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}