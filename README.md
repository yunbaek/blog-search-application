# 블로그 검색 API

## 개요

블로그 글 목록을 검색하고, 검색 순위를 확인할 수 있는 API입니다.

## 개발 환경
- Java 11
- Spring Boot 2.7.10
- Gradle
- JPA
- H2 Database
- JUnit 5

### 사용 라이브러리
- [Lombok](https://projectlombok.org/)
  - 불필요한 코드를 줄이고, 가독성을 높이기 위해 사용하였습니다.
  - version: 1.18.26
- [MapStruct](https://mapstruct.org/)
  - 객체간의 매핑을 위해 사용하였습니다.
  - version: 1.4.2.Final
- [Caffeine](https://https://github.com/ben-manes/caffeine)
  - 로컬 캐싱을 위해 사용하였습니다.
  - version: 3.0.0
- [Guava](https://github.com/google/guava)
  - 문자 대소문자 변경을 위해 사용하였습니다.
  - version: 31.1-jre

## 빌드 및 실행 방법
```shell
$ git clone https://github.com/yunbaek/blog-search-application.git
$ cd blog-search-application
$ ./gradlew clean build
$ java -jar build/libs/blog-search-application-0.0.1-SNAPSHOT.jar
``` 
- 혹은 첨부된 JAR 파일을 실행합니다.
- [[다운로드]](https://drive.google.com/file/d/1fJnXJDkN-CDc1yqjD_HkOivCaRPfxgB5/view?usp=sharing)
```shell
$ java -jar blog-search-application-0.0.1-SNAPSHOT.jar
```



## 기능

### 1. 검색

- 검색어를 입력하면, 해당 검색어가 포함된 블로그 글 목록을 반환합니다.
- 검색어, 검색 결과 수, 페이지, 정렬 순서를 선택할 수 있습니다.
- 카카오 블로그 검색 API를 사용합니다.
- 카카오 블로그 검색에 오류가 발생하면, 네이버 블로그 검색 API를 사용합니다.

#### API

- URL: http://localhost:8080/blogs
- Method: GET
- Parameter

| Parameter | Type    | Description                | Required | Default  |
|-----------|---------|----------------------------|----------|----------|
| query     | String  | 검색어                        | Y        | -        |
| size      | Integer | 검색 결과 수 (1 - 50)           | N        | 10       |
| page      | Integer | 페이지 (1 - 50)               | N        | 1        |
| sort      | String  | 정렬 순서 (accuracy / recency) | N        | accuracy |

- Response

```http request
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

{
  "blogs": [
    {
      "blogName": "테스트 블로그",
      "contents": "테스트 블로그 내용",
      "url": "https://test.blog.me/123456789",
      "thumbnailUrl": "https://test.blog.me/123456789",
      "title": "테스트 블로그 제목",
      "createTime": "2023-02-26T14:23:16"
    },
    {
      ...
    }
  "currentPage": 1,
  "size": 10,
  "total": 100
}
   
```

#### 구현 내용
- 로컬 캐싱을 활용하여 검색 결과를 30초간 캐싱합니다.
- Chain of Responsibility 패턴을 사용하여, 검색 API를 추가할 때, 기존 코드를 수정하지 않고, 새로운 검색 API를 추가할 수 있습니다.
- Event listener를 사용하여, 조회수 증가에 대한 관심사를 분리하였습니다.
- WebClient를 사용하여, 외부 API를 호출합니다.
- ReentrantLock을 사용하여, 동시에 같은 검색어로 검색 요청이 들어왔을 때, 조회수 데이터에 대한 동시성을 고려하였습니다.

### 2. 검색 순위

- 검색 순위를 확인할 수 있습니다.
- 검색 순위는, 검색 횟수가 많은 순서대로 최대 10개까지 정렬됩니다.
- 검색 순위는, 10초마다 갱신됩니다.

#### API
- URL: http://localhost:8080/keyword-rank
- Method: GET
- Response

```http request
HTTP/1.1 200 OK
Content-Type: application/json;charset=UTF-8

[
  {
    "query": "테스트 검색어",
    "count": 100
  },
  {
    ...
  }
]
```

#### 구현 내용
- 로컬 캐싱을 활용하여 검색 순위를 10초간 캐싱합니다.


### 공통
#### 구현 내용
- 공통 에러 처리를 위해, ExceptionHandler를 사용합니다.
- 인수 테스트 작성
- JPA audit 사용
