import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;


public class user_interface extends JFrame {

    private JPasswordField passwordField;
    private JLabel strengthLabel;
    private JProgressBar strengthBar;

    public user_interface() {
        setTitle("Password Strength Analyzer");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use a clean vertical layout
        setLayout(new GridLayout(5, 1, 10, 10));

        // Label
        JLabel titleLabel = new JLabel("Enter your password:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Password field (masked input)
        passwordField = new JPasswordField(20);
        passwordField.setEchoChar('●');  // Custom mask character
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Button
        JButton analyzeButton = new JButton("Analyze");
        analyzeButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Strength label
        strengthLabel = new JLabel("Strength: ", SwingConstants.CENTER);
        strengthLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Strength bar
        strengthBar = new JProgressBar(0, 100);
        strengthBar.setStringPainted(true);
        strengthBar.setValue(0);

        // Add components
        add(titleLabel);
        add(passwordField);
        add(analyzeButton);
        add(strengthLabel);
        add(strengthBar);

        // Logic when button is clicked
        analyzeButton.addActionListener((ActionEvent e) -> analyzePassword());

        setVisible(true);
    }

    private void analyzePassword() {
        String password = new String(passwordField.getPassword());
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

        double entropy = (charsetSize > 0)
                ? length * (Math.log(charsetSize) / Math.log(2))
                : 0;

        updateUI(entropy);
        showCrackingInfo(password, entropy);
    }

    private void updateUI(double entropy) {
        String rating;
        int barValue;
        Color color;

        if (entropy < 28) {
            rating = "Weak";
            barValue = 25;
            color = Color.RED;
        } else if (entropy < 50) {
            rating = "Moderate";
            barValue = 60;
            color = Color.ORANGE;
        } else {
            rating = "Strong";
            barValue = 100;
            color = new Color(0, 170, 0);
        }

        strengthLabel.setText("Strength: " + rating);
        strengthBar.setValue(barValue);
        strengthBar.setForeground(color);
    }
    
    private String sha256(String input) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (Exception e) {
            return "Hash error";
        }
    }
    
    private String formatTime(double seconds) {
        if (seconds < 60) return String.format("%.2f seconds", seconds);
        if (seconds < 3600) return String.format("%.2f minutes", seconds / 60);
        if (seconds < 86400) return String.format("%.2f hours", seconds / 3600);
        if (seconds < 31557600) return String.format("%.2f days", seconds / 86400);
        if (seconds < 3.154e8) return String.format("%.2f years", seconds / 31557600);
        return String.format("%.2f centuries", seconds / (31557600 * 100));
    }
    private String argon2Hash(String password) {
        de.mkammerer.argon2.Argon2 argon2 = de.mkammerer.argon2.Argon2Factory.create();
        return argon2.hash(2, 65536, 1, password.toCharArray());
    }
    
    private void showCrackingInfo(String password, double entropy) {

        // guesses = 2^entropy
        double guesses = Math.pow(2, entropy);

        // attacker speeds
        double slow = guesses / 1_000_000;          // 1M guesses/sec
        double medium = guesses / 1_000_000_000;    // 1B guesses/sec
        double fast = guesses / 100_000_000_000L;   // 100B guesses/sec

        String hash = sha256(password);

        String message =
                "<html>" +
                "Entropy: " + String.format("%.2f", entropy) + " bits<br><br>" +

                "<b>Estimated Crack Time:</b><br>" +
                "• Slow PC (1M/s): " + formatTime(slow) + "<br>" +
                "• GPU Rig (1B/s): " + formatTime(medium) + "<br>" +
                "• Massive Cluster (100B/s): " + formatTime(fast) + "<br><br>" +

                "<b>SHA-256 Hash:</b><br>" +
                "<span style='font-size:10px'>" + hash + "</span>" +
                "</html>";

        JOptionPane.showMessageDialog(this, message, "Security Details", JOptionPane.INFORMATION_MESSAGE);
    }
    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(user_interface::new);
    }
}