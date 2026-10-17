package bohac.apps;

import bohac.polynomial.Polynomial;
import bohac.util.Menu;
import bohac.util.Terminal;
import bohac.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PolynomialApp {
    enum Operation {
        ADDITION("(%s) + (%s) = %s"),
        SUBTRACTION("(%s) - (%s) = %s"),
        MULTIPLICATION("(%s) * (%s) = %s"),
        MULTIPLICATION_BY_CONSTANT("%f * (%s) = %s");

        private final String resultFormat;

        Operation(String resultFormat) {
            this.resultFormat = resultFormat;
        }
    }

    public static List<Polynomial> polynomials = new ArrayList<>(Arrays.asList(
            Polynomial.of(1, 2, 3, 4, 5),
            Polynomial.of(2, -7, 3, 4, 6),
            Polynomial.of(-8, 2)
    ));
    private static final Menu MENU = new Menu(
            new Menu.MenuItem("Create a polynomial", () -> polynomials.add(Utils.createPolynomial())),
            new Menu.MenuItem("Addition", () -> operation(Operation.ADDITION)),
            new Menu.MenuItem("Subtraction", () -> operation(Operation.SUBTRACTION)),
            new Menu.MenuItem("Product", () -> operation(Operation.MULTIPLICATION)),
            new Menu.MenuItem("Multiply by a constant", () -> operation(Operation.MULTIPLICATION_BY_CONSTANT)),
            new Menu.MenuItem("Clear", Menu.CLEAR),
            new Menu.MenuItem("Exit", Menu.EXIT)
    );

    public static void main(String[] args) {
        MENU.prompt(() -> {
            System.out.println("Polynomials in memory:");
            polynomials.forEach(polynomial -> System.out.printf("[%d] %s\n", polynomials.indexOf(polynomial) + 1, polynomial));
            System.out.println();
        });
    }

    /**
     * Absolutne scuffed ale w/e
     *
     * @param operation operation enum
     */
    public static void operation(Operation operation) {
        Double constant = null;
        Polynomial a = Utils.getPolynomial("Polynomial A: ");
        Polynomial b = null;
        if (operation == Operation.MULTIPLICATION_BY_CONSTANT) {
            constant = Terminal.promptNumericDouble("Constant: ");
        } else {
            b = Utils.getPolynomial("Polynomial B: ");
        }
        System.out.println("\nResult:");
        Polynomial result = null;
        switch (operation) {
            case ADDITION -> result = a.add(b);
            case SUBTRACTION -> result = a.subtract(b);
            case MULTIPLICATION -> result = a.multiply(b);
            case MULTIPLICATION_BY_CONSTANT -> result = a.multiply(constant);
        }
        System.out.printf(operation.resultFormat + "\n", constant == null ? a : constant, constant == null ? b : a, result);
    }
}
