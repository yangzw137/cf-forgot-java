package org.geekbang.thinking.in.spring.ioc.dependency.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/3/14
 * @since todo
 */
public class ThreadLocalScope implements Scope {

    public static final String NAME = "Thread-Local-Scope";

    private NamedThreadLocal<Map<String, Object>> namedThreadLocal = new NamedThreadLocal("thread-local-scope") {

        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = getContext();
        Object value = context.get(name);
        if (Objects.isNull(value)) {
            value = objectFactory.getObject();
        }
        if (Objects.nonNull(value)) {
            context.put(name, value);
        }
        return value;
    }

    @NonNull
    private Map<String, Object> getContext() {
        return namedThreadLocal.get();
    }

    @Override
    public Object remove(String name) {
        return getContext().remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //todo
    }

    @Override
    public Object resolveContextualObject(String key) {
        return getContext().get(key);
    }

    @Override
    public String getConversationId() {
        return String.valueOf(Thread.currentThread().getId());
    }
}
