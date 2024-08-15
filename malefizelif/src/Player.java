public class Player {
    private String name;
    private Field currentField;

    public Player(String name) {
        this.name = name;
        this.currentField = null;
    }

    public String getName() {
        return name;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field field) {
        this.currentField = field;
        field.setPlayer(this);
    }
}