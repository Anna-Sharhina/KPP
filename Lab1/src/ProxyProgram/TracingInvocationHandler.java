package ProxyProgram;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public record TracingInvocationHandler(Object target) implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print("Calling method " + method.getName() + " with arguments: ");
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            if (i != args.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        Object result = method.invoke(target, args);
        System.out.println("Method " + method.getName() + " returned " + result);
        return result;
    }
}
