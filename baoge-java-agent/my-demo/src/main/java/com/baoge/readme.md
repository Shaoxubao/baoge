	Java探针：https://www.cnblogs.com/aspirant/p/8796974.html
	
	my-agent打包（jar）
	MANIFEST.MF配置已在pom.xml配置，如下：

	Manifest-Version: 1.0
	Premain-Class: com.baoge.MyAgent
	Can-Redefine-Classes: true
	
	打包（导出jar包，包含MANIFEST.MF文件）结果是：
	my-agent-1.0-SNAPSHOT.jar
	myapp.jar
	
	如何执行 MyProgram.jar ？我们通过 -javaagent 参数来指定我们的Java代理包，值得一说的是 -javaagent 这个参数的个数是不限的，如果指定了多个，则会按指定的先后执行，执行完各个 agent 后，才会执行主程序的 main 方法：
	java -javaagent:E:\agent\myagent.jar=Hello1 -javaagent:E:\agent\myagent.jar=Hello2 -javaagent:E:\agent\myagent.jar=Hello3 -jar myapp.jar
	或者分别运行TimeTest和MyProgram
	
	vm参数：
	-javaagent:E:\workspace\baoge\baoge-java-agent\my-agent\target\my-agent-1.0-SNAPSHOT.jar