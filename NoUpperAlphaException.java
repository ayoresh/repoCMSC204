package sample;

public class NoUpperAlphaException extends Exception{

    public NoUpperAlphaException(String toDisplay){
        super(toDisplay);
    }
}
