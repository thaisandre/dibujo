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
}
