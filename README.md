# 小贾书（xiaojiashu）运行教程

仿小红书的微服务项目，技术栈：JDK 17、Spring Boot 3.0.2、Spring Cloud Alibaba（Nacos）、MyBatis、Sa-Token、Redis、Cassandra、MinIO、Leaf 分布式 ID。

> 所有配置里的地址都写死了 `127.0.0.1`，换机器/换端口需要同步修改各服务的 `application-dev.yml`、`bootstrap.yml`、`leaf.properties`。

## 一、模块与端口

| 模块 | 说明 | 端口 |
| --- | --- | --- |
| xiaojiashu-gateway | 网关 | 8000 |
| xiaojia-auth | 认证服务（验证码、登录注册、登出、改密） | 8080 |
| xiaojiashu-oss | 对象存储（MinIO） | 8081 |
| xiaojiashu-user | 用户服务 | 8082 |
| xiaojiashu-kv | 笔记正文 K-V 存储（Cassandra） | 8084 |
| xiaojiashu-distributed-id-generator | 分布式 ID（Leaf，号段 + snowflake） | 8085 |
| xiaojiashu-note | 笔记服务 | 8086 |

## 二、环境依赖

- JDK 17 + Maven（推荐 3.8+）
- MySQL 8.x（原生安装，3306，账号 `root` / `123456`）
- Docker Desktop（用 `docker-compose.yml` 一键起其余中间件）

中间件与账号：

| 组件 | 地址 | 账号 |
| --- | --- | --- |
| MySQL | 127.0.0.1:3306 | root / 123456 |
| Redis | 127.0.0.1:6379 | 密码 `qwe123!@#` |
| Nacos | 127.0.0.1:8848 | nacos / nacos，命名空间 `xiaojiashu` |
| ZooKeeper | 127.0.0.1:2181 | 无（Leaf snowflake 模式依赖） |
| Cassandra | 127.0.0.1:9042 | 无（kv 服务用） |
| MinIO | 127.0.0.1:9000（控制台 9090） | jiahuiling / jiahuiling，桶 `xiaojiashu` 公开读 |

## 三、第一次运行的步骤

### 1. 导入 MySQL 数据库

在项目根目录打开 PowerShell 执行（PowerShell 不支持 `<` 重定向，用 `source` 的方式）：

```powershell
mysql -uroot -p123456 --default-character-set=utf8mb4 -e "source sql/mysql-backup.sql"
mysql -uroot -p123456 --default-character-set=utf8mb4 -e "source sql/nacos-config.sql"
```

- `mysql-backup.sql`：业务库 `leaf`（Leaf 号段表）、`xiaojiashu`（用户、角色权限、笔记、频道、话题等，含测试数据）
- `nacos-config.sql`：Nacos 自己的库 `nacos_config`（含命名空间 `xiaojiashu` 和配置），并自动创建 Nacos 容器用的 `nacos/nacos` 账号，必须用 root 执行

### 2. Docker 启动中间件

MinIO 和 Cassandra 的数据分别保存在项目目录下的 `data/minio` 和 `data/cassandra`，换电脑时可以直接复制或提交整个 `data` 目录。复制前请先停止容器；两台电脑不要同时运行并写入同一份数据目录。

```powershell
docker compose up -d
docker compose ps
```

自动完成三件事（首次需要等 Cassandra 1~2 分钟）：

- `cassandra-init`：执行 `sql/cassandra-xiaojiashu.cql`，建 keyspace `xiaojiashu` 和表 `note_content`
- `minio-init`：创建桶 `xiaojiashu` 并设置公开读（头像直链需要）
- Nacos：从 `nacos_config` 库读取命名空间和配置

查看初始化日志：

```powershell
docker compose logs -f cassandra-init minio-init
```

常用命令：

```powershell
docker compose down        # 停止；data 目录和 Redis/ZooKeeper 数据都会保留
docker compose down -v     # 清空 Redis/ZooKeeper 命名卷；不会删除 data 目录
```

### 3. 编译项目

必须先在根目录 install 一次，子模块之间有依赖：

```powershell
mvn clean install -DskipTests
```

### 4. 启动服务

用 IDEA 直接运行各模块的启动类（或对应模块目录下 `mvn spring-boot:run`），默认都是 `dev` 环境：

