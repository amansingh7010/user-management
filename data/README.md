# Citations
The authentication portion of our system follows the concepts for the `Spring Boot` framework which are detailed in this article: https://www.bezkoder.com/spring-boot-security-login-jwt/

## Files used/refactored from the citation
```📦usermanagement
 ┣ 📂controller
 ┃ ┣ 📜AuthController.java
 ┣ 📂model
 ┃ ┣ 📜EUserRole.java
 ┃ ┣ 📜User.java
 ┃ ┗ 📜UserRole.java
 ┣ 📂repository
 ┃ ┣ 📜UserRepository.java
 ┃ ┗ 📜UserRoleRepository.java
 ┣ 📂security
 ┃ ┣ 📂jwt
 ┃ ┃ ┣ 📜AuthEntryPointJwt.java
 ┃ ┃ ┣ 📜AuthTokenFilter.java
 ┃ ┃ ┗ 📜JwtUtils.java
 ┃ ┣ 📂services
 ┃ ┃ ┣ 📜UserDetailsImpl.java
 ┃ ┃ ┗ 📜UserDetailsServiceImpl.java
 ┃ ┗ 📜WebSecurityConfig.java
 ┗ 📜UserManagementApplication.java
 ```

 ## Files developed from scratch
 ```
 📦usermanagement
 ┣ 📂controller
 ┃ ┣ 📜ReportController.java
 ┃ ┣ 📜TestController.java
 ┃ ┣ 📜UserController.java
 ┃ ┗ 📜WebController.java
 ┣ 📂model
 ┃ ┣ 📜SuperUser.java
 ┃ ┣ 📜User.java
 ┃ ┗ 📜UserRole.java
 ┣ 📂payload
 ┃ ┣ 📂request
 ┃ ┃ ┣ 📜DeleteRequest.java
 ┃ ┃ ┣ 📜LoginRequest.java
 ┃ ┃ ┗ 📜SignupRequest.java
 ┃ ┗ 📂response
 ┃ ┃ ┣ 📜ListResponse.java
 ┃ ┃ ┣ 📜MessageResponse.java
 ┃ ┃ ┣ 📜Response.java
 ┃ ┃ ┣ 📜ResponseFactory.java
 ┃ ┃ ┗ 📜UserInfoResponse.java
 ┣ 📂service
 ┃ ┣ 📂auth
 ┃ ┃ ┣ 📜AuthService.java
 ┃ ┃ ┗ 📜AuthServiceImpl.java
 ┃ ┣ 📂report
 ┃ ┃ ┣ 📜PDFReportGenerator.java
 ┃ ┃ ┣ 📜ReportGenerator.java
 ┃ ┃ ┣ 📜ReportService.java
 ┃ ┃ ┣ 📜ReportServiceImpl.java
 ┃ ┃ ┣ 📜ReportType.java
 ┃ ┃ ┗ 📜XLSXReportGenerator.java
 ┃ ┗ 📂user
 ┃ ┃ ┣ 📜UserService.java
 ┃ ┃ ┗ 📜UserServiceImpl.java
 ```