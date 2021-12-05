package com.kapcb.framework.optimus.key;

import com.kapcb.framework.optimus.model.SimpleKey;

import java.lang.reflect.Method;

/**
 * <a>Title: SimpleKeyGenerator </a>
 * <a>Author: Kapcb <a>
 * <a>Description: SimpleKeyGenerator <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 15:06
 * @since 1.0
 */
public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object object, Method method, Object... args) {
        return generateKey(args);
    }

    public static Object generateKey(Object... args) {
        if (args.length == 0) {
            return SimpleKey.EMPTY;
        }
        if (args.length == 1) {
            Object arg = args[0];
            if ((arg != null) && (!arg.getClass().isArray())) {
                return arg;
            }
        }
        return new SimpleKey(args);
    }

}
