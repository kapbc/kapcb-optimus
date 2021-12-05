package com.kapcb.framework.optimus.operation;

import com.kapcb.framework.common.constants.enums.StringPool;
import com.kapcb.framework.optimus.limit.Limiter;
import com.kapcb.framework.optimus.manager.LimiterManager;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;

/**
 * <a>Title: LimiterOperation </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterOperation <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 21:14
 * @since 1.0
 */
public abstract class LimiterOperation<T extends Limiter> {

    private final String name;

    private final String limiterName;

    private final String keyGenerator;

    private final String limiterManager;

    private final String key;

    private final String condition;

    private final String fallbackResolver;

    private final String errorHandler;

    private final Collection<String> argumentInjectors;

    private final Map<String, Object> customArgument;

    public String getName() {
        return this.name;
    }

    public String getLimiterName() {
        return this.limiterName;
    }

    public String getKeyGenerator() {
        return this.keyGenerator;
    }

    public String getLimiterManager() {
        return this.limiterManager;
    }

    public String getKey() {
        return this.key;
    }

    public String getCondition() {
        return this.condition;
    }

    public String getFallbackResolver() {
        return this.fallbackResolver;
    }

    public String getErrorHandler() {
        return this.errorHandler;
    }

    public Collection<String> getArgumentInjectors() {
        return this.argumentInjectors;
    }

    public Map<String, Object> getCustomArgument() {
        return this.customArgument;
    }

    public abstract Class<? extends LimiterManager<T>> getDefaultLimiterManagerClass();

    protected LimiterOperation(Builder builder) {
        this.name = builder.name;
        this.limiterName = builder.limiterName;
        this.keyGenerator = builder.keyGenerator;
        this.limiterManager = builder.limiterManager;
        this.key = builder.key;
        this.condition = builder.condition;
        this.fallbackResolver = builder.fallbackResolver;
        this.errorHandler = builder.errorHandler;
        this.argumentInjectors = builder.argumentInjectors;
        this.customArgument = builder.customArgument;
    }

    protected static abstract class Builder {

        private String name = StringPool.EMPTY_STRING.value();
        private String limiterName = StringPool.EMPTY_STRING.value();
        private String keyGenerator = StringPool.EMPTY_STRING.value();
        private String limiterManager = StringPool.EMPTY_STRING.value();
        private String key = StringPool.EMPTY_STRING.value();
        private String condition = StringPool.EMPTY_STRING.value();
        private String fallbackResolver;
        private String errorHandler;
        private Collection<String> argumentInjectors;
        private Map<String, Object> customArgument;

        public LimiterOperation.Builder name(String name) {
            Assert.hasLength(name, "name can not be null");
            this.name = name;
            return this;
        }

        public LimiterOperation.Builder limiterName(String limiterName) {
            Assert.hasLength(limiterName, "limiter name can not be null");
            this.limiterName = limiterName;
            return this;
        }

        public LimiterOperation.Builder keyGenerator(String keyGenerator) {
            Assert.hasLength(keyGenerator, "key generator can not be null");
            this.keyGenerator = keyGenerator;
            return this;
        }

        public LimiterOperation.Builder limiterManager(String limiterManager) {
            Assert.hasLength(limiterManager, "limiter manager can not be null");
            this.limiterManager = limiterManager;
            return this;
        }

        public LimiterOperation.Builder key(String key) {
            Assert.hasLength(key, "key can not be null");
            this.key = key;
            return this;
        }

        public LimiterOperation.Builder condition(String condition) {
            Assert.hasLength(condition, "condition can not be null");
            this.condition = condition;
            return this;
        }

        public LimiterOperation.Builder fallbackResolver(String fallbackResolver) {
            this.fallbackResolver = fallbackResolver;
            return this;
        }

        public LimiterOperation.Builder errorHandler(String errorHandler) {
            this.errorHandler = errorHandler;
            return this;
        }

        public LimiterOperation.Builder argumentInjectors(Collection<String> argumentInjectors) {
            this.argumentInjectors = argumentInjectors;
            return this;
        }

        public LimiterOperation.Builder customArgument(Map<String, Object> customArgument) {
            this.customArgument = customArgument;
            return this;
        }

        public abstract LimiterOperation<? extends Limiter> build();
    }

}
