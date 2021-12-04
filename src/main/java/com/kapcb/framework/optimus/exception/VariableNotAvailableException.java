package com.kapcb.framework.optimus.exception;

import org.springframework.expression.EvaluationException;

/**
 * <a>Title: VariableNotAvailableException </a>
 * <a>Author: Kapcb <a>
 * <a>Description: VariableNotAvailableException <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:42
 * @since 1.0
 */
public class VariableNotAvailableException extends EvaluationException {

    private final String name;

    public VariableNotAvailableException(String name) {
        super("variable is not available");
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }
}
