import java.util.Scanner;
import java.math.*;

/*public class PasswordAnalyzer {
	public static double calculate_entropy(String password){
		int character_count = 0;
		if(password.matches(".*[A-Z]*.")) {
			character_count += 26;
		}
		if(password.matches(".*[a-z]*.")) {
			character_count += 26;
		}
		if(password.matches(".*[0-9]*.")) {
			character_count += 26;
		}
		if(password.matches(".*[^a-zA-Z0-9]*.")) {
			character_count += 32;
		}
		
		double entropy = password.length() * (Math.log(character_count) / Math.log(2));
		return entropy;
	}
	
	public String passwordstrength(double entropy) {
		if(entropy < 20) {
			return "weak";
		}
		if(entropy > 20 && entropy < 45) {
			return "moderate";
		}
		if(entropy > 45) {
			return "strong";
		}
	}
}*/

public class PasswordAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        int length = password.length();
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSymbol = password.matches(".*[^a-zA-Z0-9].*");

        int charsetSize = 0;
        if (hasLower) charsetSize += 26;
        if (hasUpper) charsetSize += 26;
        if (hasDigit) charsetSize += 10;
        if (hasSymbol) charsetSize += 32;

        double entropy = 0;
        if (charsetSize > 0)
            entropy = length * (Math.log(charsetSize) / Math.log(2));

        String rating;
        if (entropy < 28)
            rating = "Weak";
        else if (entropy < 50)
            rating = "Moderate";
        else
            rating = "Strong";

        System.out.println("\n🔍 Password Analysis");
        System.out.println("Length: " + length);
        System.out.println("Contains lowercase: " + hasLower);
        System.out.println("Contains uppercase: " + hasUpper);
        System.out.println("Contains digits: " + hasDigit);
        System.out.println("Contains symbols: " + hasSymbol);
        System.out.printf("Entropy: %.2f bits\n", entropy);
        System.out.println("Strength: " + rating);
    }
}