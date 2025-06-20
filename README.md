# manage-platform
A full-stack management platform built with Java backend and modern frontend (Vue 3), designed for internal systems and business operations.


# 调用链路
```json
HTTP 请求
↓
Controller 层（接收请求）
↓
Service 层（接口门面，事务控制）
↓
Manager 层（核心业务逻辑，调用 Mapper）
↓
Mapper / DAO 层（数据库操作）
```
