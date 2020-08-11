package dibujo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.regex.Pattern.compile;

public enum Command {
    CREATE_CANVAS("C", compile("^C (\\d+) (\\d+)$")) {
        public void execute(Main main, String line) {
            Matcher matcher = this.getPattern().matcher(line);
            if (matcher.find()) {
                int width = Integer.parseInt(matcher.group(1));
                int height = Integer.parseInt(matcher.group(2));

                main.setCanvas(new Canvas(width, height));
            } else {
                throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
            }
        }
    },
    DRAW_LINE("L", compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$")) {
        public void execute(Main main, String line) {
            if (main == null) {
                throw new RuntimeException("No canvas. You should create a canvas before creating a new line.");
            }
            Matcher matcher = getPattern().matcher(line);
            if (matcher.find()) {
                int startingX = Integer.parseInt(matcher.group(1));
                int startingY = Integer.parseInt(matcher.group(2));
                int endingX = Integer.parseInt(matcher.group(3));
                int endingY = Integer.parseInt(matcher.group(4));

                main.getCanvas().createNewLine(startingX, startingY, endingX, endingY);
            } else {
                throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
            }
        }
    },
    DRAW_RECTANGLE("R", compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$")) {
        public void execute(Main main, String line) {
            if (main == null) {
                throw new RuntimeException("No canvas. You should create a canvas before creating a new rectangle.");
            }

            Matcher matcher = getPattern().matcher(line);
            if (matcher.find()) {
                int upperLeftCornerX = Integer.parseInt(matcher.group(1));
                int upperLeftCornerY = Integer.parseInt(matcher.group(2));
                int lowerRightCornerX = Integer.parseInt(matcher.group(3));
                int lowerRightCornerY = Integer.parseInt(matcher.group(4));

                main.getCanvas().createNewRectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
            } else {
                throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
            }
        }
    },
    FILL("B", compile("^B (\\d+) (\\d+) (\\w+)$")) {
        public void execute(Main main, String line) {
            if (main == null) {
                throw new RuntimeException("No canvas. You should create a canvas before filling it.");
            }

            Matcher matcher = getPattern().matcher(line);
            if (matcher.find()) {
                int startingX = Integer.parseInt(matcher.group(1));
                int startingY = Integer.parseInt(matcher.group(2));
                String colorCharacter = matcher.group(3);

                Position startingPositionToFill = new Position(startingX, startingY);
                main.getCanvas().fill(startingPositionToFill, colorCharacter);
            } else {
                throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
            }
        }
    },
    EXIT("Q", compile("^Q")) {
        public void execute(Main main, String line) {
            System.out.println("Bye bye!");
            System.exit(0);
        }
    };

    private String startsWith;
    private Pattern pattern;

    Command(String startsWith, Pattern pattern) {
        this.startsWith = startsWith;
        this.pattern = pattern;
    }

    public boolean matches(String line) {
        return this.pattern.matcher(line).matches();
    }

    public Pattern getPattern() {
        return pattern;
    }

    public static Optional<Command> findCommand(String line) {
        return Stream.of(Command.values())
                .filter(c -> line.startsWith(c.startsWith))
                .findFirst();
    }

    public abstract void execute(Main main, String line);
}
