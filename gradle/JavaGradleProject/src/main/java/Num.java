package expr;

class Num extends Expr {
    private double value;

    Num(double value) {
        this.value = value;
    }

    public String toString(int prec) {
        return String.valueOf(value);
    }

    public double value(Environment env) {
        return value;
    }
}