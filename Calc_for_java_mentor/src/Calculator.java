import javax.management.relation.RoleUnresolved;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    final static private String arabicExpression = "(?:-?\\d+)"; //регулярное выражение для арабских целых чисел
    static private String[] expressions = {arabicExpression, RomanNotation.expression}; //массив регулярных выражений нотаций
    static private int usingNotation; //индекс используемой нотации

    static private Scanner scanner = new Scanner(System.in); //сканер с консоли

    public static void main(String[] args) {
        int[] arguments = new int[2];
        String operator = "";
        int result = 0;

        while (true) {
            String line = scanner.nextLine(); //чтение строки из консоли
            String[] signs = selectSigns(line); //строковый массив {аргумент},{знак},{аргумент}

            operator = signs[1];
            if (usingNotation == 0) { //арабские числа
                arguments[0] = Integer.parseInt(signs[0]);
                arguments[1] = Integer.parseInt(signs[2]);
            } else if (usingNotation == 1) { //римские числа
                arguments[0] = RomanNotation.toNumber(signs[0]);
                arguments[1] = RomanNotation.toNumber(signs[2]);
            }

            boolean isRange = true;
            for (int i = 0; i < 2; i++) {
                isRange &= ((1 <= arguments[i]) && (arguments[i] <= 10));
            }
            if (!isRange) {
                throw new RuntimeException("Вводимые числа должны быть в пределах от 1 до 10 включительно");
            }

            TwoNumberOperation operation = new TwoNumberOperation(arguments[0], arguments[1]);
            switch (operator) {
                case "+" : result = operation.addition(); break;
                case "-" : result = operation.subtraction(); break;
                case "*" : result = operation.multiplication(); break;
                case "/" : result = operation.division(); break;
                default: throw new RuntimeException("Допустимый набор знаков: \"+, -, *, /\"");
            }

            System.out.println(usingNotation == 0 ? result : RomanNotation.toNote(result));

        }
    }

    static private String[] selectSigns(String line) {
        String[] result = new String[3];
        boolean isCorrectRecord = false;

        for (int i = 0; i < expressions.length; i++) {
            String ex = expressions[i];
            String twoNumEx = String.format("^(?:\\s*)(%s)(?:\\s*)([^\\s\\d\\w])(?:\\s*)(%s)(?:\\s*)$", ex, ex);
            Pattern compiledPattern = Pattern.compile(twoNumEx);
            Matcher matcher = compiledPattern.matcher(line);
            if (matcher.find()) {
                isCorrectRecord = true;
                usingNotation = i;
                for (int j = 0; j < 3; j++) {
                    result[j] = matcher.group(j+1);
                    isCorrectRecord &= !(result[j].equals(""));
                    isCorrectRecord &= !(result[j].equals("-") && (j != 1));
                }
            }
        }

        if (!isCorrectRecord) {
            throw new RuntimeException("Некорректная запись: выражение должно содержать два натуральных римских или арабских числа");
        }

        return result;
    }
}