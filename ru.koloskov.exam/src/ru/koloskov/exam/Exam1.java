package ru.koloskov.exam;

import java.util.Scanner;
import java.io.IOException;

public class Exam1 {

    public static void main(String[] args) {

        String sourceString = requestString();

        checkString(sourceString);

        String operand1 = searchOperand1(sourceString);
        char operator = searchOperator(sourceString, operand1);
        String operand2 = searchOperand2(sourceString, operator);

        CheckOperand(operand1, operand2);

        System.out.println("\"" + calculate(operand1, operator, operand2) + "\"");
    }

    public static String calculate(String operand1, char operator, String operand2) {
        int a = 0;
        String result = switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1.replace(operand2, "");
            case '*' -> {
                a = Integer.parseInt(operand2);
                String result1 = "";
                for (int i = 0; i < a; i++) {
                    result1 = result1 + operand1;
                }
                if (result1.length() > 40) {
                    yield (result1.substring(0, 40) + "...");
                } else yield result1;
            }
            case '/' -> {
                a = Integer.parseInt(operand2);
                yield (operand1.substring(0, (operand1.length() / a)));
            }
            default -> "что-то пошло не так";
        };
        return result;
    }

    private static String searchOperand1(String sourceSpring) {
        char[] str1 = new char[(sourceSpring.indexOf("\"", 1)) - (sourceSpring.indexOf("\"") + 1)];
        sourceSpring.getChars((sourceSpring.indexOf("\"") + 1), (sourceSpring.indexOf("\"", 1)), str1, 0);
        String operand1 = String.valueOf(str1);
        return operand1;
    }

    private static char searchOperator(String sourceSpring, String operand1) {
        char operator = sourceSpring.charAt(operand1.length() + 3);
        return operator;
    }

    private static String searchOperand2(String sourceSpring, char operator) {
        if (sourceSpring.lastIndexOf("\"") == (sourceSpring.length() - 1)) {
            char[] str1 = new char[(sourceSpring.length() - 1) - (sourceSpring.lastIndexOf("\"", (sourceSpring.length() - 2)) + 1)];
            sourceSpring.getChars((sourceSpring.lastIndexOf("\"", (sourceSpring.length() - 2)) + 1), (sourceSpring.length() - 1), str1, 0);
            String operand2 = String.valueOf(str1);
            if (operator != '+' && operator != '-') {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("введено не правильное выражение, строку со строкой можно только складывать или вычитать");
                    System.exit(0);
                }
            }
            return operand2;
        } else {
            char[] str2 = new char[(sourceSpring.length() - 1) - (sourceSpring.lastIndexOf(" ", (sourceSpring.length())))];
            sourceSpring.getChars((sourceSpring.lastIndexOf(" ", sourceSpring.length()) + 1), (sourceSpring.length()), str2, 0);
            String operand2 = String.valueOf(str2);
            try {
                Integer.parseInt(operand2);
            } catch (Exception e) {
                System.out.println("введено не правильное выражение, вторая подстрока должна быть целым числом или выделяться кавычками");
                System.exit(0);
            }
            int operand2int = Integer.parseInt(operand2);

            if ((operand2int > 10) || (operand2int < 1)) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("число должно быть от 1 до 10");
                    System.exit(0);
                }
            }
            if (operator != '*' && operator != '/') {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("введено не правильное выражение, строку и число можно только умножать или делить");
                    System.exit(0);
                }
            }
            return operand2;
        }
    }

    public static void CheckOperand(String operand1, String operand2) {
        if ((operand1.length() > 10) || (operand2.length() > 10)) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Строки превышают длину 10 символов");
                System.exit(0);
            }
        }
    }

    private static void checkString(String sourceString) {
        if (sourceString.indexOf("\"") != 0) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Строка должна начинаться с ковычки");
                System.exit(0);
            }
        }
    }

    public static String requestString() {
        System.out.println("input:");
        Scanner scanner = new Scanner(System.in);
        String sounrceString = scanner.nextLine();
        scanner.close();
        return sounrceString;
    }
}
