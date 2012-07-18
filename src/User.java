public class User {
    private String password;
    private String id;
    private boolean loggedIn = false;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public Boolean isLoggedIn() {
        return loggedIn;
    }

    public void login() {
        loggedIn = true;
    }
}
