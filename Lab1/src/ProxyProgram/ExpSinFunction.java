package ProxyProgram;

public record ExpSinFunction(double a) implements Evaluatable {

    @Override
    public double evalf(double x) {
        return Math.exp(-Math.abs(a) * x) * Math.sin(x);
    }
}
