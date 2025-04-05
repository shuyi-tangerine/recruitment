

# 特别声明

本程序仅用于个人学习 Java 各项技能时候使用，请不要做线上部署和使用，并且不能破坏所爬取网站的正常运作，因开发者自身的一些调试、运行导致问题本人概不负责，这里进行免责声明。

# 面向 AI 编程

```

用Gradle8管理的Java23项目，SpringBoot+MyBatis+SQLite，学习爬取招聘工作信息，项目有四个个模块
1.recruitment-common 一些公共的类如枚举、DO、DTO等
2.recruitment-repository 数据库连接的代码，数据量配置会在 resources/application.properties
3.recruitment-tencent 依赖recruitment-repository做一些逻辑，没有数据库的配置
4.recruitment-task 一来recruitment-repository做一些逻辑，没有数据库的配置

Invalid bound statement (not found): com.shuyi.recruitment.repository.mapper.TencentJobMapper.selectOne
org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.shuyi.recruitment.repository.mapper.TencentJobMapper.selectOne

```
