package dibujo;

public interface Command {

    void execute(Execution execution);

    default String getErrorMessage(){
        return "Something went wrong";
    }

    default void validateCanvas(Execution execution){
        if(!execution.hasCanvas())  throw new RuntimeException(this.getErrorMessage());
    }
}
