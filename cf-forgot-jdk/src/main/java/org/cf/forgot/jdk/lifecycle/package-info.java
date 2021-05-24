/**
 * Description:
 * 类生命周期
 * 1 加载
 * 2 链接： 验证
 *      词法分析 + 语法分析
 *
 * 3 链接： 准备
 *      静态常量初始化 + 赋值；
 *      静态变量初始化 + 分配默认值；
 *
 * 4 链接： 解析
 *      符号引用转换成直接引用
 *
 * 5 初始化
 *      父类： 静态变量 + 静态代码块
 *      子类： 静态变量 + 静态代码块
 *      父类： 实例变量 + 非静态代码块
 *      父类： 构造函数
 *      子类： 实例变量 + 非静态代码块
 *      子类： 构造函数
 *
 * 6 使用
 * 7 卸载
 *
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @date 2021/5/23
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @since todo
 */
package org.cf.forgot.jdk.lifecycle;