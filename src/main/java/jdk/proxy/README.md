# JDK动态代理
核心：InvocationHandler的invoke()方法

使用Proxy.newProxyInstance() 产生代理对象。

也可以通过Proxy.getProxyClass(classloader, clazz) 获取动态代理类，然后通过反射机制获取代理类的构造方法，通过构造函数.newInstance()来新建代理对象，并将自定义的InvocationHandler传入，即可返回新的代理对象。

# CGLIB
核心：MethodInterceptor 目标拦截器

使用Enhancer.create()方法，通过ASM框架，来转换字节码的方式生成一个新的代理类。