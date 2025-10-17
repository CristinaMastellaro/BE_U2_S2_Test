package cristinamastellaro.BE_U2_S2_Test.exceptions;

public class UsernameAlreadyUsed extends RuntimeException {
    public UsernameAlreadyUsed(String username) {
        super("The username " + username + " has already been used");
    }
}
