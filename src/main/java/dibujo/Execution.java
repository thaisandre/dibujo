package dibujo;

public class Execution {
    private Canvas canvas;

    public String drawCanvas() {
        return canvas.draw();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void execute(Command command) {
        if(!hasCanvas() && !(command instanceof CreateCanvas)) throw new RuntimeException(command.getErrorMessage());
        command.execute(this);
    }

    public boolean hasCanvas() {
        return canvas !=null;
    }
}
