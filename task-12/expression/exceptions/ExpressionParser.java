package expression.exceptions;

import expression.*;
import expression.exceptions.ownExceptions.ParseException;
import expression.exceptions.ownExceptions.UnexpectedTokenException;
import expression.exceptions.ownExceptions.UnknownIdentifierException;

public class ExpressionParser implements Parser {
    private CheckedTokenizer tokenizer;
    private boolean doNotNegate = false;

    private TripleExpression primary() throws ParseException {
        return primary(false);
    }

    private TripleExpression primary(boolean negativeNumber) throws ParseException {
        TripleExpression result;
        tokenizer.nextToken(negativeNumber);
        doNotNegate = false;
        int position = tokenizer.getLastPosition() + 1;

        switch (tokenizer.getLastToken()) {
            case NUMBER:
                doNotNegate = negativeNumber;
                result = new CheckedConst(tokenizer.getLastNumber());
                tokenizer.nextToken();
                break;
            case VARIABLE:
                String identifier = tokenizer.getLastIdentifier();

                if (!"xyz".contains(identifier)) {
                    throw new UnknownIdentifierException(identifier);
                }

                result = new CheckedVariable(identifier);
                tokenizer.nextToken();
                break;
            case LP:
                result = startPoint();

                if (tokenizer.getLastToken() != Token.RP) {
                    String token = tokenizer.getLastToken().toString();
                    throw new UnexpectedTokenException(token, position);
                }

                tokenizer.nextToken();
                break;
            case MINUS:
                TripleExpression inner = primary(true);

                if (inner instanceof CheckedConst && doNotNegate) {
                    result = inner;
                } else {
                    result = new CheckedNegate(inner);
                }

                doNotNegate = false;
                break;
            case ABS:
                result = new CheckedAbs(primary());
                break;
            case SQRT:
                result = new CheckedSqrt(primary());
                break;
            default:
                String token = tokenizer.getLastToken().toString();
                throw new UnexpectedTokenException(token, position);
        }

        return result;
    }

    private TripleExpression mulDiv() throws ParseException {
        TripleExpression result = primary();

        while (true) {
            switch (tokenizer.getLastToken()) {
                case MUL:
                    result = new CheckedMultiply(result, primary());
                    break;
                case DIV:
                    result = new CheckedDivide(result, primary());
                    break;
                default:
                    return result;
            }
        }
    }

    private TripleExpression addSub() throws ParseException {
        TripleExpression result = mulDiv();

        while (true) {
            switch (tokenizer.getLastToken()) {
                case PLUS:
                    result = new CheckedAdd(result, mulDiv());
                    break;
                case MINUS:
                    result = new CheckedSubtract(result, mulDiv());
                    break;
                default:
                    return result;
            }
        }
    }

    private TripleExpression minMax() throws ParseException {
        TripleExpression result = addSub();

        while (true) {
            switch (tokenizer.getLastToken()) {
                case MIN:
                    result = new CheckedMin(result, addSub());
                    break;
                case MAX:
                    result = new CheckedMax(result, addSub());
                    break;
                default:
                    return result;
            }
        }
    }

    private TripleExpression startPoint() throws ParseException {
        return minMax();
    }

    public TripleExpression parse(String expression) throws ParseException {
        this.tokenizer = new CheckedTokenizer(expression);

        TripleExpression result = startPoint();
        if (tokenizer.getLastToken() != Token.END) {
            String token = tokenizer.getLastToken().toString();
            int position = tokenizer.getLastPosition() + 1;
            throw new UnexpectedTokenException(token, position);
        }

        return result;
    }
}