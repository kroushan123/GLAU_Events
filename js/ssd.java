
import java.util.Scanner;
public class Main {
    private static int modInverse (int k1, int m) {
        for (int i = 1; i < m; i++)
            if ((k1 * i) % m == 1)
                return i;
        return -1;
    }
private static String encrypt(String plaintext, int k1, int k2) { 
    StringBuilder ciphertext = new StringBuilder();
    for (char ch : plaintext.toCharArray()) {
        if (Character.isLetter (ch)) {
            int x = (Character.toUpperCase (ch) - 'A');
            int encryptedChar = (k1 * x + k2) % 26;
            ciphertext.append((char) (encryptedChar + 'A'));
        } else {
            ciphertext.append(ch);
        }
    }
    return ciphertext.toString();
}    

private static String decrypt(String ciphertext, int k1, int k2) { 
    StringBuilder decryptedText = new StringBuilder();
    int aInverse = modInverse (k1, 26);
    if (aInverse == -1) {
        System.out.println("Invalid key. The key must be chosen such that 'k1' and 'm' are coprime.");
        return "";
    }
    for (char ch : ciphertext.toCharArray()) {
        if (Character.isLetter(ch)) {
            int y = (Character.toUpperCase(ch) - 'A');
            int decryptedChar = (aInverse * (y - k2 + 26)) % 26;
            decryptedText.append((char) (decryptedChar + 'A'));
        } else {
            decryptedText.append(ch);
        }
    }
    return decryptedText.toString();
}


public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the plaintext: ");
    String plaintext = scanner.nextLine().toUpperCase();
    System.out.print ("Enter the value of 'k1' (must be coprime with 26): ");
    int k1 = scanner.nextInt();
    System.out.print("Enter the value of 'k2': ");
    int k2 = scanner.nextInt();
    if (k1 < 0 || k1>= 26 || gcd (k1, 26) != 1) {
        System.out.println("Invalid value of 'kl'. It must be coprime with 26.");
        return;
    }
    String encryptedText = encrypt(plaintext, k1, k2);
    System.out.println("Encrypted Text: " + encryptedText);
    String decryptedText = decrypt(encryptedText, k1, k2);
    System.out.println("Decrypted Text: " + decryptedText);
}
private static int gcd(int k1, int k2) {
    if (k2 == 0)
        return k1;
    return gcd (k2, k1 % k2);
}
}
