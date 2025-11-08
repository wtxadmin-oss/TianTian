# Maven Project

这是一个简单的Maven项目示例。

## 项目结构

```
maven-project/
├── pom.xml                    # Maven配置文件
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/
│   │           └── App.java  # 主应用程序
│   └── test/
│       └── java/
│           └── com/example/
│               └── AppTest.java  # 测试类
└── target/                    # 编译输出目录
```

## 使用方法

### 编译项目
```bash
mvn compile
```

### 运行测试
```bash
mvn test
```

### 运行应用程序
```bash
java -cp target\classes com.example.App
```

### 打包项目
```bash
mvn package
```

### 清理项目
```bash
mvn clean
```

## 功能

- 简单的Java应用程序
- 包含JUnit 5测试
- 标准的Maven项目结构
- 支持基本的构建生命周期

## 技术栈

- Java 8
- Maven 3.x
- JUnit 5