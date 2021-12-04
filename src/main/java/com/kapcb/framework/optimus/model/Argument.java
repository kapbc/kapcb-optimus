package com.kapcb.framework.optimus.model;

import com.kapcb.framework.common.constants.enums.IntegerPool;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <a>Title: Argument </a>
 * <a>Author: Kapcb <a>
 * <a>Description: Argument <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 20:54
 * @since 1.0
 */
public final class Argument implements Arg<Map<String, Object>> {

    private final Map<String, Object> args;

    public Argument() {
        args = new HashMap<>(IntegerPool.FOUR.value());
    }

    public Argument withArg(String key, Object object) {
        Assert.hasLength(key, "param key can not be null or empty");
        Assert.notNull(object, "param can not be null");
        this.args.put(key, object);
        return this;
    }

    @Override
    public Map<String, Object> build() {
        return this.args;
    }

    @Override
    public Object get(String name) {
        Assert.hasLength(name, "name can not be null or empty");
        return this.args.get(name);
    }

}
