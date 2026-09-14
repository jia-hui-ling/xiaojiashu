-- =====================================================================
-- Cassandra 初始化脚本（xiaojiashu-kv 服务使用）
-- 兼容 Cassandra 4.x / 5.x，默认端口 9042，本地默认无账号密码
-- 执行方式（二选一）：
--   本机装有 cqlsh：cqlsh 127.0.0.1 9042 -f cassandra-xiaojiashu.cql
--   Docker 环境：  Get-Content cassandra-xiaojiashu.cql | docker compose exec -T cassandra cqlsh
-- =====================================================================

CREATE KEYSPACE IF NOT EXISTS xiaojiashu
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE xiaojiashu;

-- 笔记正文表（对应 NoteContentDO，@Table("note_content")）
CREATE TABLE IF NOT EXISTS note_content (
    id uuid PRIMARY KEY,
    content text
);
