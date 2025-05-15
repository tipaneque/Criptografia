package ciphers;

public class Vigenere implements Cripto{
    /*
        Call this method to encrypt the message
        Give to it a plain text and a key
     */
    @Override
    public String enc(String message, String key){
        return buildCipher(getCleanedText(message), getCleanedText(key));
    }

    /*
        Call this method to decrypt the message
        Give to it an encrypted message and its corresponding key
     */
    @Override
    public String dec(String cipher, String key){

        return buildMessage(getCleanedText(cipher), getCleanedText(key));
    }

    /*
        Method for spaces removal and letters capitalization
        We do NOT want to work with lowercase letters
     */
    private String getCleanedText(String text){
        return text.trim().replace(" ", "").toUpperCase();
    }

    //Cipher builder
    private String buildCipher(String message, String key){
        int [] cipherIndexes = getCipherIndex(message, key);
        return buildText(cipherIndexes);
    }

    //Message builder
    private String buildMessage(String cipher, String key){
        int [] messageIndexes = getMessageIndex(cipher, key);
        return buildText(messageIndexes);

    }

    //It builds any text
    private String buildText(int [] indexes){
        StringBuilder message = new StringBuilder();
        for(int index: indexes){
            message.append(LETTERS[index]);
        }
        return message.toString();
    }


     /*
        It's here where we apply the Vigenere's cipher;
        We give to the method a message and a key, and it calculates the index of ciphered letter
      */
    private int [] getCipherIndex(String message, String key){
        int [] cipherIndexes = new int[message.length()];
        char [] equalizedKey = getEqualizedKey(key, message.length());

        for(int i = 0; i < message.length(); i++){
            int m = getIndex(message.charAt(i));
            int k = getIndex(equalizedKey[i]);
            cipherIndexes[i] = (m + k) % 26;
        }
        return cipherIndexes;
    }

    /*
        It's here where we apply the Vigenere's cipher;
        We give to the method a ciphered message and it's key, and it calculates the index of the plain text letter
     */
    private int [] getMessageIndex(String cipher, String key){
        int [] messageIndexes = new int[cipher.length()];
        char [] equalizedKey = getEqualizedKey(key, cipher.length());

        for(int i = 0; i < cipher.length(); i++){
            int c = getIndex(cipher.charAt(i));
            int k = getIndex(equalizedKey[i]);
            messageIndexes[i] = ((c - k < 0) ? (c - k + 26) : c - k) % 26;
        }
        return messageIndexes;
    }

    private int getIndex(char letter){
        for(int i = 0; i < LETTERS.length; i++){
            if(letter == LETTERS[i]) return i;
        }
        return -1;
    }


    //A method used to equalize the key length to match with te message length
    private char [] getEqualizedKey(String key, int textLength){
        char [] arrayKey = new char[textLength];
        int k = 0;

        for(int i = 0; i < arrayKey.length; i++){
            if(i % key.length() == 0){
                k = 0;
            }
            arrayKey[i] = key.charAt(k);
            k++;
        }
        return arrayKey;
    }
}
