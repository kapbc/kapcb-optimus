package com.kapcb.framework.optimus.model;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;

/**
 * <a>Title: SimpleKey </a>
 * <a>Author: Kapcb <a>
 * <a>Description: SimpleKey <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/5 15:08
 * @since 1.0
 */
public class SimpleKey implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final SimpleKey empty = new SimpleKey();

    private final int hashCode;

    private final Object[] params;

    public SimpleKey(Object... elements) {
        Assert.notNull(elements, "elements can not be null");
        this.params = new Object[elements.length];
        System.arraycopy(elements, 0, this.params, 0, elements.length);
        this.hashCode = Arrays.deepHashCode(this.params);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj instanceof SimpleKey && Arrays.deepEquals(this.params, ((SimpleKey) obj).params));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + StringUtils.arrayToCommaDelimitedString(this.params) + "]";
    }

}
