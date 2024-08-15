public class Player {
    private String name;
    private Field currentField;

    public Player(String name) {
        this.name = name;
        currentField = null;
    }

    public String getName() {
        return name;
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field field) {
        currentField = field;
    }
}