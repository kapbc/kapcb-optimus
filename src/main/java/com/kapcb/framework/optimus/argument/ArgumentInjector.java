package com.kapcb.framework.optimus.argument;

import java.util.Map;

/**
 * <a>Title: ArgumentInjector </a>
 * <a>Author: Kapcb <a>
 * <a>Description: ArgumentInjector <a>
 *
 * @author Kapcb
 * @version 1.0
 * @date 2021/12/4 19:40
 * @since 1.0
 */
public interface ArgumentInjector extends Injector<Map<String, Object>> {

    @Override
    Map<String, Object> inject(Object... objects);

}
