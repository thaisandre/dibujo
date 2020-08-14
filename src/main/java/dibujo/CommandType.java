package dibujo;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.regex.Pattern.compile;

public enum CommandType {
    CREATE_CANVAS("C", compile("^C (\\d+) (\\d+)$")) {
        public Command buildCommand(String line) {
            Matcher matcher = this.getPattern().matcher(line);
            if (matcher.find()) {
                int width = Integer.parseInt(matcher.group(1));
                int height = Integer.parseInt(matcher.group(2));
                return new CreateCanvas(width, height);
            } else {
                throw new RuntimeException("Invalid parameters for the create new canvas command. Should be: C <width> <height>");
            }
        }
    },
    DRAW_LINE("L", compile("^L (\\d+) (\\d+) (\\d+) (\\d+)$")) {
        public Command buildCommand(String line) {
            Matcher matcher = getPattern().matcher(line);
            if (matcher.find()) {
                int startingX = Integer.parseInt(matcher.group(1));
                int startingY = Integer.parseInt(matcher.group(2));
                int endingX = Integer.parseInt(matcher.group(3));
                int endingY = Integer.parseInt(matcher.group(4));
                return new NewLine(startingX, startingY, endingX, endingY);
            } else {
                throw new RuntimeException("Invalid parameters for the create new line command. Should be: L <starting x> <starting y> <ending x> <ending y>");
            }
        }
    },
    DRAW_RECTANGLE("R", compile("^R (\\d+) (\\d+) (\\d+) (\\d+)$")) {
        public Command buildCommand(String line) {
            Matcher matcher = getPattern().matcher(line);
            if (matcher.find()) {
                int upperLeftCornerX = Integer.parseInt(matcher.group(1));
                int upperLeftCornerY = Integer.parseInt(matcher.group(2));
                int lowerRightCornerX = Integer.parseInt(matcher.group(3));
                int lowerRightCornerY = Integer.parseInt(matcher.group(4));
                return new Rectangle(upperLeftCornerX, upperLeftCornerY, lowerRightCornerX, lowerRightCornerY);
            } else {
                throw new RuntimeException("Invalid parameters for the create new rectangle command. Should be: L <upper left corner x> <upper left corner y> <lower right corner x> <lower right corner y>");
            }
        }
    },
    FILL("B", compile("^B (\\d+) (\\d+) (\\w+)$")) {
        public Command buildCommand(String line) {
            Matcher matcher = getPattern().matcher(line);
            if (matcher.find()) {
                int startingX = Integer.parseInt(matcher.group(1));
                int startingY = Integer.parseInt(matcher.group(2));
                String colorCharacter = matcher.group(3);

                Position startingPositionToFill = new Position(startingX, startingY);
                return new Fill(startingPositionToFill, colorCharacter);
            } else {
                throw new RuntimeException("Invalid parameters for the bucket fill command. Should be: B <starting x> <starting y> <color>");
            }
        }
    },
    EXIT("Q", compile("^Q")) {
        public Command buildCommand(String line) {
            System.out.println("Bye bye!");
            System.exit(0);
            return null;
        }
    };

    private String startsWith;
    private Pattern pattern;

    CommandType(String startsWith, Pattern pattern) {
        this.startsWith = startsWith;
        this.pattern = pattern;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public static Optional<CommandType> findCommand(String line) {
        return Stream.of(CommandType.values())
                .filter(c -> line.startsWith(c.startsWith))
                .findFirst();
    }

    public abstract Command buildCommand(String line);
}
