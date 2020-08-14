package dibujo;

public class NewLine implements Command {
    private final int startingX;
    private final int startingY;
    private final int endingX;
    private final int endingY;

    public NewLine(int startingX, int startingY, int endingX, int endingY) {
        if (startingX <= 0 || startingY <= 0 || endingX <= 0 || endingY <= 0) {
            throw new RuntimeException("Invalid parameters: the starting and ending coordinates should be greater than zero. Given parameters: " + this);
        }

        if (startingX != endingX && startingY != endingY ) {
            throw new RuntimeException("Invalid parameters: currently only horizontal or vertical lines are supported. Given parameters: " + this);
        }
        this.startingX = startingX;
        this.startingY = startingY;
        this.endingX = endingX;
        this.endingY = endingY;
    }

    @Override
    public void execute(Execution execution) {
        validateCanvas(execution);
        execution.getCanvas().createNewLine(startingX,startingY,endingX,endingY);
    }

    @Override
    public String getErrorMessage() {
        return "No canvas. You should create a canvas before creating a new line.";
    }
}
