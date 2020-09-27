- maven 打包配置
```
在maven 中配置 assembly plugin，
```
- 编译
```
cd ~/code/javaprojects/cf-forgot-java/cf-forgot-lib

mvn clean

mvn package
```
- 启动命令：
```
cd target/cf-forgot-lib-1.0-SNAPSHOT-assembly/lib
示例1：
java -javaagent:cf-forgot-lib-1.0-SNAPSHOT.jar  org.cf.forgot.lib.instrument.agent.demo1.Launcher

示例2:                                                
java -javaagent:cf-forgot-lib-1.0-SNAPSHOT.jar  org.cf.forgot.lib.instrument.agent.bci.Test

注：运行哪个示例，需要在编译打包之前修改 resources/META-INF/MANIFEST.MF 文件，文件内容参考 src/main/resources/bak 目录下备份文件
```

- 参考资料
```
1、https://www.infoq.cn/article/fH69pYPqZPF6Cj1UJy7X
2、https://mp.weixin.qq.com/s/3hXyFCgclsuoznNQ2ulC4g
3、demo 实例参考资料
https://blog.csdn.net/maoyeqiu/article/details/108005391
4、https://www.jianshu.com/p/d573456401eb
```
