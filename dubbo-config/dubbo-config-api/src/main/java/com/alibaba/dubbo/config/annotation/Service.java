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
 * Service
 * 
 * @author william.liangf
 * @export
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Service {

    Class<?> interfaceClass() default void.class;

    String interfaceName() default "";

    /**
     * 多版本 当一个接口实现,出现不兼容升级时,可用用版本号过渡 版本号不同的服务相互间不引用
     * 在低峰期 先升级一半提供者为新版本
     * 然后将所有的消费者升级为新版本
     * 最好将剩下的一半提供者升级为新版本
     * @return
     */
    String version() default "";

    /**
     * 同一接口的不同实现时 使用group分组区分
     * @return
     */
    String group() default "";

    String path() default "";

    boolean export() default false;

    String token() default "";

    boolean deprecated() default false;

    boolean dynamic() default false;

    String accesslog() default "";

    int executes() default 0;

    boolean register() default false;

    int weight() default 0;

    String document() default "";

    int delay() default 0;

    String local() default "";

    String stub() default "";

    String cluster() default "";

    String proxy() default "";

    int connections() default 0;

    /**
     * 参数回调
     * 参数回调方式与调用本地callback或listener相同，只需要在Spring的配置文件中声明哪个参数是callback类型即可，Dubbo将基于长连接生成反向代理，这样就可以从服务器端调用客户端逻辑。
     * @return
     */
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

    /**
     * 参数验证:客户端是否验证参数
     * @return
     */
    String validation() default "";

    int timeout() default 0;

    String cache() default "";

    String[] filter() default {};

    String[] listener() default {};

    String[] parameters() default {};

    String application() default "";

    String module() default "";

    String provider() default "";

    /**
     * 服务使用的协议 一个服务支持多个协议 使用逗号分隔如 protocol="dubbo,hessian"
     * @return
     */
    String[] protocol() default {};

    String monitor() default "";

    /**
     * 一个服务配置一个或多个注册中心
     * @return
     */
    String[] registry() default {};

}
