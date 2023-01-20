import cn.hutool.core.lang.Assert;
import common.CommonConstants;
import common.util.CompareUtils;
import common.util.SysOut;
import common.util.SysRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nibnait on 2022/07/06
 */
@Slf4j
public class UnitTestDemo {

    @Test
    public void loopTestCase() {
        for (int i = 0; i < CommonConstants.TEST_CASE_COUNT_1000; i++) {
            testCase();
        }
    }

    @Test
    public void testCase() {
        int maxLen = 20;
        int maxValue = 30;
        int[] arr = SysRandom.generateArrNaturalNum(maxLen);
        int aim = SysRandom.generateNaturalNum(maxValue);
        int ans1 = test(arr, aim);
        int ans2 = test(arr, aim);

        if (ans1 != ans2) {
            SysOut.printArray(arr);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }
    }

    @Test
    public void test2() {
        int[] arr = SysRandom.randomArrNaturalNumRandomLength(20);

        int ans1 = test();
        int ans2 = test();

        if (ans1 != ans2) {
            SysOut.printArray(arr);
            SysOut.printf("ans1: {}, ans2: {}", ans1, ans2);
            throw new RuntimeException();
        }

        SysOut.printArray(arr);
        SysOut.println(ans1);
    }

    @Test
    public void test1() {
        String str1 = "a12b3c456d";
        String str2 = "1ef23ghi4j56k";
        int ans1 = test(str1, str2);
        int ans2 = test(str1, str2);
        Assert.equals(ans1, ans2);

        str1 = "abcde";
        str2 = "ace";
        ans1 = test(str1, str2);
        ans2 = test(str1, str2);
        Assert.equals(ans1, ans2);
    }

    @Test
    public void testCase1() {
        int[] arr = SysRandom.generateArrNaturalNum();
        int[] copyArr = Arrays.copyOf(arr, arr.length);

        if (!CompareUtils.isSortAsc(arr)) {
            log.error("原数组: {}", copyArr);
            log.error("排序后: {}", arr);
            throw new RuntimeException();
        }
    }

    public int test(Object... args) {
        boolean f1 = true;
        boolean f2 = false;

        f1 = f1 || f2;
        System.out.println(f1);

        return 0;
    }

