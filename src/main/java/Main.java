

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static String inputValue;
    private static String[] arab = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private static String[] rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private static String[] symbol = {"+", "-", "*", "/"};

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        inputValue = scanner.nextLine();
        String[] box = inputValue.split(" ");
        scanner.close();
        if (!isCheckLineLength(box)) {
            throw new Exception();
        } else if (!isCheckLineSymbol(box)) {
            throw new Exception();
        } else if (!isCheckLineArab(box)) {
            if (!isCheckLineRim(box)) {
                throw new Exception();
            }
        }
    }

    public static void calc(String input, boolean value) throws Exception {
        String[] splitValue = input.split(" ");
        int firstValue = 0;
        int secondValue = 0;
        if (value) {
            firstValue = Integer.parseInt(splitValue[0]);
            secondValue = Integer.parseInt(splitValue[2]);
            switch (splitValue[1]) {
                case "+" :
                    System.out.println(firstValue + secondValue);
                    break;
                case "-" :
                    System.out.println(firstValue - secondValue);
                    break;
                case "*" :
                    System.out.println(firstValue * secondValue);
                    break;
                case "/" :
                    System.out.println(firstValue / secondValue);
                    break;
            }
        } else {
            for (int e = 0; e < rim.length; e++) {
                if (rim[e].equals(splitValue[0])) {
                    firstValue = e + 1;
                    if (rim[e].equals(splitValue[2])) {
                        secondValue = e + 1;
                    }
                } else if (rim[e].equals(splitValue[2])) {
                    secondValue = e + 1;
                }
            }
            switch (splitValue[1]) {
                case "-" : if ((firstValue - secondValue) <= 0) {
                    throw new Exception();
                } else System.out.println(RomanNumber.toRoman(firstValue - secondValue));
                    break;

                case "/" : if ((firstValue / secondValue) <= 0){
                    throw new Exception();
                } else System.out.println(RomanNumber.toRoman(firstValue / secondValue));
                    break;

                case "*" :
                    System.out.println(RomanNumber.toRoman(firstValue * secondValue));
                    break;

                case "+" :
                    System.out.println(RomanNumber.toRoman(firstValue + secondValue));
                    break;
            }
        }
    }

    public static String converterToRim(int value) {

        return null;
    }

    public static boolean isCheckLineLength(String[] q) throws Exception {
        if (q.length != 3) {
            return false;
        } else return true;
    }
    public static boolean isCheckLineArab(String[] q) throws Exception {
        Arrays.sort(arab);
        if ((Arrays.binarySearch(arab, q[0]) >= 0) && (Arrays.binarySearch(arab, q[2]) >= 0)) {
            calc(inputValue, true);
            return true;
        }
        return false;
    }
    public static boolean isCheckLineRim(String[] q) throws Exception {
        int firstIndex = -1;
        int secondIndex = -1;
        for (int w = 0; w < rim.length; w++){
            if (q[0].equals(rim[w])) {
                firstIndex = w + 1;
                if (q[2].equals(rim[w])) {
                    secondIndex = w + 1;
                }
            } else if (q[2].equals(rim[w])) {
                secondIndex = w + 1;
            }
        }
        if (firstIndex != -1 && secondIndex != -1) {
            calc(inputValue, false);
            return true;
        }
        return false;
    }
    public static boolean isCheckLineSymbol(String[] q) {
        Arrays.sort(symbol);
        if (Arrays.binarySearch(symbol, q[1]) < 0) {
            return false;
        }
        return true;
    }

    public static class RomanNumber {

        private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

        static {

            map.put(1000, "M");
            map.put(900, "CM");
            map.put(500, "D");
            map.put(400, "CD");
            map.put(100, "C");
            map.put(90, "XC");
            map.put(50, "L");
            map.put(40, "XL");
            map.put(10, "X");
            map.put(9, "IX");
            map.put(5, "V");
            map.put(4, "IV");
            map.put(1, "I");

        }

        public final static String toRoman(int number) {
            int l = map.floorKey(number);
            if (number == l) {
                return map.get(number);
            }
            return map.get(l) + toRoman(number - l);
        }
    }
}
