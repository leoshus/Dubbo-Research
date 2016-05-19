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

    /**
     * 令牌验证
     *  防止消费者绕过注册中心访问提供者
     *  在注册中心控制权限，以决定要不要下发令牌给消费者
     *  注册中心可灵活改变授权方式，而不需修改或升级提供者
     *  1、全局设置开启令牌验证
     *   <!--随机token令牌，使用UUID生成-->
     *   <dubbo:provider interface="com.foo.BarService" token="true" />
     *   <!--固定token令牌，相当于密码-->
     *   <dubbo:provider interface="com.foo.BarService" token="123456" />
     *  2、在服务级别设置
     *   <!--随机token令牌，使用UUID生成-->
     *   <dubbo:service interface="com.foo.BarService" token="true" />
     *   <!--固定token令牌，相当于密码-->
     *   <dubbo:service interface="com.foo.BarService" token="123456" />
     *  3、在协议级别设置
     *   <!--随机token令牌，使用UUID生成-->
     *   <dubbo:protocol name="dubbo" token="true" />
     *   <!--固定token令牌，相当于密码-->
     *   <dubbo:protocol name="dubbo" token="123456" />
     * @return
     */
    String token() default "";

    boolean deprecated() default false;

    boolean dynamic() default false;

    String accesslog() default "";

    /**
     * 并发控制
     * 服务端并发执行(或占用线程池线程数)不能超过指定个数
     * @return
     */
    int executes() default 0;

    boolean register() default false;

    int weight() default 0;

    String document() default "";

    /**
     * 延迟暴露
     * 若服务需要warmup时间,比如初始化缓存,等待相关资源就位等 可以使用delay进行延迟暴露
     * delay=-1 :延迟到spring初始化完成后,再暴露服务(基于spring的ContextRefreshedEvent事件触发暴露)
     * @return
     */
    int delay() default 0;

    String local() default "";

    /**
     * 本地存根
     * 远程服务后，客户端通常只剩下接口，而实现全在服务器端，但提供方有些时候想在客户端也执行部分逻辑，
     * 比如：做ThreadLocal缓存，提前验证参数，调用失败后伪造容错数据等等，此时就需要在API中带上Stub，客户端生成Proxy实，会把Proxy通过构造函数传给Stub，然后把Stub暴露组给用户，Stub可以决定要不要去调Proxy。
     * 注:Stub必须有可传入Proxy的构造函数。
     * @return
     */
    String stub() default "";

    String cluster() default "";

    String proxy() default "";

    /**
     * 连接控制
     *
     * 限制客户端服务使用连接连接数：(如果是长连接，比如Dubbo协议，connections表示该服务对每个提供者建立的长连接数)
     * 如果<dubbo:service>和<dubbo:reference>都配了connections，<dubbo:reference>优先
     * @return
     */
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

    /**
     * 并发控制
     * 客户端并发执行(或占用连接的请求数)不能超过指定个数
     * 如果<dubbo:service>和<dubbo:reference>都配了actives，<dubbo:reference>优先
     *
     * 配置服务的客户端的loadbalance属性为leastactive，此Loadbalance会调用并发数最小的Provider（Consumer端并发数）
     * <dubbo:reference interface="com.foo.BarService" loadbalance="leastactive" />
     * @return
     */
    int actives() default 0;

    boolean sent() default false;

    /**
     * 本地伪装
     * Mock通常用于服务降级,比如某验权服务,当服务提供方全部挂掉后,客户端不抛出异常 而是通过Mock数据返回授权失败
     * Mock是Stub的一个子集,便于服务提供方在客户端执行容错逻辑 因经常需要在出现RPCException(比如网络失败,超时等)时进行容错,而在出现业务异常(比如登录用户名密码错误)时不需要容错
     * 若用Stub,可能就需要捕获并依赖RPCException类,而用Mock就可以不依赖RPCException,因为它的约定就是只有出现RPCException时才执行
     * @return
     */
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
