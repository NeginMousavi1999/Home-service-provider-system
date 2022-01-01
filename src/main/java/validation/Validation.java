package validation;

import java.util.regex.Pattern;

/**
 * @author Negin Mousavi
 */
public class Validation {
    public boolean validateEmail(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (!Pattern.matches(regex, email))
            throw new RuntimeException("invalid email!");
        return true;
    }

    public boolean validatePassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$";
        if (!Pattern.matches(regex, password))
            throw new RuntimeException("the password must be at least 8 character, with a lower case, an upper case and no whitespace");
        return true;
    }
}
