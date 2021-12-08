package com.kapcb.framework.optimus.operation;

import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * <a>Title: BeanFactoryLimiterOperationSourceAdvisor </a>
 * <a>Author: Kapcb <a>
 * <a>Description: BeanFactoryLimiterOperationSourceAdvisor <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/7 22:33
 * @since 1.0
 */
public class BeanFactoryLimiterOperationSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private LimiterOperationSource limiterOperationSource;


}
