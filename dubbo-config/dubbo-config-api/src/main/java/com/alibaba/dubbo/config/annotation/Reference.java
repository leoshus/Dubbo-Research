/*
 * Copyright 1999-2012 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Reference
 * 
 * @author william.liangf
 * @export
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Reference {

    Class<?> interfaceClass() default void.class;

    String interfaceName() default "";

    /**
     * 多版本 当一个接口实现,出现不兼容升级时,可用用版本号过渡 版本号不同的服务相互间不引用
     * 在低峰期 先升级一半提供者为新版本
     * 然后将所有的消费者升级为新版本
     * 最好将剩下的一半提供者升级为新版本
     * "*" 表示不区分版本 (2.2.0以上版本支持)
     * @return
     */
    String version() default "";

    /**
     * 同一接口的不同实现时 使用group分组区分
     * "*" 表示任意分组(2.2.0以上版本支持 总是只调用一个可用组的实现)
     * @return
     */
    String group() default "";

    /**
     * 配置url 绕过注册中心 实现点对点通信
     * @return
     */
    String url() default "";

    String client() default "";

    boolean generic() default false;

    boolean injvm() default false;

    boolean check() default false;

    boolean init() default false;

    boolean lazy() default false;

    boolean stubevent() default false;

    String reconnect() default "";

    boolean sticky() default false;

    String proxy() default "";

    String stub() default "";

    String cluster() default "";

    int connections() default 0;

    int callbacks() default 0;

    String onconnect() default "";

    String ondisconnect() default "";

    String owner() default "";

    String layer() default "";

    int retries() default 0;

    String loadbalance() default "";

    boolean async() default false;

    int actives() default 0;

    boolean sent() default false;

    String mock() default "";

    String validation() default "";

    int timeout() default 0;

    String cache() default "";

    String[] filter() default {};

    String[] listener() default {};

    String[] parameters() default {};

    String application() default "";

    String module() default "";

    String consumer() default "";

    String monitor() default "";

    /**
     * 指向注册中心id
     * @return
     */
    String[] registry() default {};

}
