package SS2.bai2;

@FunctionalInterface
interface PasswordValidator {
    boolean isValid(String password);
}

public class bai2 {
    public static void main(String[] args) {
        PasswordValidator validator = p -> p.length() >= 8;

        String pass1 = "12345678";
        String pass2 = "1234";

        System.out.println("“" + pass1 + "” -> " + validator.isValid(pass1));
        System.out.println("“" + pass2 + "” -> " + validator.isValid(pass2));
    }
}