FROM postgres
ENV POSTGRES_USER someuser
ENV POSTGRES_PASSWORD somepassword
ENV POSTGRES_DB somedb
COPY db.sql /docker-entrypoint-initdb.d/