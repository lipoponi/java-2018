package expression.exceptions;

import expression.TripleExpression;
import expression.exceptions.ownExceptions.EvaluationException;

public class CheckedMin extends CheckedBinaryOperator {
    @Override
    protected void check(int left, int right) throws EvaluationException {

    }

    @Override
    protected int apply(int left, int right) {
        return left < right ? left : right;
    }

    public CheckedMin(TripleExpression left, TripleExpression right) {
        super(left, right);
    }
}
