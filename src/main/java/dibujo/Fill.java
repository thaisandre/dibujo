package dibujo;

public class Fill implements Command {
    private final Position startingPositionToFill;
    private final String colorCharacter;

    public Fill(Position startingPositionToFill, String colorCharacter) {
        this.startingPositionToFill = startingPositionToFill;
        this.colorCharacter = colorCharacter;
    }

    @Override
    public void execute(Execution execution) {
        validateCanvas(execution);
        execution.getCanvas().fill(startingPositionToFill, colorCharacter);
    }

    @Override
    public String getErrorMessage() {
        return "No canvas. You should create a canvas before filling it.";
    }
}