1. `XiaojiashuDistributedIdGeneratorBizApplication`（依赖 ZooKeeper + leaf 库）
2. `XiaojiashuOssApplication`、`XiaojiashuUserBizApplication`
3. `XiaojiashuAuthApplication`
4. `XiaojiashuKvBizApplication`、`XiaojiashuNoteBizApplication`、`XiaojiashuGatewayApplication`

启动后去 Nacos 控制台 `http://127.0.0.1:8848/nacos` 查看服务列表是否注册成功。

### 5. 冒烟测试（可选）

统一走网关 8000：

```powershell
# 1) 发送验证码（dev 环境验证码直接放在返回里）
curl.exe -X POST http://127.0.0.1:8000/auth/verification/code/send -H "Content-Type: application/json" -d "{\"phone\":\"15513003999\"}"

# 2) 验证码登录（type=1 验证码登录，type=2 密码登录）
curl.exe -X POST http://127.0.0.1:8000/auth/login -H "Content-Type: application/json" -d "{\"phone\":\"15513003999\",\"code\":\"收到的6位验证码\",\"type\":1}"
```

登录成功返回 token，后续请求带请求头：`Authorization: Bearer <token>`。

## 四、sql 目录说明

| 文件 | 用途 |
| --- | --- |
| `sql/mysql-backup.sql` | `leaf` + `xiaojiashu` 两个业务库（结构 + 数据） |
| `sql/nacos-config.sql` | `nacos_config` 库（Nacos 自身数据），含 OSS 服务必需的配置 |
| `sql/cassandra-xiaojiashu.cql` | Cassandra keyspace 和 `note_content` 表 |

> 关于 OSS 配置：Nacos 里 Data Id `xiaojiashu-oss-dev.yaml`（group `DEFAULT_GROUP`，命名空间 `xiaojiashu`）的内容是：
>
> ```yaml
> storage:
>   type: minio # 对象存储类型
> ```
>
> OSS 服务的 `FileStrategyFactory` 启动时读取 `storage.type`，所以这条配置不能少。它已经包含在 `sql/nacos-config.sql` 里，导入数据库后 Nacos 会自动恢复，不需要手动建。

## 五、常见问题

1. **Nacos 启动报连不上数据库**：Nacos 容器通过 `host.docker.internal` 连原生 MySQL。检查 MySQL 的 `bind-address` 是否为 `0.0.0.0`（只听 127.0.0.1 时容器连不上），以及 `nacos` 账号是否存在（重新导入 `sql/nacos-config.sql`）。
2. **分布式 ID 服务启动失败**：Leaf 开了 snowflake 模式（`leaf.properties` 配置了 ZooKeeper），必须先起 ZooKeeper；同时 `leaf` 库要已导入。
3. **头像上传失败**：确认 MinIO 已起、桶 `xiaojiashu` 存在且公开读（compose 会自动建，用 `docker compose logs minio-init` 排查）。
4. **Redis 报 NOAUTH**：密码是 `qwe123!@#`，在 `docker/redis.conf` 里。
5. **网关访问 user 服务的路径**：gateway 对 `/user/**` 做了 `StripPrefix=1`，而 user 控制器又带了 `/user` 前缀，所以经网关访问要写两层，例如 `/user/user/update`；auth 服务则是 `/auth/login` 这样一层。业务内部走 Feign 直连不受影响。
6. **Leaf 的号段**：`leaf` 库 `leaf_alloc` 表里 `leaf-segment-xiaojiashu-id` 这条记录控制小贾书号从多少开始发，`max_id` 越大 ID 越大，想从 1 开始可以清空重新插一条 `max_id=1`。

## 六、目录速览

```
docker-compose.yml      # 一键启动 redis/zookeeper/nacos/cassandra/minio
docker/redis.conf       # Redis 配置（密码）
data/                   # MinIO 和 Cassandra 数据（可随项目迁移）
sql/                    # 数据库脚本
xiaojia-framework/      # 公共框架（common、jackson、上下文、操作日志）
xiaojia-auth/           # 认证服务
xiaojiashu-gateway/     # 网关
xiaojiashu-user/        # 用户服务
xiaojiashu-oss/         # 对象存储服务
xiaojiashu-kv/          # 笔记正文 K-V 服务
xiaojiashu-note/        # 笔记服务
xiaojiashu-distributed-id-generator/  # Leaf 分布式 ID 服务
```
