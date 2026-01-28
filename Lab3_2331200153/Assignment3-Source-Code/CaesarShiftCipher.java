

public class CaesarShiftCipher {

public String CaesarShift(String message, int shift){
    StringBuilder sb = new StringBuilder();
    char currentChar;
    int length = message.length();

    shift = shift%26;
 
    for(int i = 0; i < length; i++){   
        currentChar = message.charAt(i); 
       
      sb.append(currentChar); 
        
        
        if (currentChar > 'Z' || currentChar < 'A') {
            return "invalid"; 
        } else if ((char) (currentChar + shift) >= 'Z') {
            currentChar = (char) (currentChar - 26);
        } else if ((char) (currentChar + shift) <= 'A'){
            currentChar = (char) (currentChar + 26);
        }
        sb.append((char) (currentChar + shift)); 
    }

    return sb.toString();
}
}

