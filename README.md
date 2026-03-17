# Mini Job Platform

Mini Job Platform — bu Spring Boot asosida qurilgan oddiy backend loyiha bo‘lib, foydalanuvchilarga ish e'lonlarini ko‘rish va yangi ish e'lonlari qo‘shish imkonini beradi.

## Texnologiyalar

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Lombok

## Loyiha imkoniyatlari

- Barcha ish e'lonlarini olish
- Yangi ish e'lonini qo‘shish
- Validation ishlatish
- `ApiResponse` formatida javob qaytarish
- H2 Console orqali database ko‘rish

## API Endpointlar

### 1. GET /api/jobs
Barcha ish e'lonlarini qaytaradi.

#### So‘rov:
```http
GET http://localhost:8080/api/jobs
```

### 2. POST /api/jobs
Yangi ish e'lonini qo‘shadi.

#### So‘rov:
```http
POST http://localhost:8080/api/jobs
Content-Type: application/json
```

#### Body:
```json
{
  "title": "Backend Developer",
  "company": "Codefy Group",
  "salary": 2500,
  "description": "Spring Boot bilan ishlaydigan backend developer kerak"
}
```

## H2 Console

Project ishga tushgandan keyin browserda quyidagi manzilni och:

```text
http://localhost:8080/h2-console
```

### Kirish uchun ma’lumotlar

**JDBC URL**
```text
jdbc:h2:mem:jobdb
```

**User Name**
```text
sa
```

**Password**
bo‘sh qoldiriladi.

## application.properties

```properties
spring.application.name=mini_job_platform

spring.datasource.url=jdbc:h2:mem:jobdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

server.port=8080
```

## Projectni ishga tushirish

1. `pom.xml` dependencylarni tekshir
2. `application.properties` ni joylashtir
3. Projectni run qil
4. Postman orqali API ni test qil
5. H2 Console orqali database ni ko‘r

## Keyingi kengaytirishlar

- `GET /api/jobs/{id}`
- `PUT /api/jobs/{id}`
- `DELETE /api/jobs/{id}`
- Search
- Pagination
- Sorting
- Frontend integration
- GitHub README ni boyitish
