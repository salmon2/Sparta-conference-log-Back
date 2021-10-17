# Sparta Conference Calender

## 달력으로 정리하는 회의록 만들기

달력으로 정리할 수 있는 회의 프로젝트의 백엔드 서버를 spring 과 jpa, security를 통하여 구성하였습니다.

<br>

## 시연영상
https://www.youtube.com/watch?v=3qWBMpdNZpE   
   
<image src="https://user-images.githubusercontent.com/44156173/137639603-ab4b8fba-1a78-47b5-8756-6cca7fe66f6c.png" width="400" height="300">

<br><br>

## 팀원소개
### FRONT END
https://github.com/sungminleeme/Sparta-conference-log
- 이성민(https://github.com/sungminleeme)
- 안송현(https://github.com/thdgus90)
- 신유빈(https://github.com/sinyubin)
### BACK END
https://github.com/salmon2/Sparta-conference-log-Back
- 박기남(https://github.com/salmon2)
- 강준규(https://github.com/Smallzoo-dev)
- 배나영(https://github.com/NayoungBae)

<br><br>

## 1. 제작기간 & 프로젝트 규모

- 2021/10/03 ~ 2021/10/04
- 소규모 팀프로젝트

<br><br>

## 2. 사용 기술

- `Backend`
    - JPA
    - Spring Boot
    - Spring web
    - Lombok
    - spring security
    - JWT
    - swagger
- `DB`
    - h2
    - mysql
- `Test Tool`
    - postman
    - Talend API Tester
<br><br>
## 3. 도메인 설계

<image src="https://user-images.githubusercontent.com/23234577/137581281-6dab3741-c231-4fa4-af3c-ea858cbcb046.png" width="500" height="450"><br><br>
    
## 4. API 설계

[API 설계](https://www.notion.so/bbd17c426a6642fb872a10412330c622)
<br><br>
## 5. 실행 화면

- DB 저장
    - select query와 insert query<br><br>
    <image src="https://user-images.githubusercontent.com/23234577/137581333-b662ebfc-ce12-47dc-9136-ff76b3b1091f.png" width="300" height="500"><br><br><br>
- swagger api docs
    - api 모음 집<br><br>
    <image src="https://user-images.githubusercontent.com/23234577/137581454-8d625dbc-a858-458a-b262-2003d4d47eab.png" width="550" height="500"><br><br>
    
    - 날짜에 따른 회의 목록 조회 관련 api<br><br>
    <image src="https://user-images.githubusercontent.com/23234577/137581484-4f5ca31d-19ad-4f92-bdf2-2a6bba647a56.png" width="550" height="300"><br><br><br>
    
- Talend API Tester 를 이용한 테스트
    - 로그인 테스트와 response에 나타나는 토큰 값<br><br>
    <image src="https://user-images.githubusercontent.com/23234577/137581496-ce396f25-4e57-433c-89cf-355668b84080.png" width="600" height="400"><br><br>

<br><br>

## 6. 핵심 기술

- 로그인
    - Spring security와 JWT를 이용한 로그인<br><br>
- 회원가입
    - 일반 회원가입 구현<br><br>
- 날짜에 따른 회의록 조회
    - 해당 년, 월, 일에 진행한 전체 회의 목록을 조회하고, 각 회의에 대한 상세 내용을 확인합니다.
    - 해당 년, 월, 일에 진행한 전체 회의 목록을 조회하고, 각 회의에 대한 상세 내용을 확인합니다.<br><br>
- 회의록 생성, 수정, 삭제
    - 회의를 참여한 사람과 회의 내용을 기록합니다.
    - 회의 내용을 수정하며, 회의 기록을 삭제합니다.<br><br>

<br><br>

## 7. 해결한 문제 정리해보기

1. **Year, Month, Day 키워드를 통해서 혹은 Year, Month에 해당하는 Conference도메인을 쉽게 가져올 수 없을까?**
   
    Date 도메인에 각각 `년`, `월`, `일` 컬럼을 생성하고, Conference 도메인과 `연관 관계`를 지어 조회하고 싶은 년, 월, 일 데이터로 request하면 해당 날짜에 진행한 회의 목록을 response하도록 하였다.

      <image src="https://user-images.githubusercontent.com/23234577/137581528-f8992b1b-ffa0-4c51-952d-802290d13289.png" width="600" height="400">

<br><br>
    
2. **프로젝트의 로그인 방식을 구현할 것 인가?**
    
    구현 가능한 방법은  `쿠키-세션` 과 `JWT Token` 방식이 있었다. 결론적으로는 `JWT Token`방식을 선택했는데 그 이유는 다음과 같다.
    ```
    1. 프론트와 백엔드 서버간의 통신을 이용한 환경에서 세션에 로그인 정보를 유지시켜 놓는 방식은 백엔드 측면에서는 어렵지 않지만 
       프론트엔드에서 매 통신마다 인증 정보를 확인하고 넘기는데 있어 헤더에 명시적인 토큰을 보내는게 
       구현이 용이 할 것이라고 생각했다. 
    
    2. 기존 프로젝트에서 구현해 보지 않은 방식이라 배우는 단계에서 새로운 방식으로 구현하는것이 도움이 될 것 같았다.
    ```

<br><br>

3. **구현한 API의 수정이 되었을 때 어떻게 관리하고 나타낼 수 있을까?**
    
    notion에 구현한 `API`를 수정할 때마다 바꾸는 건 번거로우며, 사람이 하는 일이기에 오류가 발생할 가능성이 존재한다.   
    그렇기에 이번 프로젝트에서는 `swagger` framework 를 통하여 동적으로 `web api docs` 를 구현하게 되었다.
    
    실제 swagger api 화면   
       
    <image src="https://user-images.githubusercontent.com/23234577/137581536-c1dff430-936e-400a-ba9a-bd87b1e883fa.png" width="500" height="400">
    
    
    회의글 관련 api
        
    <image src="https://user-images.githubusercontent.com/23234577/137581542-12fb1b40-f762-415e-835d-3c83ce5fd6e1.png" width="450" height="300">
   
<br><br>

4. **API를 설계한 request 와 response 데이터를 어떻게 쉽게 JSON 형식으로 보낼 수 있을까?**
    
    1. Controller에 `@RestController` 를 선언하여 JSON형식으로 데이터를 response하도록 한다.
   
    2. 사용자가 화면에서 입력한 데이터를 ReqeustDto에 저장하고, DB에서 데이터를 조회하여 
       response해야 할 JSON형식에 맞춰 Service에서 가공하여 response한다.
   
       <image src="https://user-images.githubusercontent.com/23234577/137581560-378f28c9-7086-4f08-9cbf-ef97fa5936a0.png" width="700" height="500">
    
<br><br>
          
5. **프론트 서버가 개발되기 전에 백엔드에서 구현한 API를 어떻게 테스트를 할 것인가?**
    
    `postman` 혹은 `Talend API Tester`를 통해서 rest api 툴을 사용하여 테스트를 시도하였다.
    
    `jwt token` 경우는 `header` 에 `Authorization` key에 토큰 값을 넣어서 테스트를 시도하였다.
    
    user 정보를 가져오는 api
    
    <image src="https://user-images.githubusercontent.com/23234577/137581565-c79f7ae2-97ce-46cb-831d-0ce532fb51c4.png" width="600" height="400">

<br><br>
    
6. **CORS 규약이란 무엇이며, 개발단계-배포단계에서는 어떻게 대응해야 할 것인가?**
    
    Cross-origin resources sharing : CORS
    * Cross Site Http Request를 가능하게 하는 표준 규약
    * 다른 도메인의 리소스를 사용 할 경우 CORS를 사용
    * 접근 가능한 도메인과 접근하려고하는 Api, http method를 지정해둬야 정상적인 통신이 가능하다.
    
    처음으로 이러한 문제점을 확인한 시점은 서버에서 CORS관련 작업을 해두지 않아 배포한 서버가 프론트엔드 요청을 블락하면서 알게 되었고, 문제 해결을 시도하게 되었다.   
    원칙적으로는 보안상 접근하고자 하는 도메인을 설정해야 하지만, 개발이 완료되지 않은 상황에서는 그렇게 처리하기가 어려웠다.   
    결과적으로는 개발단계에서 접근을 위한 처리는 다음과 같이 진행했다.
    
    ```java
    @Configuration
    public class WebMvcConfigurerimpl implements WebMvcConfigurer {
    
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000", "http://localhost:8080")
    
                    .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")
                    .allowCredentials(true);
    
        }
    
    }
    ```

<br><br>

7. **구현한 jwt token 방식의 로그인을 프론트서버없이 어떻게 테스트를 할 것인가?**
   
    1. jwt token 방식은 서버에서 Spring-Security에 접근을 허용해두지 않은 모든 요청을 인증 필터가 가로채서 인증을 진행한다.   
       인증을 진행하는 방식은 다름이 아니라 미리 약속한 규약에 맞게 jwt 토큰값을 http 요청에 전달하고, 요청에 포함된 토큰을 서버에서 검증하는 방식이다.   
       우리 프로젝트의 경우 Request Header에 `Authorization:<jwt>` 와 같은 방식으로 토큰을 전달하기로 했고,   
       로그인이 필요한 요청마다 Request Header 의 Authorization에 토큰을 전달하며 테스트를 했어야 하는데..   
       **로그인을 담당한 사람이 커뮤니케이션 능력이 부족해서** 팀원들이 데모용 로그인 페이지를 같이 빌드하고 배포해서   
       배포한 서버에서 테스트 하는 방식으로 개발을 진행했다.

<br><br>

8. **개발속도 차이에 따른 스코프의 확대 축소 변경사항에 대비하여 미리 작업해 둘 수 있는 것은 무엇인가?**
    
    초기 도메인 모델
    
    <image src="https://user-images.githubusercontent.com/23234577/137581581-2316e2c2-49f1-4753-aeed-9213434def92.png" width="600" height="500">

     변경된 도메인 모델   
   
    <image src="https://user-images.githubusercontent.com/23234577/137581572-eb7d7898-9d26-412c-ab03-d8d1c39f1814.png" width="400" height="300">

   
   
    일단 필수적인 서비스인 `conference` api 와 `date` api는 모두 작성해두고 프로젝트의 스코프가 축소한만큼 도메인 모델을 다시 설계하게 되었으며   
    변경된 api만 다시 작성하여 스코프가 변경되어도 유연하게 대처할 수 있게 되었다.
