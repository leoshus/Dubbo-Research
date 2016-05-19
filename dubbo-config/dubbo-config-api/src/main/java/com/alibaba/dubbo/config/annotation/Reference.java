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

    /**
     * 泛化引用
     * 泛接口调用方式主要用于客户端没有API接口及模型类元的情况,参数及返回值中的所有POJO均用Map表示 通常用于框架集成
     * 比如:实现一个通用的服务测试框架,可通过GenericService调用所有服务实现。
     * @return
     */
    boolean generic() default false;

    /**
     * 本地调用 使用Injvm协议,是一个伪协议 它不开启端口 不发起远程调用 只在JVM内直接关联 但执行Dubbo的Filter链
     * @return
     */
    boolean injvm() default false;

    boolean check() default false;

    boolean init() default false;

    /**
     * 延迟连接
     * 延迟连接，用于减少长连接数，当有调用发起时，再创建长连接。
     * 注:只对使用长连接的dubbo协议生效。
     * @return
     */
    boolean lazy() default false;

    boolean stubevent() default false;

    String reconnect() default "";

    /**
     * 粘滞连接
     * 粘滞连接用于有状态服务,尽可能让客户端总是向同一提供者发起调用,除非该提供者挂了,再连另一台
     * 粘滞连接将自动开启延迟连接,以减少长连接数
     * @return
     */
    boolean sticky() default false;

    String proxy() default "";

    String stub() default "";

    String cluster() default "";

    /**
     * 连接控制
     *
     * 限制客户端服务使用连接连接数：(如果是长连接，比如Dubbo协议，connections表示该服务对每个提供者建立的长连接数)
     * 如果<dubbo:service>和<dubbo:reference>都配了connections，<dubbo:reference>优先
     * @return
     */
    int connections() default 0;

    int callbacks() default 0;

    String onconnect() default "";

    String ondisconnect() default "";

    String owner() default "";

    String layer() default "";

    int retries() default 0;

    String loadbalance() default "";

    /**
     * 异步调用
     * 基于NIO的非阻塞实现并行调用 客户端不需要启动多线程即可完成并行调用多个远程服务 相对多线程开销较小
     * @return
     */
    boolean async() default false;

    int actives() default 0;

    boolean sent() default false;

    String mock() default "";

    /**
     * 参数验证:服务器端是否验证参数
     * @return
     */
    String validation() default "";

    int timeout() default 0;

    /**
     * 结果缓存
     *  lru :基于最近最少使用原则删除多余缓存 保持最热的数据被缓存
     *  threadlocal :当前线程缓存,比如一个页面渲染 用到很多portal 每个portal都要查询用户信息 通过线程缓存 可以减少这种访问
     *  jcache 与JSR107集成 可以桥接各种缓存实现
     * @return
     */
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
