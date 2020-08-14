package dibujo;

public class DrawCanvas {

    public String toText(Canvas canvas) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i <= canvas.getWidth(); i++) {
            builder.append("-");
        }
        builder.append("-");

        builder.append("\n");

        int x = 1, y = 1;
        while (x >= 1 && x <= canvas.getWidth() && y >= 1 && y <= canvas.getHeight()) {
            Position position = canvas.getPosition(x, y);
            if (position.getX() == 1) {
                if (position.getY() > 1) {
                    builder.append("|\n");
                }
                builder.append("|");
            }

            if (position.getColor() != null) {
                builder.append(position.getColor());
            } else if (position.isFilled()) {
                builder.append("x");
            } else {
                builder.append(" ");
            }
            x++;
            if (x >= canvas.getWidth() + 1) {
                x = 1;
                y++;
            }
        }

        builder.append("|\n");
        builder.append("-");

        for (int i = 0; i < canvas.getWidth(); i++) {
            builder.append("-");
        }
        builder.append("-");
        return builder.toString();
    }
}
