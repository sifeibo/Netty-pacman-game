  

#### 软件安装：

1.  安装jdk-1.8.0_161

2.  安装集成开发坏境IntelliJ IDEA 2018.2.5

3.  安装Mysql数据库

 

#### Jar包导入：

1.  使用IDEA打开项目PacmanServer

2.  点击Project Structure

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image002.jpg)

 

3.  在Modules中选择Dependencies, 点击”+”号选择Library->Java导入jar包

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image004.jpg)

4.  在PacmanServer项目中导入mysql-connector-java-5.1.44-bin.jar和netty-all-5.0.0.Alpha2.jar

5.  在PacmanClient和PacmanClient-副本项目中导入netty-all-5.0.0.Alpha2.jar

 

#### 运行sql文件:

1.  在mysql数据库中运行pacman.sql文件

2.  添加用户：root@% 密码：root

 



#### **一、** 启动系统

PacmanSever

1. 进入src/Pacman/service/Server.java文件

2. 右键，点击Run ’Server.main()’

 

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image002.jpg)

 

 

PacmanClient：

1. 进入src/Pacman/service/Client.java文件

2. 修改host为server端所在的ip地址

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image004.jpg)

\3. 右键，点击点击Run ’Client.main()’

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image006.jpg)

 

 

**PacmanClient-****副本：**

操作步骤和PacmanClient相同

 

 

**二、** **系统使用**

 

**1.** **登录：**

出现如下画面可以进行登录或者注册操作

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image008.jpg)

使用正确的账号密码登录后点击确定，跳转到游戏界面

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image010.jpg)

按下s键则开始游戏

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image012.jpg)

![img](file:///C:/Users/feibo/AppData/Local/Temp/msohtmlclip1/01/clip_image014.jpg)