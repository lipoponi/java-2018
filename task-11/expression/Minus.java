package expression;

public class Minus extends UnaryOperator {
    public Minus(CommonExpression inner) {
        super(inner);
    }

    protected int operationImpl(int x) {
        return -x;
    }

    protected double operationImpl(double x) {
        return -x;
    }
}