    @Test
    public void t() {
        String s = "第一章 业务架构知识学习\n" +
                "\n" +
                "\n" +
                "1.01-SpringCloud Alibaba云商城大纲介绍\n" +
                "7分钟\n" +
                "\n" +
                "2.02-SpringCloud Alibaba云商城课程目标介绍\n" +
                "2分钟\n" +
                "\n" +
                "3.03-电商知识学习\n" +
                "4分钟\n" +
                "\n" +
                "4.04-云商城业务功能介绍\n" +
                "6分钟\n" +
                "\n" +
                "5.05-前后端分离开发模式讲解\n" +
                "6分钟\n" +
                "\n" +
                "6.06-前后端分离-swagger使用\n" +
                "13分钟\n" +
                "\n" +
                "7.07-云商城技术架构讲解\n" +
                "10分钟\n" +
                "\n" +
                "8.08-云商城表结构分析\n" +
                "17分钟\n" +
                "\n" +
                "9.09-项目开发环境准备\n" +
                "6分钟\n" +
                "\n" +
                "10.10-项目结构讲解\n" +
                "9分钟\n" +
                "\n" +
                "11.11-工程搭建-顶级父工程搭建\n" +
                "6分钟\n" +
                "\n" +
                "12.12-工程搭建-各个模块父工程搭建\n" +
                "6分钟\n" +
                "\n" +
                "13.13-工程搭建-公共工程搭建\n" +
                "9分钟\n" +
                "\n" +
                "14.14-MyBatisPlus集成-MyBatisPlus介绍\n" +
                "5分钟\n" +
                "\n" +
                "15.15-MyBatisPlus集成-集成配置\n" +
                "17分钟\n" +
                "\n" +
                "16.16-MyBatisPlus集成-增删改实现\n" +
                "15分钟\n" +
                "\n" +
                "17.17-MyBatisPlus集成-多条件 分页搜索\n" +
                "16分钟\n" +
                "第二章 基础数据处理和分布式文件存储\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "7分钟\n" +
                "\n" +
                "2.02-Ceph特性介绍\n" +
                "8分钟\n" +
                "\n" +
                "3.03-Ceph架构图讲解\n" +
                "6分钟\n" +
                "\n" +
                "4.04-集群环境准备\n" +
                "18分钟\n" +
                "\n" +
                "5.05-Ceph集群搭建-Ceph安装\n" +
                "5分钟\n" +
                "\n" +
                "6.06-Ceph集群-OSD安装\n" +
                "5分钟\n" +
                "\n" +
                "7.07-Ceph集群-Dashboard安装\n" +
                "5分钟\n" +
                "\n" +
                "8.08-Ceph集群-CephFS安装\n" +
                "3分钟\n" +
                "\n" +
                "9.09-Ceph Swift Api开发准备工作\n" +
                "6分钟\n" +
                "\n" +
                "10.10-SpringBoot集成Ceph实现文件上传和下载\n" +
                "17分钟\n" +
                "\n" +
                "11.11-Spu和Sku关系讲解\n" +
                "12分钟\n" +
                "\n" +
                "12.12-Spu和Sku表结构介绍\n" +
                "4分钟\n" +
                "\n" +
                "13.13-产品发布流程分析\n" +
                "6分钟\n" +
                "\n" +
                "14.14-产品发布-分类查询\n" +
                "11分钟\n" +
                "\n" +
                "15.15-产品发布-品牌加载\n" +
                "11分钟\n" +
                "\n" +
                "16.16-产品发布-属性加载\n" +
                "8分钟\n" +
                "\n" +
                "17.17-产品发布-组合数据分析\n" +
                "8分钟\n" +
                "\n" +
                "18.18-产品发布-保存功能\n" +
                "16分钟\n" +
                "\n" +
                "19.19-产品发布-保存功能演示\n" +
                "7分钟\n" +
                "\n" +
                "20.20-产品修改-代码实现\n" +
                "3分钟\n" +
                "\n" +
                "21.21-MyBatisPlus代码生成器讲解\n" +
                "11分钟\n" +
                "第三章 高性能门户网构建\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "4分钟\n" +
                "\n" +
                "2.02-OpenResty学习和安装讲解\n" +
                "14分钟\n" +
                "\n" +
                "3.03-OpenResty发布静态网站\n" +
                "14分钟\n" +
                "\n" +
                "4.04-Lua脚本介绍\n" +
                "2分钟\n" +
                "\n" +
                "5.05-Lua脚本常用操作讲解\n" +
                "9分钟\n" +
                "\n" +
                "6.06-Lua脚本编程学习\n" +
                "4分钟\n" +
                "\n" +
                "7.07-多级缓存架构设计\n" +
                "6分钟\n" +
                "\n" +
                "8.08-门户首页推广产品加载实现\n" +
                "13分钟\n" +
                "\n" +
                "9.09-缓存操作注解讲解\n" +
                "3分钟\n" +
                "\n" +
                "10.10-缓存注解实现缓存加载和删除\n" +
                "9分钟\n" +
                "\n" +
                "11.11-缓存注解操作缓存修改\n" +
                "3分钟\n" +
                "\n" +
                "12.12-注解缓存操作-缓存操作优化\n" +
                "4分钟\n" +
                "\n" +
                "13.13-缓存操作-feign接口编写\n" +
                "3分钟\n" +
                "\n" +
                "14.14-Lua Redis缓存操作\n" +
                "13分钟\n" +
                "\n" +
                "15.15-Nginx代理缓存实现\n" +
                "12分钟\n" +
                "\n" +
                "16.16-Nginx代理缓存讲解\n" +
                "7分钟\n" +
                "\n" +
                "17.17-缓存一致性-Canal原理讲解\n" +
                "12分钟\n" +
                "\n" +
                "18.18-缓存一致性-Canal安装\n" +
                "7分钟\n" +
                "\n" +
                "19.19-缓存一致性-Canal监听数据库增量日志消费\n" +
                "8分钟\n" +
                "\n" +
                "20.20-缓存一致性-实时更新Redis缓存实现\n" +
                "5分钟\n" +
                "第四章 海量数据搜索实现\n" +
                "\n" +
                "\n" +
                "1.01-学习目标介绍\n" +
                "6分钟\n" +
                "\n" +
                "2.02-搜索工程搭建实现\n" +
                "12分钟\n" +
                "\n" +
                "3.03-ES索引增加和删除功能实现\n" +
                "11分钟\n" +
                "\n" +
                "4.04-ES索引库数据实时更新操作实现\n" +
                "20分钟\n" +
                "\n" +
                "5.05-ES实现关键词搜索\n" +
                "11分钟\n" +
                "\n" +
                "6.06-ES商品搜索-分组数据条件回显查询\n" +
                "19分钟\n" +
                "\n" +
                "7.07-ES商品搜索-动态属性条件回显查询\n" +
                "18分钟\n" +
                "\n" +
                "8.08-ES搜索-分类、品牌、价格区间搜索\n" +
                "9分钟\n" +
                "\n" +
                "9.09-ES搜索-动态属性搜索实现\n" +
                "4分钟\n" +
                "\n" +
                "10.10-ES搜索-分页实现\n" +
                "3分钟\n" +
                "\n" +
                "11.11-ES搜索-多条件查询测试和问题讲解\n" +
                "9分钟\n" +
                "\n" +
                "12.12-ES搜索-多种排序实现\n" +
                "5分钟\n" +
                "\n" +
                "13.13-ES搜索-高亮功能配置\n" +
                "5分钟\n" +
                "\n" +
                "14.14-ES搜索-高亮数据映射转换实现\n" +
                "16分钟\n" +
                "第五章 商品详情页实战\n" +
                "\n" +
                "\n" +
                "1.01-学习目标介绍\n" +
                "3分钟\n" +
                "\n" +
                "2.02-Thymeleaf集成SpringBoot讲解\n" +
                "11分钟\n" +
                "\n" +
                "3.03-搜索渲染-结果集展示实现\n" +
                "11分钟\n" +
                "\n" +
                "4.04-搜索渲染-关键词回显\n" +
                "7分钟\n" +
                "\n" +
                "5.05-搜索渲染-品牌和分类搜索条件回显实现\n" +
                "7分钟\n" +
                "\n" +
                "6.06-搜索渲染-动态条件处理\n" +
                "7分钟\n" +
                "\n" +
                "7.07-搜索渲染-搜索条件记录并回显\n" +
                "11分钟\n" +
                "\n" +
                "8.08-搜索渲染-条件操作和URL地址关系分析\n" +
                "7分钟\n" +
                "\n" +
                "9.09-搜索渲染-条件动态添加和移除操作实现\n" +
                "10分钟\n" +
                "\n" +
                "10.10-搜索渲染-分页实现\n" +
                "14分钟\n" +
                "\n" +
                "11.11-搜索渲染-排序实现\n" +
                "5分钟\n" +
                "\n" +
                "12.12-商品详情页生成-工程搭建\n" +
                "5分钟\n" +
                "\n" +
                "13.13-商品详情页-页面数据查询\n" +
                "9分钟\n" +
                "\n" +
                "14.14-详情页静态页生成实现\n" +
                "5分钟\n" +
                "\n" +
                "15.15-详情页生成数据加载实现\n" +
                "11分钟\n" +
                "\n" +
                "16.16-详情页生成-静态页生成测试\n" +
                "5分钟\n" +
                "\n" +
                "17.17-详情页生成-分类填充\n" +
                "3分钟\n" +
                "\n" +
                "18.18-静态页生成-图片和属性填充\n" +
                "4分钟\n" +
                "\n" +
                "19.19-静态页生成-默认Sku选中实现\n" +
                "7分钟\n" +
                "\n" +
                "20.20-静态页-属性选中实现\n" +
                "6分钟\n" +
                "\n" +
                "21.21-静态页-动态选中Sku实现\n" +
                "9分钟\n" +
                "\n" +
                "22.22-静态页实时更新实现\n" +
                "7分钟\n" +
                "第六章 购物车、订单\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "2分钟\n" +
                "\n" +
                "2.02-MongoDB介绍\n" +
                "6分钟\n" +
                "\n" +
                "3.03-MongoDB安装\n" +
                "7分钟\n" +
                "\n" +
                "4.04-MongoDB常用操作讲解\n" +
                "16分钟\n" +
                "\n" +
                "5.05-购物车功能分析\n" +
                "5分钟\n" +
                "\n" +
                "6.06-SpringData MongoDB特点介绍\n" +
                "5分钟\n" +
                "\n" +
                "7.07-购物车工程搭建\n" +
                "9分钟\n" +
                "\n" +
                "8.08-添加购物车功能\n" +
                "18分钟\n" +
                "\n" +
                "9.09-商品加入购物车流程测试\n" +
                "5分钟\n" +
                "\n" +
                "10.10-购物车列表实现\n" +
                "7分钟\n" +
                "\n" +
                "11.11-购物车页面对接\n" +
                "3分钟\n" +
                "\n" +
                "12.12-订单功能分析\n" +
                "2分钟\n" +
                "\n" +
                "13.13-收件地址加载\n" +
                "9分钟\n" +
                "\n" +
                "14.14-选中购物车列表查询\n" +
                "7分钟\n" +
                "\n" +
                "15.15-选中购物车删除操作实现\n" +
                "5分钟\n" +
                "\n" +
                "16.16-订单工程搭建\n" +
                "9分钟\n" +
                "\n" +
                "17.17-库存递减实现\n" +
                "7分钟\n" +
                "\n" +
                "18.18-下单功能实现\n" +
                "16分钟\n" +
                "\n" +
                "19.19-页面操作购物车、下单流程测试\n" +
                "10分钟\n" +
                "第七章 分布式事务解决方案\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "3分钟\n" +
                "\n" +
                "2.02-分布式事务介绍\n" +
                "11分钟\n" +
                "\n" +
                "3.03-CAP原理\n" +
                "5分钟\n" +
                "\n" +
                "4.04-BASE理论讲解\n" +
                "6分钟\n" +
                "\n" +
                "5.05-2PC和3PC事务模型讲解\n" +
                "8分钟\n" +
                "\n" +
                "6.06-TCC事务模型讲解\n" +
                "6分钟\n" +
                "\n" +
                "7.07-MQ分布式事务方案讲解\n" +
                "4分钟\n" +
                "\n" +
                "8.08-下单分布式事务问题分析\n" +
                "4分钟\n" +
                "\n" +
                "9.09-Seata介绍\n" +
                "6分钟\n" +
                "\n" +
                "10.10-Seata-AT模式讲解\n" +
                "16分钟\n" +
                "\n" +
                "11.11-Seata 分布式事务实战\n" +
                "19分钟\n" +
                "\n" +
                "12.12-Seata控制下单分布式事务测试\n" +
                "7分钟\n" +
                "\n" +
                "13.13-RocketMQ安装\n" +
                "8分钟\n" +
                "\n" +
                "14.14-RocketMQ事务消息运行流程讲解\n" +
                "9分钟\n" +
                "\n" +
                "15.15-支付微服务搭建\n" +
                "6分钟\n" +
                "\n" +
                "16.16-RocketMQ事务消息案例准备工作\n" +
                "11分钟\n" +
                "\n" +
                "17.17-RocketMQ事务消息绑定本地事务业务操作\n" +
                "7分钟\n" +
                "\n" +
                "18.18-RocketMQ事务消息监听实现\n" +
                "5分钟\n" +
                "\n" +
                "19.19-RocketMQ事务消息消费实现\n" +
                "3分钟\n" +
                "第八章 云商城 微信支付\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "5分钟\n" +
                "\n" +
                "2.02-摘要加密-MD5讲解\n" +
                "15分钟\n" +
                "\n" +
                "3.03-验签流程讲解\n" +
                "7分钟\n" +
                "\n" +
                "4.04-Base64编码讲解\n" +
                "7分钟\n" +
                "\n" +
                "5.05-AES加密算法讲解\n" +
                "7分钟\n" +
                "\n" +
                "6.06-AES算法库下载并安装\n" +
                "3分钟\n" +
                "\n" +
                "7.07-AES加密解密实现\n" +
                "9分钟\n" +
                "\n" +
                "8.08-AES加密算法测试\n" +
                "5分钟\n" +
                "\n" +
                "9.09-微信支付模式二实现流程分析\n" +
                "19分钟\n" +
                "\n" +
                "10.10-微信支付SDK安装\n" +
                "9分钟\n" +
                "\n" +
                "11.11-微信支付统一下单实现\n" +
                "10分钟\n" +
                "\n" +
                "12.12-微信扫码支付测试\n" +
                "3分钟\n" +
                "\n" +
                "13.13-扫码支付安全验签流程讲解\n" +
                "5分钟\n" +
                "\n" +
                "14.14-支付数据加密验签实现\n" +
                "17分钟\n" +
                "\n" +
                "15.15-内网穿透工具介绍\n" +
                "4分钟\n" +
                "\n" +
                "16.16-支付通知数据获取实现\n" +
                "11分钟\n" +
                "\n" +
                "17.17-支付成功状态变更\n" +
                "5分钟\n" +
                "\n" +
                "18.18-支付状态主动查询实现\n" +
                "14分钟\n" +
                "\n" +
                "19.19-订单支付流程测试\n" +
                "4分钟\n" +
                "\n" +
                "20.20-退款实现流程分析\n" +
                "4分钟\n" +
                "\n" +
                "21.21- 微信退款申请记录操作\n" +
                "11分钟\n" +
                "\n" +
                "22.22-取消订单申请消息发送\n" +
                "16分钟\n" +
                "\n" +
                "23.23-退款申请实现\n" +
                "6分钟\n" +
                "\n" +
                "24.24-退款结果解密讲解\n" +
                "10分钟\n" +
                "\n" +
                "25.25-退款结果解析测试\n" +
                "5分钟\n" +
                "\n" +
                "26.26-作业说明\n" +
                "1分钟\n" +
                "第九章 商品秒杀-数据处理\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "3分钟\n" +
                "\n" +
                "2.02-秒杀业务设计和表结构设计讲解\n" +
                "7分钟\n" +
                "\n" +
                "3.03-秒杀业务特点讲解\n" +
                "3分钟\n" +
                "\n" +
                "4.04-秒杀架构设计讲解\n" +
                "5分钟\n" +
                "\n" +
                "5.05秒杀工程搭建\n" +
                "5分钟\n" +
                "\n" +
                "6.06-活动列表查询实现\n" +
                "10分钟\n" +
                "\n" +
                "7.07-秒杀商品导入索引库实现\n" +
                "12分钟\n" +
                "\n" +
                "8.08-秒杀商品导入索引库测试\n" +
                "5分钟\n" +
                "\n" +
                "9.09-秒杀商品详情页生成后台代码实现\n" +
                "13分钟\n" +
                "\n" +
                "10.10-秒杀商品详情页数据填充\n" +
                "4分钟\n" +
                "\n" +
                "11.11-秒杀商品详情页发布\n" +
                "11分钟\n" +
                "\n" +
                "12.12-详情页和索引实时更新操作\n" +
                "8分钟\n" +
                "\n" +
                "13.13-详情页和索引实时更新操作\n" +
                "2分钟\n" +
                "\n" +
                "14.14-动态定时任务对比\n" +
                "9分钟\n" +
                "\n" +
                "15.15-elasticjob静态定时任务实现\n" +
                "7分钟\n" +
                "\n" +
                "16.16-elasticjob动态定时任务操作\n" +
                "15分钟\n" +
                "\n" +
                "17.17-动态创建定时任务案例\n" +
                "8分钟\n" +
                "\n" +
                "18.18-动态定时任务测试\n" +
                "5分钟\n" +
                "\n" +
                "19.19-定时删除页面实现\n" +
                "23分钟\n" +
                "第一十章 商品秒杀-热门数据实时收集\n" +
                "\n" +
                "\n" +
                "1.01-课程目标\n" +
                "5分钟\n" +
                "\n" +
                "2.02-热门数据收集流程实现\n" +
                "6分钟\n" +
                "\n" +
                "3.03-Lua高级指令执行流程讲解\n" +
                "6分钟\n" +
                "\n" +
                "4.04-Kafka常用操作讲解\n" +
                "10分钟\n" +
                "\n" +
                "5.05-秒杀商品详情页发布\n" +
                "7分钟\n" +
                "\n" +
                "6.06-Lua操作Kafka组件安装\n" +
                "5分钟\n" +
                "\n" +
                "7.07-Lua收集日志实现\n" +
                "10分钟\n" +
                "\n" +
                "8.08-Apache Druid介绍\n" +
                "9分钟\n" +
                "\n" +
                "9.09-Apache Druid架构讲解\n" +
                "4分钟\n" +
                "\n" +
                "10.10-Apache Druid安装\n" +
                "9分钟\n" +
                "\n" +
                "11.11-Apache Druid批量数据导入\n" +
                "10分钟\n" +
                "\n" +
                "12.12-Apache Druid垂直日志收集实时数据流摄入\n" +
                "16分钟\n" +
                "\n" +
                "13.13-DruidSQL语法讲解\n" +
                "8分钟\n" +
                "\n" +
                "14.14-DruidSQL查询实战\n" +
                "9分钟\n" +
                "第一十一章 商品秒杀-程序隔离解决方案\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "6分钟\n" +
                "\n" +
                "2.02-查询Druid工程搭建\n" +
                "13分钟\n" +
                "\n" +
                "3.03-MyBatis查询Apache Druid-查询所有\n" +
                "4分钟\n" +
                "\n" +
                "4.04-MyBatis查询Apache Druid-查询前N条记录\n" +
                "5分钟\n" +
                "\n" +
                "5.05-MyBatis查询Apache Druid-分页查询\n" +
                "14分钟\n" +
                "\n" +
                "6.06-MyBatis查询Apache Druid分页 排序\n" +
                "5分钟\n" +
                "\n" +
                "7.07-MyBatis查询Apache Druid-时间查询操作\n" +
                "9分钟\n" +
                "\n" +
                "8.08-MyBatis查询Apache Druid-排除指定数据\n" +
                "10分钟\n" +
                "\n" +
                "9.09-热门商品分析实现\n" +
                "14分钟\n" +
                "\n" +
                "10.10-热门数据定时分析\n" +
                "12分钟\n" +
                "\n" +
                "11.11-热门商品分离操作\n" +
                "12分钟\n" +
                "\n" +
                "12.12-微服务网关搭建\n" +
                "9分钟\n" +
                "\n" +
                "13.13-热门商品抢单排队实现\n" +
                "21分钟\n" +
                "\n" +
                "14.14-热门商品抢单和非热门商品抢单程序分离实现\n" +
                "6分钟\n" +
                "\n" +
                "15.15-抢单监听实现\n" +
                "5分钟\n" +
                "\n" +
                "16.16-热门商品抢单下单实现\n" +
                "18分钟\n" +
                "\n" +
                "17.17-抢单过程问题分析\n" +
                "4分钟\n" +
                "\n" +
                "18.18-分布式锁控制抢单超卖现象\n" +
                "18分钟\n" +
                "第一十二章 网关鉴权\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "4分钟\n" +
                "\n" +
                "2.02-权限校验流程分析\n" +
                "7分钟\n" +
                "\n" +
                "3.03-权限表结构分析\n" +
                "7分钟\n" +
                "\n" +
                "4.04-JWT令牌介绍\n" +
                "7分钟\n" +
                "\n" +
                "5.05-JWT令牌结构\n" +
                "5分钟\n" +
                "\n" +
                "6.06-JWT令牌创建和解析\n" +
                "13分钟\n" +
                "\n" +
                "7.07-登录创建令牌实现\n" +
                "10分钟\n" +
                "\n" +
                "8.08-JWT令牌IP安全校验封装\n" +
                "6分钟\n" +
                "\n" +
                "9.09-JWT令牌基于IP校验\n" +
                "23分钟\n" +
                "\n" +
                "10.10-权限校验流程分析\n" +
                "11分钟\n" +
                "\n" +
                "11.11-鉴权工程搭建X\n" +
                "5分钟\n" +
                "\n" +
                "12.12-权限初始化加载实现\n" +
                "23分钟\n" +
                "\n" +
                "13.13-权限初始化测试\n" +
                "3分钟\n" +
                "\n" +
                "14.14-Gateway过滤器顺序调整\n" +
                "5分钟\n" +
                "\n" +
                "15.15-是否进行权限拦截控制\n" +
                "20分钟\n" +
                "\n" +
                "16.16-令牌校验抽取\n" +
                "2分钟\n" +
                "\n" +
                "17.17-角色权限匹配\n" +
                "7分钟\n" +
                "\n" +
                "18.18-角色权限测试\n" +
                "5分钟\n" +
                "第一十三章 服务安全控制\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "3分钟\n" +
                "\n" +
                "2.02-常见限流方案\n" +
                "8分钟\n" +
                "\n" +
                "3.03-微服务网关限流操作\n" +
                "15分钟\n" +
                "\n" +
                "4.04-Nginx速率限流讲解\n" +
                "9分钟\n" +
                "\n" +
                "5.05-Nginx并发量限流实现\n" +
                "12分钟\n" +
                "\n" +
                "6.06-Nginx黑白名单操作\n" +
                "4分钟\n" +
                "\n" +
                "7.07-Redis集群原理分析\n" +
                "6分钟\n" +
                "\n" +
                "8.08-Redis集群搭建\n" +
                "12分钟\n" +
                "\n" +
                "9.09-SpringBoot链接Redis集群\n" +
                "2分钟\n" +
                "\n" +
                "10.10-Redis集群扩容和收容\n" +
                "16分钟\n" +
                "\n" +
                "11.11-缓存灾难问题介绍\n" +
                "6分钟\n" +
                "\n" +
                "12.12-布隆过滤器原理\n" +
                "7分钟\n" +
                "\n" +
                "13.13-Gava布隆过滤器学习\n" +
                "6分钟\n" +
                "\n" +
                "14.14-Redis布隆过滤器无效请求路径过滤功能分析\n" +
                "4分钟\n" +
                "\n" +
                "15.15-Redis布隆过滤器数据初始化\n" +
                "6分钟\n" +
                "\n" +
                "16.16-基于Redis布隆过滤器实现无效路径过滤\n" +
                "7分钟\n" +
                "\n" +
                "17.17-无效路径过滤思考问题\n" +
                "1分钟\n" +
                "第一十四章 百万并发站点构建\n" +
                "\n" +
                "\n" +
                "1.01-课程目标介绍\n" +
                "5分钟\n" +
                "\n" +
                "2.02-Sentinel介绍\n" +
                "6分钟\n" +
                "\n" +
                "3.03-Sentinel功能介绍\n" +
                "6分钟\n" +
                "\n" +
                "4.04-Sentinel集成Gateway介绍\n" +
                "8分钟\n" +
                "\n" +
                "5.05-Sentinel集成Gateway\n" +
                "5分钟\n" +
                "\n" +
                "6.06-Sentinel集成Gateway-Api创建\n" +
                "9分钟\n" +
                "\n" +
                "7.07Sentinel集成Gateway-规则创建\n" +
                "7分钟\n" +
                "\n" +
                "8.08-Sentinel规则属性讲解\n" +
                "14分钟\n" +
                "\n" +
                "9.09-Sentinel控制台安装\n" +
                "3分钟\n" +
                "\n" +
                "10.10-SpringCloud Gateway对接Sentinel控制台\n" +
                "4分钟\n" +
                "\n" +
                "11.11-Sentinel控制台使用\n" +
                "8分钟\n" +
                "\n" +
                "12.12-Lvs介绍\n" +
                "6分钟\n" +
                "\n" +
                "13.13-Lvs负载均衡模式介绍\n" +
                "6分钟\n" +
                "\n" +
                "14.14-Vip配置\n" +
                "9分钟\n" +
                "\n" +
                "15.15-Lvs地址解析配置\n" +
                "7分钟\n" +
                "\n" +
                "16.16-Lvs Nginx集群创建\n" +
                "5分钟\n" +
                "\n" +
                "17.17-Lvs Nginx集群测试\n" +
                "1分钟";

        String[] split = s.split("\n");
        int sum = 0;
        for (String s1 : split) {
            if (s1.contains("分钟")) {
                String 分钟 = s1.substring(0, s1.indexOf("分钟"));
                sum += Integer.valueOf(分钟);
            }
        }

        System.out.println(sum);
    }

}
