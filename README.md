# Mini Job Platform

Mini Job Platform — bu Spring Boot asosida qurilgan backend loyiha bo‘lib, foydalanuvchilarga web forma orqali ariza yuborish, CV/Resume fayl yuklash va yuborilgan arizalarni Telegram bot orqali ko‘rish imkonini beradi.

## Loyiha maqsadi

Loyihaning asosiy vazifasi:

- foydalanuvchi web forma orqali ariza topshiradi
- CV/Resume fayl yuklaydi
- backend arizani H2 database ga saqlaydi
- backend Telegram botga yangi ariza haqida xabar yuboradi
- Telegram bot orqali barcha arizalarni ko‘rish mumkin bo‘ladi

## Texnologiyalar

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- H2 Database
- Lombok
- Multipart File Upload
- Telegram Bot API
- RestTemplate
- Spring Scheduling

## Loyiha imkoniyatlari

- Ariza yuborish
- CV / Resume fayl yuklash
- Arizani database ga saqlash
- Telegram botga yangi ariza haqida xabar yuborish
- Telegram bot orqali barcha arizalarni ko‘rish
- `ApiResponse` formatida javob qaytarish
- H2 Console orqali database ni ko‘rish

## Ariza modeli

Loyihada quyidagi ma’lumotlar saqlanadi:

- `id`
- `fullName`
- `phone`
- `email`
- `position`
- `message`
- `cvFileName`
- `cvFilePath`
- `createdAt`

## API Endpointlar

### 1. POST `/api/applications`

Yangi ariza yuboradi.

Bu endpoint `multipart/form-data` qabul qiladi.

#### Form fieldlar:

- `fullName`
- `phone`
- `email`
- `position`
- `message`
- `cvFile`

#### So‘rov:

```http
POST http://localhost:8080/api/applications
Content-Type: multipart/form-data
```

#### Javob namunasi:

```json
{
  "message": "Ariza muvaffaqiyatli yuborildi",
  "success": true,
  "status": "CREATED",
  "body": {
    "id": 1,
    "fullName": "Ali Valiyev",
    "phone": "+998901234567",
    "email": "ali@gmail.com",
    "position": "Frontend Developer",
    "message": "I want to join your team",
    "cvFileName": "resume.pdf",
    "createdAt": "2026-03-18T10:20:30"
  }
}
```

### 2. GET `/api/applications`

Barcha arizalarni qaytaradi.

#### So‘rov:

```http
GET http://localhost:8080/api/applications
```

#### Javob namunasi:

```json
{
  "message": "Barcha arizalar olindi",
  "success": true,
  "status": "OK",
  "body": [
    {
      "id": 1,
      "fullName": "Ali Valiyev",
      "phone": "+998901234567",
      "email": "ali@gmail.com",
      "position": "Frontend Developer",
      "message": "I want to join your team",
      "cvFileName": "resume.pdf",
      "createdAt": "2026-03-18T10:20:30"
    }
  ]
}
```

## Telegram bot komandalar

Telegram bot quyidagi komandalarni qo‘llab-quvvatlaydi:

### `/start`

Botni ishga tushiradi va mavjud buyruqlarni ko‘rsatadi.

### `/applications`

Database dagi barcha arizalarni bot orqali chiqaradi.

## Telegram bot ishlash mantig‘i

- foydalanuvchi web forma orqali ariza yuboradi
- backend arizani database ga saqlaydi
- agar CV yuklangan bo‘lsa, fayl local `uploads` papkaga saqlanadi
- backend Telegram botga yangi ariza haqida xabar yuboradi
- Telegram bot admin chatga:
  - ism
  - telefon
  - email
  - lavozim
  - xabar
  - CV haqida ma’lumot yuboradi

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

## `application.properties`

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

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

file.upload-dir=uploads

telegram.bot.token=BU_YERGA_TOKEN
telegram.bot.username=BU_YERGA_BOT_USERNAME
telegram.bot.admin-chat-id=BU_YERGA_CHAT_ID
```

## Projectni ishga tushirish

1. `pom.xml` dependencylarini tekshir
2. `application.properties` ni to‘g‘ri sozla
3. Telegram bot tokenini `application.properties` ga yoz
4. Admin chat id ni `telegram.bot.admin-chat-id` ga yoz
5. Projectni run qil
6. Postman yoki frontend orqali `/api/applications` endpointga request yubor
7. Telegram bot orqali `/start` va `/applications` komandalarini sinab ko‘r
8. H2 Console orqali database ni tekshir

## Upload qilingan fayllar

Yuklangan CV / Resume fayllar local ravishda quyidagi papkaga saqlanadi:

```text
uploads/
```

## Validatsiya

Loyihada quyidagi validatsiyalar ishlatiladi:

- `fullName` bo‘sh bo‘lmasligi kerak
- `phone` bo‘sh bo‘lmasligi kerak
- `email` bo‘sh bo‘lmasligi kerak
- `email` format to‘g‘ri bo‘lishi kerak
- `position` bo‘sh bo‘lmasligi kerak

## Arxitektura

Loyiha quyidagi qatlamlar asosida qurilgan:

- `controller` — request qabul qiladi
- `service` — business logic
- `repository` — database bilan ishlaydi
- `mapper` — entity va dto o‘rtasida mapping qiladi
- `telegram` — bot bilan ishlaydi
- `exception` — global xatolarni ushlaydi

## Keyingi kengaytirishlar

- Arizani ID bo‘yicha olish
- Arizani o‘chirish
- Arizalarni pagination bilan olish
- Arizalarni search qilish
- CV faylni yuklab olish endpointi
- Admin panel qo‘shish
- Telegram botda inline buttonlar qo‘shish
- Deploy qilish
- PostgreSQL yoki MySQL ga o‘tish

## Xulosa

Bu loyiha web forma, fayl yuklash, database va Telegram bot integratsiyasini birlashtirgan mini backend tizimdir. Loyihaning asosiy vazifasi foydalanuvchidan ariza qabul qilish va uni Telegram orqali admin ga tez yetkazishdan iborat.
