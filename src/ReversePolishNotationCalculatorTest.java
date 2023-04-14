import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReversePolishNotationCalculatorTest {
    static ReversePolishNotationCalculator rpc;

    @BeforeAll
    static void beforeAll() {
        rpc = new ReversePolishNotationCalculator();
    }

    @Test
    void shouldCalculateAddition() {
    String data = "1 2 3 + +";
    int result = rpc.calculatePolishNotation(data);
    int expected = 6;

    assertEquals(expected, result);
    }

    @Test
    void shouldCalculateSubstraction() {
        String data = "1 2 3 - -";
        int result = rpc.calculatePolishNotation(data);
        int expected = 2;

        assertEquals(expected, result);
    }

    @Test
    void shouldCalculateMultiplication() {
        String data = "1 2 0 * *";
        int result = rpc.calculatePolishNotation(data);
        int expected = 0;

        assertEquals(expected, result);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}

class Main {

    public static void main(String[] args) {
        String data = "1 2 3 - +";
        ReversePolishNotationCalculator rpc = new ReversePolishNotationCalculator();

        rpc.calculatePolishNotation(data);
    }
}
