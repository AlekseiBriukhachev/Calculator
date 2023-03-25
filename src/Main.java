
import java.util.Scanner;

class Main extends NumbersConverter {
    static NumbersConverter numbersConverter = new NumbersConverter();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter expression:");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Calculator is finish.");
                break;
            }
            try {
                String result = calc(input);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                System.out.println("Calculator is finish with error.");
                break;
            }
        }
    }

    public static String calc(String input) throws Exception {

        String[] arguments = input.split(" ");
        if (arguments.length != 3) {
            throw new Exception("Invalid numbers of arguments");
        }
        String xArg = arguments[0];
        String opArg = arguments[1];
        String yArg = arguments[2];

        if (xArg.matches("\\d+") && yArg.matches("\\d+")) {

            int x, y;
            try {
                x = Integer.parseInt(xArg);
                y = Integer.parseInt(yArg);
            } catch (NumberFormatException e) {
                throw new Exception("Invalid format of numbers");
            }

            if (x < 1 || x > 10 || y < 1 || y > 10) {
                throw new Exception("Numbers should be in range from 1 to 10 for arabic numbers");
            }

            return String.valueOf(calculate(x, opArg, y));

        } else if (xArg.matches("[IVX]+") && yArg.matches("[IVX]+")) {
            int arabicA = numbersConverter.romanToArabic(xArg);
            int arabicB = numbersConverter.romanToArabic(yArg);
            int result = calculate(arabicA, opArg, arabicB);
            if (result < 1) {
                throw new Exception("Result should not be less than 1 for roman numbers");
            }
            return numbersConverter.arabicToRoman(result);
        } else {
            throw new Exception("Arguments must be both arabic or both roman numerals");
        }
    }

    static int calculate(int a, String op, int b) throws Exception {
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception("Invalid operation");
        };
    }
}
