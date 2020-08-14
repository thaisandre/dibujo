package dibujo;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class CreateCanvas implements Command {

    private final int width;
    private final int height;

    public CreateCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute(Execution execution) {
        execution.setCanvas(new Canvas(width, height));
    }

}
