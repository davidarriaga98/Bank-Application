**Ejecutar Normalmente**

1. Ejecutar scripts de base de datos

```console
mysql -u root < BaseDatos.sql
```

2. Compilar codigo

```console
cd account
./gradlew bootJar

cd client
./gradlew bootJar
```

3. Ejecutar

```console
cd account
java -jar ./build/libs/account-0.0.1-SNAPSHOT.war

cd client
java -jar ./build/libs/client-0.0.1-SNAPSHOT.war
```

**Ejecutar con docker compose**

```console
docker compose up --build
```
