# team3-server

<br/>

<div align='center'>

![Logo](https://github.com/wafflestudio21-5/team3-server/assets/79765552/39aaab03-488a-43fc-8a55-c494a432a2df)

> **TEAM 3** 토이프로젝트 <br/> **에브리와플**

---

<img src='https://img.shields.io/badge/Version-1.0.0-blue?style=for-the-badge&logo'>

<img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=android&logoColor=white"/> <a href="https://github.com/wafflestudio21-5/team3-android/graphs/contributors"><img alt="GitHub contributors" src="https://img.shields.io/github/contributors/wafflestudio21-5/team3-android?color=success"></a> <a href="https://github.com/wafflestudio21-5/team3-android/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/wafflestudio21-5/team3-android"></a> <a href="https://github.com/wafflestudio21-5/team3-android/network/members"><img alt="GitHub forks" src="https://img.shields.io/github/forks/wafflestudio21-5/team3-android"></a> <a href="https://github.com/wafflestudio21-5/team3-android/search?l=JavaScript&type=code"><img alt="GitHub language count" src="https://img.shields.io/github/languages/count/wafflestudio21-5/team3-android"></a>

<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/> <a href="https://github.com/wafflestudio21-5/team3-server/graphs/contributors"><img alt="GitHub contributors" src="https://img.shields.io/github/contributors/wafflestudio21-5/team3-server?color=success"></a> <a href="https://github.com/wafflestudio21-5/team3-server/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/wafflestudio21-5/team3-server"></a> <a href="https://github.com/wafflestudio21-5/team3-server/network/members"><img alt="GitHub forks" src="https://img.shields.io/github/forks/wafflestudio21-5/team3-server"></a> <a href="https://github.com/wafflestudio21-5/team3-server/search?l=JavaScript&type=code"><img alt="GitHub language count" src="https://img.shields.io/github/languages/count/wafflestudio21-5/team3-server"></a>

## </div>

---

## <br/> :book: 목차

  <ol>
    <li><a href="#introduction-project"> 프로젝트 소개 (Intro)</a></li>
    <li><a href="#schedule"> 개발 일정</a></li>
    <li><a href="#introduction-function"> 기능설명 </a></li>
    <li><a href="#how-to-install"> 설치 안내 </a></li>
    <li><a href="#techniques"> 기술 스택</a></li>
    <li><a href="#team"> 팀 정보</a></li>
  </ol>

<br/>

---

<h2 id='introduction-project'> :books: 프로젝트 소개</h2>

<h4> :bulb: 선정 배경 </h4>

> 대학생들에게 가장 친숙한 앱 서비스<br/>
> 사용자, 게시판, 댓글 중심의 서비스이므로 클론 코딩하기에 적합하다 판단 <br/>
> 현재의 에브리타임 앱 서비스는 확장할 만한 기능들이 많은 것 같아서 추가 기능 구현 용이 <br/>

위와 같은 이유로 인해, TEAM3는 에브리타임을 클론 코딩하기로 결정하였습니다! <br/><br/>

<h4> :+1: 기본 기능 </h4>

**`로그인/소셜 로그인`** : 사용자 구분을 위한 기본적인 로그인 기능<br/><br/>
**`게시판/댓글 작성 기능`** : 에브리타임의 주요 기능인 글 및 댓글/대댓글 작성 기능<br/><br/>
**`쪽지기능`** : 다른 사용자와의 소통을 위한 쪽지 기능<br/><br/>

> 해당 기능에 대한 시연영상은 <a href="#introduction-function"> 기능설명 </a>에서 확인할 수 있습니다.

<br/>

<h4> :+1: 추가 기능 </h4>

**`1:1 랜덤 쪽지 기능`** : 모르는 사람과의 대화 세션 생성 기능<br/><br/>
**`투표 게시판 기능`** : 토론이 활발해지는 주제에 대해 투표를 추가할 수 있는 기능<br/><br/>

> 해당 기능에 대한 시연영상은 <a href="#introduction-function"> 기능설명 </a>에서 확인할 수 있습니다.

<br/>

---

<h2 id='schedule'> :calendar: 개발 일정 </h2>

<table class="tg">
<thead>
  <tr>
    <th class="tg-6qw1" colspan="3">개발 일정</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td class="tg-0lax">스프린트1</td>
    <td class="tg-baqh"><span style="font-weight:400;font-style:normal">~12/30</span></td>
    <td class="tg-0lax">1. 클론 서비스 확정<br><br>2. 프론트 및 백엔드 개발 컨벤션 수립</td>
  </tr>
  <tr>
    <td class="tg-0lax">스프린트2</td>
    <td class="tg-baqh">~1/13</td>
    <td class="tg-0lax">1. AWS 배포 세팅 및 프론트엔드/백엔드 api 테스트<br><br>2. 회원가입/로그인 기능 구현<br><br>3. 유저페이지(마이페이지 구현)</td>
  </tr>
  <tr>
    <td class="tg-0lax">스프린트3</td>
    <td class="tg-baqh">~1/27</td>
    <td class="tg-0lax">1. 게시판 및 댓글 작성 기능 구현<br><br>2. 페이지네이션 구현<br><br>3. 쪽지 기능 구현</td>
  </tr>
  <tr>
    <td class="tg-0lax">스프린트4</td>
    <td class="tg-baqh">~2/3</td>
    <td class="tg-0lax">1. 랜덤 쪽지 기능 구현<br><br>2. 투표 게시판 기능 구현<br><br>3. 디자인 개선 및 디버깅</td>
  </tr>
</tbody>
</table>

---

<h2 id="introduction-function"> :page_with_curl: 기능 설명</h2>

- <h4>회원가입 로그인</h4>

<table width="900">

<tr>
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/cea09190-e1db-497b-ba8a-878d37f02e2d" width=100%"></td>
<td width ="450" align="center">기본 <b>회원가입</b> 기능</td>
</tr>

<tr>
<td width ="450" align="center">기본 <b>로그인</b> 기능
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/088bb7e7-c6d0-4f0e-a3ff-91688c3ae3ea" width=100%"></td>
</tr>

<tr>
<td width ="450" align="center"></td>
<td width ="450" align="center"><b>카카오 로그인</b> 기능</td>
</tr>

</table>
<br/>

- <h4>게시판 기능</h4>

<table width="900">

<tr>
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/fca186fc-dde2-423f-842f-ab9c7fa31481" width=100%"></td>
<td width ="450" align="center">기본 게시판 기능</td>
</tr>

<tr>
<td width ="450" align="center">댓글 및 대댓글 작성</td>
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/e825dc77-3413-40ee-85e1-cfb7f658a4aa" width=100%"></td>
</tr>

<tr>
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/4811282a-1b54-4b3d-b302-e695018440ba" width=100%"></td>
<td width ="450" align="center">내가 쓴 글<br>댓글 단 글<br>스크랩 한 글</td>
</tr>

</table>
<br/>

- <h4>투표 게시판 기능</h4>

<table width="900">

<tr>
<td width ="450" align="center">투표 글에 대해 투표 가능</td>
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/9e0d93cf-0738-4a4e-8bb6-c26bb11e413e"></td>
</tr>

</table>
<br/>

- <h4>쪽지 기능</h4>

<table width="900">

<tr>
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/38ba51bd-e3ea-4c73-9e6e-cafc934f9bea"></td>
<td width ="450" align="center">사용자에게 쪽지 보내기 기능</td>
</tr>

<tr>
<td width ="450" align="center">쪽지 리스트 및 쪽지 보내기</td>
<td width ="450" align="center"><video src="https://github.com/wafflestudio21-5/team3-server/assets/79765552/811dcc2f-b072-433d-839a-57a0d69679ca"></video></td>
</tr>

<tr>
<td width ="450" align="center"><video src=https://github.com/wafflestudio21-5/team3-server/assets/79765552/2e222e18-ca46-44de-9a24-2d9a06f048fb></video></td>
<td width ="450" align="center">랜덤 쪽지 보내기</td>
</tr>

</table>
<br/>

그밖에도 다양한 기능이 앱에 존재합니다! <a href="#how-to-install"> 설치 안내 </a>에서 apk 파일을 받아 직접 사용해보세요!

---

<h2 id="how-to-install"> :wrench: 설치 안내 (Installation Process)</h2>

<h4>서버 설치</h4>

```bash
$ git clone https://github.com/wafflestudio21-5/team3-server.git
$ cd team3-server
$ ./gradlew build
$ ./gradlew bootRun
```

<br/>
<h4>안드로이드 앱 다운</h4>

안드로이드 앱은 <a href="https://drive.google.com/file/d/1Okdv-F5DhdU2b7igFGb0OrrWoxJYqXFL/view?usp=sharing">다운로드 링크</a>에서 다운받으실 수 있습니다.

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

<h2 id="team">:runner: :runner: 팀 정보 (Team Information)</h2>

> **TEAM3** <br> TEAM3는 2명의 안드로이드 개발자, 4명의 스프링 개발자, 그리고 1명의 디자이너로 구성되어 있습니다.

<br/>

<table width="900">

<thead>

<tr>

<th width="100" align="center">Name</th>

<th width="250" align="center">Role</th>

<th width="150" align="center">Github</th>

<th width="300" align="center">E-mail</th>

</tr>

</thead>

<tbody>

<tr>
<td width="100" height="55" align="center">이현우<br>(팀장)</td>
<td width="250">BackeEnd Developer</td>
<td width="150" align="center">
<a href="https://github.com/lhw414">
<img src="https://img.shields.io/badge/lhw414-655ced?style=social&logo=github"/></a>
<td width="300" align="center"><a href="mailto:dlgusdn0414@snu.ac.kr"><img src="https://img.shields.io/static/v1?label=&message=dlgusdn0414@snu.ac.kr&color=lightgray&style=flat-square&logo=gmail"></a></td>
</tr>

<tr>
<td width="100" height="55" align="center">김건희</td>
<td width="250">BackEnd Developer</td>
<td width="150" align="center">
<a href="https://github.com/gunhee1113">
<img src="https://img.shields.io/badge/gunhee1113-655ced?style=social&logo=github"/></a>
<td width="300" align="center"><a href="mailto:gunhee2001@snu.ac.kr"><img src="https://img.shields.io/static/v1?label=&message=gunhee2001@snu.ac.kr&color=lightgray&style=flat-square&logo=gmail"></a></td>
</tr>

<tr>
<td width="100" height="55" align="center">한상우</td>
<td width="250">BackEnd Developer</td>
<td width="150" align="center">
<a href="https://github.com/navgod">
<img src="https://img.shields.io/badge/navgod-655ced?style=social&logo=github"/></a>
<td width="300" align="center"><a href="mailto:buzz2604@snu.ac.kr"><img src="https://img.shields.io/static/v1?label=&message=buzz2604@snu.ac.kr&color=lightgray&style=flat-square&logo=gmail"></a></td>
</tr>

<tr>
<td width="100" height="55" align="center">나예경</td>
<td width="250">BackEnd Developer</td>
<td width="150" align="center">
<a href="https://github.com/yknxh">
<img src="https://img.shields.io/badge/yknxh-655ced?style=social&logo=github"/></a>
<td width="300" align="center"><a href="mailto:nyk7535@snu.ac.kr"><img src="https://img.shields.io/static/v1?label=&message=nyk7535@snu.ac.kr&color=lightgray&style=flat-square&logo=gmail"></a></td>
</tr>

<tr>
<td width="100" height="55" align="center">이현도</td>
<td width="250">App Developer</td>
<td width="150" align="center">
<a href="https://github.com/plgafhd">
<img src="https://img.shields.io/badge/plgafhd-655ced?style=social&logo=github"/></a>
<td width="300" align="center"><a href="mailto:plgafhd@snu.ac.kr"><img src="https://img.shields.io/static/v1?label=&message=plgafhd@snu.ac.kr&color=lightgray&style=flat-square&logo=gmail"></a></td>
</tr>

<tr>
<td width="100" height="55" align="center">전준아</td>
<td width="250">App Developer</td>
<td width="150" align="center">
<a href="https://github.com/junahjeon2002">
<img src="https://img.shields.io/badge/junahjeon2002-655ced?style=social&logo=github"/></a>
<td width="300" align="center"><a href="mailto:junahjeon2002@snu.ac.kr"><img src="https://img.shields.io/static/v1?label=&message=junahjeon2002@snu.ac.kr&color=lightgray&style=flat-square&logo=gmail"></a></td>
</tr>

<tr>
<td width="100" height="55" align="center">정유정</td>
<td width="250">Designer</td>
<td width="150" align="center">
<a href="https://github.com/yo0jj">
<img src="https://img.shields.io/badge/yo0jj-655ced?style=social&logo=github"/></a>
<td width="300" align="center"><a href="mailto:yasmin607@snu.ac.kr"><img src="https://img.shields.io/static/v1?label=&message=yasmin607@snu.ac.kr&color=lightgray&style=flat-square&logo=gmail"></a></td>
</tr>

</table>

<br/>

---
