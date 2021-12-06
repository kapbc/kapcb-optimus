package com.kapcb.framework.optimus.spel;

import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.Objects;

/**
 * <a>Title: CustomerExpressionEvaluator </a>
 * <a>Author: Kapcb <a>
 * <a>Description: CustomerExpressionEvaluator <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:43
 * @since 1.0
 */
public class CustomerExpressionEvaluator {

    private final SpelExpressionParser spelExpressionParser;

    private final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    protected CustomerExpressionEvaluator(SpelExpressionParser spelExpressionParser) {
        Assert.notNull(spelExpressionParser, "spring expression language parser can not be null");
        this.spelExpressionParser = spelExpressionParser;
    }

    protected CustomerExpressionEvaluator() {
        this(new SpelExpressionParser());
    }

    protected SpelExpressionParser getSpelExpressionParser() {
        return this.spelExpressionParser;
    }

    protected ParameterNameDiscoverer getParameterNameDiscoverer() {
        return this.parameterNameDiscoverer;
    }

    protected Expression getExpression(String springExpression, AnnotatedElementKey annotatedElementKey, Map<ExpressionKey, Expression> cache) {
        ExpressionKey expressionKey = createExpressionKey(springExpression, annotatedElementKey);
        Expression expression = cache.get(expressionKey);
        if (Objects.isNull(expression)) {
            expression = getSpelExpressionParser().parseExpression(springExpression);
            cache.put(expressionKey, expression);
        }
        return expression;
    }

    private static ExpressionKey createExpressionKey(String expression, AnnotatedElementKey annotatedElementKey) {
        return new ExpressionKey(expression, annotatedElementKey);
    }

    protected static class ExpressionKey implements Comparable<ExpressionKey> {

        private final String expression;
        private final AnnotatedElementKey annotatedElementKey;

        protected ExpressionKey(String expression, AnnotatedElementKey annotatedElementKey) {
            Assert.notNull(expression, "expression can not be null");
            Assert.notNull(annotatedElementKey, "annotation element key can not be null");
            this.expression = expression;
            this.annotatedElementKey = annotatedElementKey;
        }

        @Override
        public int hashCode() {
            return this.annotatedElementKey.hashCode() * 29 + this.expression.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ExpressionKey)) {
                return false;
            }
            ExpressionKey expressionKey = (ExpressionKey) obj;
            return this.annotatedElementKey.equals(expressionKey.annotatedElementKey) &&
                    ObjectUtils.nullSafeEquals(this.expression, expressionKey.expression);
        }

        @Override
        public String toString() {
            return this.annotatedElementKey + " with expression \"" + this.expression + "\"";
        }

        @Override
        public int compareTo(ExpressionKey other) {
            int result = this.annotatedElementKey.toString().compareTo(other.annotatedElementKey.toString());
            if (result == 0) {
                result = this.expression.compareTo(other.expression);
            }
            return result;
        }
    }

}
