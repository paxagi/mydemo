public class TwoNumberOperation {
    private int[] arguments = new int[2];

    public TwoNumberOperation(int firstArgument, int secondArgument) {
        setArguments(firstArgument, secondArgument);
    }

    public void setArguments(int firstArgument, int secondArgument) {
        arguments[0] = firstArgument;
        arguments[1] = secondArgument;
    }
    public int getFirstArgument() { return arguments[0]; }
    public int getSecondArgument() { return arguments[1]; }

    public int addition() {
        return arguments[0] + arguments[1];
    }

    public int subtraction() {
        return arguments[0] - arguments[1];
    }

    public int multiplication() {
        return arguments[0] * arguments[1];
    }

    public int division() throws ArithmeticException {
        return arguments[0] / arguments[1];
    }
}