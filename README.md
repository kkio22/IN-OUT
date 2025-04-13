# ▶ SNS 프로젝트 - allin◀

* 프로젝트 이름이 allin인 이유 :  우리 팀원들의 MBTI가 모두 I 이기 때문에 All In 으로 프로젝트명을 지었습니다.

JDK version 17

## ✅ 목적

**기본적인 SNS의 기능을 갖춘 소셜미디어 앱 만들기**

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

-----

## ✅ API 명세서

* _**Schedule**_

|     기능     | Method | Endpoint(URL) | Path Variable | Request Parameter | Request Body                                                                           | Response                                                                                                                                                 |     상태코드     |
|:----------:|:------:|:-------------:|:-------------:|:-----------------:|:---------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------------|:------------:|
|   스케줄 생성   |  POST  |  /schedules   |       X       |         X         | { "user_name": "유저명",<br/>"task_title": "할일 제목",<br/>"task_content": "할일 내용" }         | { "schedule_id": 1,<br/>"user_name": "유저명",<br/>"task_title": "할일 제목",<br/>"task_content": "할일 내용",<br/>"created_at": 작성된 시간,<br/>"updated_at": 수정된 시간 } |  200: 정상등록   |
| 스케줄 단건 조회  |  GET   |     /{id}     |   id (Long)   |         X         | X                                                                                      | { "schedule_id": 1,<br/>"user_name": "유저명",<br/>"task_title": 제목1,<br/>"task_content": 할 일 내용1 }                                                         |  200: 정상조회   |
| 스케줄 수정(전체) |  PUT   |     /{id}     |   id (Long)   |         X         | { "task_title": "수정된 제목",<br/>"task_content": "수정된 내용" }                               | { "schedule_id": 1,<br/>"user_name": "유저명",<br/>"task_title": "수정된 제목",<br/>"task_content": "수정된 내용",<br/>"updated_at": 수정된 시간 }                         |  200: 정상수정   |
|   스케줄 삭제   | DELETE |     /{id}     |   id (Long)   |         X         | X                                                                                      | { "msg": "일정 삭제 완료" }                                                                                                                                    |  200: 정상삭제   |

-----
