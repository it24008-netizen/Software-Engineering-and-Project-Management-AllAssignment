import java.lang.reflect.Method;

public class Router {
    public static String dispatch(Container container, String path) {
        for (Object bean : container.getBeans()) {
            for (Method method : bean.getClass().getDeclaredMethods()) {
                Route route = method.getAnnotation(Route.class);

                if (route != null && route.path().equals(path)) {
                    if (method.getParameterCount() != 0
                            || method.getReturnType() != String.class) {
                        throw new IllegalStateException(
                                "Route method must take no parameters and return String: "
                                        + method.getName());
                    }

                    try {
                        method.setAccessible(true);
                        return (String) method.invoke(bean);
                    } catch (ReflectiveOperationException e) {
                        throw new IllegalStateException(
                                "Could not invoke route: " + path, e);
                    }
                }
            }
        }

        return "404 Not Found: " + path;
    }
}
