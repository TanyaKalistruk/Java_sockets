import java.io.Serializable;

public class UsersListMessage implements Serializable{
    private String message;

    public UsersListMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
