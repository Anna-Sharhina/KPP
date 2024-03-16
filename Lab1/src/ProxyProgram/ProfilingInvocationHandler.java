package ProxyProgram;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public record ProfilingInvocationHandler(Object target) implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long startTime = System.nanoTime();
        Object result = method.invoke(target, args);
        long endTime = System.nanoTime();
        System.out.println("Method " + method.getName() + " took " + (endTime - startTime) + " ns to execute.");
        return result;
    }
}
