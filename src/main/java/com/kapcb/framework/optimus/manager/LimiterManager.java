package com.kapcb.framework.optimus.manager;

import com.kapcb.framework.optimus.limit.Limiter;
import org.apache.poi.ss.formula.functions.T;

import java.util.Collection;

/**
 * <a>Title: LimiterManager </a>
 * <a>Author: Kapcb <a>
 * <a>Description: LimiterManager <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 21:09
 * @since 1.0
 */
public interface LimiterManager<T extends Limiter> extends Manager {

    T getLimiter(String name);

    Collection<String> getLimiterNames();

}
