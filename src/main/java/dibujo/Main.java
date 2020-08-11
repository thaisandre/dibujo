package dibujo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private Canvas canvas;

    public void run(InputStream in, PrintStream out, PrintStream err) {
        try (Scanner scanner = new Scanner(in)) {
            out.print("\nenter command: ");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Optional<Command> command = Command.findCommand(line);


                    if(command.isEmpty()) {
                        err.println("Invalid command: " + line + "\n");
                    }else{
                        command.get().execute(this, line);
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

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public static void main(String[] args) {
        new Main().run(System.in, System.out, System.err);
    }
}

