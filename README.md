# spring-security
 spring security with Basic Http Authentication

 
http://localhost:8080/api/auth/signup (POST) is public URL where user can provide username, password and role and those user information are stored in the database. for further authentication.
{
  "userName": "test",
  "password": "12345",
  "role": "ADMIN"
}

This is the Request json for /signup URL.
After successfull signup 

In order to  access the secured resources http://localhost:8080/api/secure/data(GET) 
User can use the SignedUp user name and password by passing them in Authorization header.
