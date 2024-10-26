// 231RDB234 Germans Veidemans 3

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class proj {
  static Scanner sc;


  public static void decomp(String str){
    String user = sc.nextLine();
    String[] part = user.split(" ");
    int letter_count = Integer.parseInt(part[1]);
    int size = Integer.parseInt(part[2]);
    int len = part.length-2;
    if (len != letter_count) {
      System.out.println("wrong command format");
      check();
    }
    try {
      byte[] input = new byte[letter_count];
      for (int i = 0; i < letter_count; i++) {
        int integer = Integer.parseInt(part[i+2]);
        if (integer < -127 || integer >127) {
          System.out.println("wrong command format");
          check();
        }
        byte IntToByte = (byte) integer;
        input[i] = IntToByte;
      }

      StringBuilder ByteToHex = new StringBuilder();
      int first = 1;
      for (byte b : input) {
        if (first == 1) {
          ByteToHex.append(String.format("%1X" + " ", b));
          first = 2;
        } else {
          ByteToHex.append(String.format("%02X" + " ", b));
        }
      }
      System.out.println(ByteToHex);
      int index = 1;
      StringBuilder result = new StringBuilder();
      for (byte b : input) {
        if (index != 1){
          result.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(" ", "0" ));
        }
        index = 2;
      }

      int zeros = (letter_count * 8)-result.length();

      if (zeros > 0) {
        for(int i = 0; i < zeros; i++){
          result.append("0");
        }
      } else { 
        System.out.println("wrong command format");
        return;
      }
      Map<String, Character> letters = new HashMap<>();
      letters.put("00", 'A');
      letters.put("01", 'C');
      letters.put("10", 'G');
      letters.put("11", 'T');

      StringBuilder letter_result = new StringBuilder();

      for (int i = 0; i < size * 2; i += 2) {
          String twoBits = result.substring(i, i + 2);
          letter_result.append(letters.get(twoBits));
      }

      System.out.println(letter_result.toString());
      check();
    } catch (Exception e) {
      System.out.println("wrong command format");
      check();
    }
  }





  public static void comp(String str){
    if (CompIsValid(str)) {
        Integer count = str.length();
        byte[] byteFormat = new byte[(count + 3) /4];
        int index = 0;
        byte currentByte = 0;
        int bitCount = 0;

        for (char letter : str.toCharArray()) {
            switch (letter) {
            case 'A':
                currentByte <<= 2;
                bitCount += 2;
                break;
            case 'C':
                currentByte <<= 2;
                currentByte |= 0b01;
                bitCount += 2;
                break;
          case 'G':
                currentByte <<= 2;
                currentByte |= 0b10;
                bitCount += 2;
                break;
          case 'T':
                currentByte <<= 2;
                currentByte |= 0b11;
                bitCount += 2;
                break;
            default:
                break;
            }   

            if (bitCount >= 8) {
                byteFormat[index++] = currentByte;
                currentByte = 0;
                bitCount = 0;
            }
        }

        if (bitCount > 0) {
            currentByte <<= (8 - bitCount);
            byteFormat[index++] = currentByte;
        }

        StringBuilder ByteToStr = new StringBuilder();
        for (int i = 0; i<byteFormat.length; i += 2) {
            int decimal = (byteFormat[i] << 8) | (byteFormat[i+1] & 0xFF);
            String hex = Integer.toHexString(decimal).toUpperCase();
            ByteToStr.append(hex).append(" ");
        }
        String[] result = {ByteToStr.substring(0, 2), ByteToStr.substring(2)};
        System.out.println(count + " " + result[0] + " " + result[1]);
    } else {
      System.out.println("wrong command format");
    }
    check();
}


  public static boolean CompIsValid(String input){
    input = input.toUpperCase();
    for(int i = 0; i<input.length(); i++){
      char ch = input.charAt(i);
      if (ch!= 'A' && ch != 'C' && ch != 'G' && ch != 'T'){
        return false;
      }
    }
    return true;
  }


  public static void about(){
    System.out.println("231RDB234 Germans Veidemans 3");
    check();
  }
  

  public static void check(){
    sc = new Scanner(System.in);
    String user_command = sc.next();
    String user_text = new String();
    switch(user_command){
      case "comp":
      user_text = sc.next();
        comp(user_text);
        break;
      case "decomp":
        decomp(user_text);
        break;
      case "about":
        about();
        break;
      case "exit":
        System.exit(0);
    }
  }

  public static void main(String[] args) {
    check();
  }
}