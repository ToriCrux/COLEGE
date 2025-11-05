#!/usr/bin/env bash
set -e

MYSQLD_DATA_DIR="/var/lib/mysql"

start_temp_mysql() {
  mysqld --skip-networking=0 --socket=/var/run/mysqld/mysqld.sock --daemonize

  for i in {30..0}; do
    if mysqladmin ping --silent; then break; fi
    echo "Aguardando mysqld iniciar... ($i)"
    sleep 1
  done
}

stop_temp_mysql() {
  mysqladmin shutdown || true
}

if [ ! -d "$MYSQLD_DATA_DIR/mysql" ]; then
  echo "Inicializando data dir..."
  mysqld --initialize-insecure --user=mysql
  start_temp_mysql

  echo "Configurando usuários/banco..."
  mysql -uroot <<-SQL
    ALTER USER 'root'@'localhost' IDENTIFIED BY '${MYSQL_ROOT_PASSWORD}';
    CREATE DATABASE IF NOT EXISTS \`${MYSQL_DATABASE}\` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
    CREATE USER IF NOT EXISTS '${MYSQL_USER}'@'%' IDENTIFIED BY '${MYSQL_PASSWORD}';
    GRANT ALL PRIVILEGES ON \`${MYSQL_DATABASE}\`.* TO '${MYSQL_USER}'@'%';
    FLUSH PRIVILEGES;
SQL

  if [ -f /docker-init/init.sql ]; then
    echo "Executando init.sql..."
    mysql -uroot -p"${MYSQL_ROOT_PASSWORD}" "${MYSQL_DATABASE}" < /docker-init/init.sql || true
  fi

  stop_temp_mysql
fi

exec "$@"

