package ciphers;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Playfair implements Cripto {

    @Override
    public String enc(String message, String key) {
        return "";
    }

    @Override
    public String dec(String cipher, String key) {
        return "";
    }

    private String ge(String text){
        return "";
    }

    private String getCleanText(String text){
        text = text.trim().replace(" ", "").toUpperCase();
        if(text.length() % 2 != 0) text = text.concat("X");

        return text;
    }

    public String getFinalTex(String text){
        text = getSeparatedText(text);
        String [] array = text.split("-");
        List<String> ls = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list = new ArrayList<>();
        Collections.addAll(list, array);

        for(String l : list){
            if(l.charAt(0) == l.charAt(1)){
                ls.add(String.valueOf(l.charAt(0)).concat("X"));
                ls.add(String.valueOf(l.charAt(1)).concat("X"));

            }else ls.add(l);
            list2.addAll(ls);
            ls.clear();
        }
        String sepText = "";
        for(String st: list2) sepText = sepText.concat(st);
        return getSeparatedText(sepText);
    }

    public String getSeparatedText(String txt){
        String text = getCleanText(txt);
        List<String> list = new ArrayList<>();
        String sepText = "";
        for(int i = 0; i < text.length(); i++){
            if(i % 2 == 0 && i != 0) list.add("-");
            list.add(String.valueOf(text.charAt(i)));
        }

        for(String st: list) sepText = sepText.concat(st);
        return sepText;
    }

    public char [][] getMatrix(String key){
        char [][] matrix = new char[5][5];
        String fullText = getFinalText(key);
        int k = 0;
        for(int i = 0; i <  5; i++){
            for(int j = 0; j < 5; j++){
                matrix[i][j] = fullText.charAt(k);
                k++;
            }
        }
        return matrix;
    }

    private String getFinalText(String key){
        List<Character> alphabet = new ArrayList<>();
        for(int i = 0; i < key.length(); i++){
            alphabet.add(key.charAt(i));
        }
        for(char c: LETTERS){
            alphabet.add(c);
        }
        removeChar(alphabet, 'J');

        return removeRepeated(buildText(alphabet));
    }

    private void removeChar(List<Character> chars, char c){
        for(int i = 0; i < chars.size(); i++){
            if(chars.get(i) == c){
                chars.remove(i);
                break;
            }
        }
    }
    public String removeRepeated(String text){
        List<Character> chars = stringToCharsList(text);

        for(int i = 0; i < chars.size(); i++){
            for(int j = i+1; j < chars.size(); j++){
                if(chars.get(i) == chars.get(j)){
                    chars.remove(j);
                }
            }
        }
        return buildText(chars);
    }

    private List<Character> stringToCharsList(String text){
        List<Character> chars = new ArrayList<>();
        for(int i = 0; i < text.length(); i++){
            chars.add(text.charAt(i));
        }
        return chars;
    }

    private String buildText(List<Character> chars){
        StringBuilder text = new StringBuilder();
        for(char c: chars){
            text.append(c);
        }
        return text.toString();
    }
}
