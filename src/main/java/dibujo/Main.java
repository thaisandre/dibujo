package dibujo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Canvas canvas = null;
                    Optional<Command> command = Command.findCommand(line);
                    command.ifPresent(c -> c.execute(canvas, line));

                    if(command.isEmpty()) {
                        err.println("Invalid command: " + line + "\n");
                    }

                    out.println();

                    out.print(canvas.draw());

                    out.println();

                    out.print("\nenter command: ");

                } catch (Exception ex) {
                    err.println(ex.getMessage()+"\n");
                    out.print("\nenter command: ");
                }
            }
        }
    }



    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

