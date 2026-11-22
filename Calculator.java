import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double currentResult = 0;
        boolean isFirstCalculation = true;

        System.out.println("Калькулятор запущен.");
        System.out.println("Доступные операции: +, -, *, /");
        System.out.println("Команды управления (доступны на любом шаге): C — сброс, S — выход.");

        while (true) {
            if (isFirstCalculation) {
                System.out.print("Введите первое число (или C/S): ");
                String input = scanner.next();

                if (isExitCommand(input)) {
                    System.out.println("Завершение работы калькулятора.");
                    break;
                }
                if (isClearCommand(input)) {
                    System.out.println("Результат уже сброшен.");
                    continue;
                }

                Double firstOperand = parseNumber(input);
                if (firstOperand == null) {
                    System.out.println("Ошибка: введено не число! Попробуйте снова.");
                    continue;
                }

                currentResult = firstOperand;
                isFirstCalculation = false;
            }

            System.out.print("Введите операцию (+, -, *, /) или команду (C, S): ");
            String opInput = scanner.next();

            if (isExitCommand(opInput)) {
                System.out.println("Завершение работы калькулятора.");
                break;
            }
            if (isClearCommand(opInput)) {
                currentResult = 0;
                isFirstCalculation = true;
                System.out.println("Результат сброшен.");
                continue;
            }

            if (opInput.length() != 1 || "+-*/".indexOf(opInput.charAt(0)) == -1) {
                System.out.println("Ошибка: неподдерживаемая операция! Попробуйте снова.");
                continue;
            }
            char operation = opInput.charAt(0);

            Double secondOperand = null;
            boolean shouldReset = false;
            boolean shouldExit = false;

            while (secondOperand == null) {
                System.out.print("Введите второе число (или C/S): ");
                String secondInput = scanner.next();

                if (isExitCommand(secondInput)) {
                    shouldExit = true;
                    break;
                }
                if (isClearCommand(secondInput)) {
                    shouldReset = true;
                    break;
                }

                secondOperand = parseNumber(secondInput);
                if (secondOperand == null) {
                    System.out.println("Ошибка: введено не число! Попробуйте снова.");
                }
            }

            if (shouldExit) {
                System.out.println("Завершение работы калькулятора.");
                break;
            }
            if (shouldReset) {
                currentResult = 0;
                isFirstCalculation = true;
                System.out.println("Результат сброшен.");
                continue;
            }

            switch (operation) {
                case '+':
                    currentResult = add(currentResult, secondOperand);
                    System.out.println("Результат: " + currentResult);
                    break;
                case '-':
                    currentResult = subtract(currentResult, secondOperand);
                    System.out.println("Результат: " + currentResult);
                    break;
                case '*':
                    currentResult = multiply(currentResult, secondOperand);
                    System.out.println("Результат: " + currentResult);
                    break;
                case '/':
                    if (secondOperand == 0) {
                        System.out.println("Ошибка: деление на ноль невозможно! Текущий результат не изменён: " + currentResult);
                    } else {
                        currentResult = divide(currentResult, secondOperand);
                        System.out.println("Результат: " + currentResult);
                    }
                    break;
            }
        }

        scanner.close();
    }

    private static boolean isExitCommand(String input) {
        return input.equalsIgnoreCase("s");
    }

    private static boolean isClearCommand(String input) {
        return input.equalsIgnoreCase("c");
    }

    private static Double parseNumber(String input) {
        try {
            return Double.parseDouble(input.replace(',', '.'));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subtract(double a, double b) {
        return a - b;
    }

    private static double multiply(double a, double b) {
        return a * b;
    }

    private static double divide(double a, double b) {
        return a / b;
    }
}