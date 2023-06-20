import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int a;
        int b;
        boolean isThisRomanDigits;
        String answerForQuestion;
        input = input.replaceAll(" ", "");
        String[] mass = input.split("[+/*-]");
        String sign = whatSign(input);
        if (sign == null) throw new Exception("Вы ввели недопустимуюяя математическую операцию");
        if (mass.length > 2) throw new Exception("Вы ввели более двух чисел");
        if (mass.length < 2) throw new Exception("Вы ввели менее двух чисел");
        if (RomanDigits.isThisRomanDigit(mass[0]) && RomanDigits.isThisRomanDigit(mass[1])) {
            a = RomanDigits.convertationArab(mass[0]);
            b = RomanDigits.convertationArab(mass[1]);
            isThisRomanDigits = true;
        } else if (!RomanDigits.isThisRomanDigit(mass[0]) && !RomanDigits.isThisRomanDigit(mass[1])) {
            a = Integer.parseInt(mass[0]);
            b = Integer.parseInt(mass[1]);
            isThisRomanDigits = false;
        } else throw new Exception("Используются одновременно разные системы счисления");
        if (a > 10 || b > 10) {
            throw new Exception("Числа принимаются от 1 до 10");
        }
        int answer = result(a, sign, b);
        if (isThisRomanDigits) {
            if (answer <= 0) {
                throw new Exception("Римское выражение меньше или равно нулю");
            }
            answerForQuestion = RomanDigits.convertationRoman(answer);
        } else {
            answerForQuestion = String.valueOf(answer);
        }
        return answerForQuestion;
    }

    static String whatSign(String expression) {
        if (expression.contains("+")) {
            return "+";
        } else if (expression.contains("-")) {
            return "-";
        } else if (expression.contains("*")) {
            return "*";
        } else if (expression.contains("/")) {
            return "/";
        } else return null;
    }

    static int result(int a, String sign, int b) {
        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}

class RomanDigits {
    static String[] romanDigits = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV",
            "XVI", "XVII", "XVIII", "XIX", "XX", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XXIX", "XXX", "XXXI", "XXXII",
            "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII",
            "XLVIII", "XLVIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV",
            "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII",
            "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "CX", "CXI",
            "CXII", "CXIII", "CXIV", "CXV", "CXVI", "CXVII", "CXVIII", "CXIX", "C"};


    static boolean isThisRomanDigit(String index) {
        for (int i = 0; i < romanDigits.length; i++) {
            if (index.equals(romanDigits[i])) {
                return true;
            }
        }
        return false;
    }

    static int convertationArab(String index) {
        for (int i = 0; i < romanDigits.length; i++) {
            if (index.equals(romanDigits[i])) {
                return i;
            }
        }
        return 0;
    }

    static String convertationRoman(int answer) {
        return romanDigits[answer];
    }

}