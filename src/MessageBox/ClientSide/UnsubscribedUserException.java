package MessageBox.ClientSide;

public class UnsubscribedUserException extends Exception{
    private final String message;

    public UnsubscribedUserException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
