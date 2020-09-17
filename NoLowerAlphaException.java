package sample;

public class NoLowerAlphaException extends Exception{

    public NoLowerAlphaException(String toDisplay){
        super(toDisplay);
    }

}
