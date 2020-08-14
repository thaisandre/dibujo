package dibujo;

public class Rectangle implements Command {
    private final int upperLeftCornerX;
    private final int upperLeftCornerY;
    private final int lowerRightCornerX;
    private final int lowerRightCornerY;

    public Rectangle(int upperLeftCornerX, int upperLeftCornerY, int lowerRightCornerX, int lowerRightCornerY) {
        this.upperLeftCornerX = upperLeftCornerX;
        this.upperLeftCornerY = upperLeftCornerY;
        this.lowerRightCornerX = lowerRightCornerX;
        this.lowerRightCornerY = lowerRightCornerY;
    }

    @Override
    public void execute(Execution execution) {
        validateCanvas(execution);
        execution.getCanvas().createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
    }

    @Override
    public String getErrorMessage() {
        return "No canvas. You should create a canvas before creating a new rectangle.";
    }
}
