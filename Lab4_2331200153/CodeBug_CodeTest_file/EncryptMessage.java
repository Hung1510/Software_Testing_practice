package Class;

public class EncryptMessage {

    public String Encryption(String message) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        char currentChar, nextChar;
        int length = message.length();
        for (int i = 0; i < length - 1; i = i + 2) {
            currentChar = message.charAt(i);
            nextChar = message.charAt(i + 1);
            sb1.append(nextChar);
            sb1.append(currentChar);
        }
        //Incorrect odd/even logic, correct should be odd not even
        //if (length % 2 == 0) {
        if (length % 2 == 1) {
            sb1.append(message.charAt(length - 1));
        }

        for (int i = 0; i < sb1.length(); i++) {
            currentChar = sb1.charAt(i);
            if (currentChar > 'z' || currentChar < 'a')
                return "invalid";
            else {
                // wrong way to do character substitute, should be - and not +
                //currentChar = (char) ('z' + 'a' + currentChar);
                currentChar = (char) ('z' + 'a' - currentChar);
                sb2.append(currentChar);
            }
        }
        return sb2.toString();

    }

}
