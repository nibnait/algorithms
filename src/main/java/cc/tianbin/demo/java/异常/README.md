Java 中常见的异常类型包括以下几种：

 - Checked Exception：可检查异常，必须在代码中进行处理或声明抛出。例如：IOException，ClassNotFoundException。
 - Unchecked Exception：非检查异常，也称为运行时异常。通常是程序员的错误或者环境导致的问题，不需要显式地处理或声明抛出。例如：NullPointerException，ArrayIndexOutOfBoundsException。
 - Error：表示严重的错误，不应该被程序员处理或捕获。例如：OutOfMemoryError，StackOverflowError。

常见的 Java 异常类型可以归为以下几类：

空指针异常（NullPointerException）：当尝试访问空对象时抛出。  
类型转换异常（ClassCastException）：当试图将一个对象强制转换为不兼容的类型时抛出。  
数组越界异常（ArrayIndexOutOfBoundsException）：当尝试访问数组中不存在的元素时抛出。  
算术异常（ArithmeticException）：当出现除零或者其它算术错误时抛出。  
文件 IO 异常（IOException）：当文件 I/O 操作失败时抛出。  
SQL 异常（SQLException）：当数据库操作失败时抛出。  
并发修改异常（ConcurrentModificationException）：当尝试在迭代时修改集合时抛出。  
线程中断异常（InterruptedException）：当线程被中断时抛出。  
类未找到异常（ClassNotFoundException）：当试图加载类时找不到类定义时抛出。  
以上仅是 Java 异常类型的一部分，实际上还有很多其它的异常类型，不同的库和框架也可能会定义自己的异常类型。