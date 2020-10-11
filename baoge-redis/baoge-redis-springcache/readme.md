### 一、为什么要用SpringCache？，它解决了什么问题？
    SpringCache是Spring3.1发版发布的，他是对使用缓存进行封装和抽象，通过在方法上使用annotation
    注解就能拿到缓存结果。正是因为用了annotation，所以他解决了业务代码和缓存代码的耦合问题，即在
    不入侵业务代码的基础上让现有代码即刻支持缓存，它让开发人员无感知的使用了缓存。
    （注意：对于redis的缓存，SpringCache只支持String，其他的List、Set、ZSet、Hash都不支持）