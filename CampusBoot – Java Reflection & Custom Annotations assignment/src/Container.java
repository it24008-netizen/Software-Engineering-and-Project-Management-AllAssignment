import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class Container {
    // LinkedHashMap keeps beans in registration order.
    private final Map<String, Object> beans = new LinkedHashMap<>();

    public void scan(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(Component.class)) {
                System.out.println("[Container] Skipped: " + clazz.getSimpleName()
                        + " (no @Component)");
                continue;
            }

            Component component = clazz.getAnnotation(Component.class);
            String beanName = component.value().trim();

            if (beanName.isEmpty()) {
                beanName = Character.toLowerCase(clazz.getSimpleName().charAt(0))
                        + clazz.getSimpleName().substring(1);
            }

            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                beans.put(beanName, instance);
                System.out.println("[Container] Registered: " + beanName
                        + " (" + clazz.getSimpleName() + ")");
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(
                        "Could not create bean: " + clazz.getName(), e);
            }
        }
    }

    public void injectValues(Map<String, String> config) {
        for (Object bean : beans.values()) {
            Class<?> clazz = bean.getClass();

            for (Field field : clazz.getDeclaredFields()) {
                Value valueAnnotation = field.getAnnotation(Value.class);
                if (valueAnnotation == null) {
                    continue;
                }

                String key = valueAnnotation.value();
                if (!config.containsKey(key)) {
                    throw new IllegalArgumentException(
                            "Missing configuration key: " + key);
                }

                String configuredValue = config.get(key);

                try {
                    field.setAccessible(true);

                    if (field.getType() == String.class) {
                        field.set(bean, configuredValue);
                    } else if (field.getType() == int.class
                            || field.getType() == Integer.class) {
                        field.set(bean, Integer.parseInt(configuredValue));
                    } else {
                        throw new IllegalArgumentException(
                                "Unsupported @Value field type: " + field.getType().getName());
                    }

                    System.out.println("[Container] Injected: " + key + " -> "
                            + clazz.getSimpleName() + "." + field.getName());
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(
                            "Could not inject field: " + field.getName(), e);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Configuration value for '" + key + "' must be an integer: "
                                    + configuredValue, e);
                }
            }
        }
    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    // Package-private access for Router; callers cannot modify the bean map.
    Iterable<Object> getBeans() {
        return beans.values();
    }
}
