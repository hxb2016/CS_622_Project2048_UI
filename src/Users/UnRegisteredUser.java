package Users;

/**
 * The purpose of this class is to create an unregistered user
 */
public class UnRegisteredUser extends User {

    public UnRegisteredUser(String username) {
        this.username = username;
    }

    @Override
    public String getInformation() {
        return "You have not registered.";
    }

    /**
     * The purpose of getType method is to get type of user object
     */
    @Override
    public String getType() {
        return "UnRegisteredUser";
    }

    @Override
    public void setIntroduce(String introduction) {
        this.introduce = "You have not registered.";
    }

}


