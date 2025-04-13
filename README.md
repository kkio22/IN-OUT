# ▶ SNS 프로젝트 - allin◀

* 사용 버전 : JDK version 17

* 기술 스택 : Github, Git, IntelliJ, Notion,  ERD Cloud, SpringBoot

* 사용 언어 : Java, JavaScript, MySql

## 프로젝트 소개

* 팀 이름:  ALL- IN → 우리 팀원들의 MBTI가 모두 I 이기 때문에 All In 으로 프로젝트명을 지었습니다.
  
* SNS 이름:  IN & OUT

* 개발 기간 : 2025.04.07 ~ 2025.04.14
  
* 프로젝트 설명 :

    - IN & OUT이라는 새로운 SNS 플랫폼을 런칭하였다.

    - IN & OUT은 Sparta 코딩 클럽을 참여하는 참여자들을 위한 정보 공유, 일상 공유를 위해 개발한 SNS입니다.
  
* 팀장: 김나현
  
* 팀원: 김하정, 송윤태, 김윤범, 정기백

-----

## ✅ 목적

**기본적인 SNS의 기능을 갖춘 내배캠 참여자 전용 소셜미디어 앱 만들기**

* 회원가입

* 로그인

* 게시글 작성

* 댓글 작성

* 게시글 및 댓글에 좋아요 가능

* 친구 맺기 가능

-----

## ✅ 주요기능

✔️ **회원가입 (`POST`)** → 새로운 유저 생성

✔️ **로그인 (`POST`)** → 생성된 유저로 로그인

✔️ **게시글 작성 (`POST`)** → 로그인한 유저로 게시글 작성 가능

✔️ **게시글 개별 조회 (`GET`)** → 게시물 id로 게시물 조회 가능

✔️ **게시글 페이징 조회 (`GET`)** → 게시물이 10개까지만 조회되도록 가능

✔️ **게시글에 좋아요 / 좋아요 취소 가능 (`PUT`)** → 생성된 게시글에 좋아요 가능 / 좋아요 취소도 가능

✔️ **댓글 작성 (`POST`)** → 로그인한 유저로 댓글 작성 가능

✔️ **댓글 수정 (`PUT`)** → 로그인한 유저의 해당 댓글 수정 가능

✔️ **댓글에 좋아요 (`POST`)** → 작성된 댓글에 좋아요 가능

✔️ **댓글에 좋아요 취소 (`POST`)** → 좋아요 한 댓글에 좋아요 취소 가능

✔️ **댓글 삭제 (`DELETE`)** → 작성한 댓글 삭제 가능

✔️ **친구 추가 요청 (`POST`)** → 로그인한 유저가 다른 회원가입된 유저에게 친구 요청 보내기 가능

✔️ **친구 수락 요청 (`POST`)** → 친구 요청 수락 / 거절 가능 (handleRequest)

✔️ **친구 목록 조회 (`GET`)** → 로그인된 해당 유저의 친구 목록 조회 가능

✔️ **친구 삭제 (`DELETE`)** → 추가된 친구 삭제 가능

-----

## ✅ Error Code

|![image](https://github.com/user-attachments/assets/8f4e6925-2254-4f2a-a3a4-68bd3e9da523)
|:---:|

-----

-----

## ✅ API 명세서

### * _**User**_

|![image](https://github.com/user-attachments/assets/bc6a2021-2c27-48eb-be08-d727e4f2af04)
|:---:|

-----

### * _**Login**_

|![image](https://github.com/user-attachments/assets/500d3d83-815d-45a7-8e87-c466dc35365e)
|:---:|

-----

### * _**Post**_

|![image](https://github.com/user-attachments/assets/4c61b96f-fcff-4046-842f-0abe2514435e)
|:---|

-----

### * _**Friend**_

|![image](https://github.com/user-attachments/assets/6ed78e72-b8a9-4cac-8f03-13897dfc711f)
|:---:|

-----

### * _**Comment**_

|![image](https://github.com/user-attachments/assets/3d04385a-800d-492f-a2d7-8fd9074d79b1)
|:---:|

-----

### * _**Like - 게시물 좋아요**_

|![image](https://github.com/user-attachments/assets/a1d7180b-520e-4ceb-bc52-fe4042ace754)
|:---:|

-----

### * _**Like - 댓글 좋아요**_

|![image](https://github.com/user-attachments/assets/e53a52f6-bfa1-4875-ba24-95944f972fdf)
|:---:|

-----
-----

## ✅ ERD

|![image](https://github.com/user-attachments/assets/6cf07c16-64d5-416d-81c2-ed4f8410c4b3)|
|:---:|
