# team3-server

<br/>

<div align='center'>

![Logo](https://github.com/wafflestudio21-5/team3-server/assets/79765552/07bbef19-14b1-47d5-9045-084764ac161f)

---

<img src='https://img.shields.io/badge/Version-1.0.0-blue?style=for-the-badge&logo'>

<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white"/> <a href="https://github.com/wafflestudio21-5/team3-android/graphs/contributors"><img alt="GitHub contributors" src="https://img.shields.io/github/contributors/wafflestudio21-5/team3-android?color=success"></a> <a href="https://github.com/wafflestudio21-5/team3-android/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/wafflestudio21-5/team3-android"></a> <a href="https://github.com/wafflestudio21-5/team3-android/network/members"><img alt="GitHub forks" src="https://img.shields.io/github/forks/wafflestudio21-5/team3-android"></a> <a href="https://github.com/wafflestudio21-5/team3-android/search?l=JavaScript&type=code"><img alt="GitHub language count" src="https://img.shields.io/github/languages/count/wafflestudio21-5/team3-android"></a>

<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/> <a href="https://github.com/wafflestudio21-5/team3-server/graphs/contributors"><img alt="GitHub contributors" src="https://img.shields.io/github/contributors/wafflestudio21-5/team3-server?color=success"></a> <a href="https://github.com/wafflestudio21-5/team3-server/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/wafflestudio21-5/team3-server"></a> <a href="https://github.com/wafflestudio21-5/team3-server/network/members"><img alt="GitHub forks" src="https://img.shields.io/github/forks/wafflestudio21-5/team3-server"></a> <a href="https://github.com/wafflestudio21-5/team3-server/search?l=JavaScript&type=code"><img alt="GitHub language count" src="https://img.shields.io/github/languages/count/wafflestudio21-5/team3-server"></a>

## </div>

---

## <br/> :book: 목차

  <ol>
    <li><a href="#introduction-project"> 프로젝트 소개 (Intro)</a></li>
    <li><a href="#introduction-function"> 기능설명 </a></li>
    <li><a href="#how-to-install"> 설치 안내 </a></li>
    <li><a href="#how-to-use"> 프로젝트 사용법 </a></li>
    <li><a href="#prerequisites"> 컴퓨터 구성 / 필수 조건 안내</a></li>
    <li><a href="#techniques"> 기술 스택</a></li>
    <li><a href="#team"> 팀 정보</a></li>
  </ol>

<br/>

---

<h2 id="techniques">:hammer: 기술 스택 (Technique Used)</h2>

<img src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/02fb4a9b-43ac-4dac-97cf-5c3fb4ece485">

### Server(back-end)

**1. DB, Framework**  
DB는 RDBMS의 [MySQL](https://www.mysql.com/)을 사용하였고, 서버 프레임워크는 스프링부트 [SpringBoot](https://spring.io/projects/spring-boot)를 사용였습니다.
<br/>

**2. ORM**  
스프링에서 기본적으로 지원해주는 [JPA](https://docs.spring.io/spring-data/jpa/reference/index.html)를 사용했습니다.
<br/>

**3. Swagger for API Docs**  
백엔드는 프론트엔드 개발자들과 협업을 하기 위해 각각의 API에 대한 스펙을 적절한 방법으로 전달해야 합니다.  
그 중 API Route별로 Response, Request Body의 형태를 상세히 기술한 API Document를 제작하는 경우가 흔한데, 이를 자동으로 생성해주는 `Swagger`을 사용하였습니다.
<br/>

### Front-end

**1. Framework**
안드로이드 앱 빌드를 위해 [Android Studio](https://developer.android.com/studio?hl=ko)를 사용하였습니다. 언어로는 [Kotlin](https://kotlinlang.org/)을 사용하였습니다.

<br/>

### Infra

**1. 서버 인스턴스**
서버 구동을 위해서, AWS에서 제공해주는 [EC2](https://aws.amazon.com/ko/ec2/)를 사용하였습니다.

**2. 버전 관리**
도커 이미지로 서버를 빌드하게 되는데, 버전 관리를 더 용이하게 하기 위해 도커 이미지 관리 시스템인 [ECR](https://aws.amazon.com/ko/ecr/)을 사용하였습니다. 해당 시스템을 통해 버전별 도커 이미지를 확인하고 PULL할 수 있습니다.

## <br/>

---
