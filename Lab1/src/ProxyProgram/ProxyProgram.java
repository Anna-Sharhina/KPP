package ProxyProgram;
import java.lang.reflect.Proxy;

public class ProxyProgram {
    public static void main(String[] args) {
        Evaluatable expSinFunction = new ExpSinFunction(2.5);
        Evaluatable xSquareFunction = new XSquareFunction();

        Evaluatable profilingExpSinFunction = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[] {Evaluatable.class},
                new ProfilingInvocationHandler(expSinFunction)
        );

        Evaluatable tracingExpSinFunction = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[] {Evaluatable.class},
                new TracingInvocationHandler(expSinFunction)
        );

        Evaluatable profilingXSquareFunction  = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[] {Evaluatable.class},
                new ProfilingInvocationHandler(xSquareFunction)
        );

        Evaluatable tracingXSquareFunction = (Evaluatable) Proxy.newProxyInstance(
                Evaluatable.class.getClassLoader(),
                new Class<?>[] {Evaluatable.class},
                new TracingInvocationHandler(xSquareFunction)
        );

        double x = 1.0;

        tracingExpSinFunction.evalf(x);
        System.out.println("exp(−∣a∣⋅x)⋅sin(x) with a=2.5 and x=1.0: " + profilingExpSinFunction.evalf(x));

        System.out.println("\n\n");
        tracingXSquareFunction.evalf(x);
        System.out.println("x^2 with x=1.0: " + profilingXSquareFunction.evalf(x));
    }
}



