# findPopularComment
2018 상반기 Naver Campus Hackday 인기 댓글 찾기 구현 (핵데이 후)
<hr/>

## 프로젝트 설명
* 사용자는 공감을 하거나 이미 한 공감에 대해서 취소할 수 있다. 
* 많은 사용자가 공감을 한 순서로 댓글을 정렬할 수 있어야 하며 공감 수는 사용자에게 노출되어야 한다.
* 초당 3000건 이상의 공감을 처리할 수 있어야 한다.
* 모든 요청에 대한 처리는 1초 내로 응답되어야 한다.

  * 참고사항
      * 만들 인터페이스는 목록과 공감 API 두개 뿐이므로 그 외 상황은 고려하지 않는다.
      * 테이블의 복잡도를 줄이기 위해서 사용자 정보는 댓글 작성 시의 스냅샷으로 나타낸다고 가정하고
        댓글 테이블에 저장한다.
      * 역시 같은 취지에서 post테이블 또한 굳이 그리지 않으며 댓글 목록만 그린다.
<hr/>

## 개발도구
* Spring Boot - 어플리케이션 프레임워크
* Maven - 라이브러리 관리, 빌드
* Tomcat - WAS
* STS - IDE
* MyBatis - ORM
* MySQL, Redis - 저장소
<hr/>

## Maven dependency
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
			<exclusions>
				<exclusion>
					<groupId>io.lettuce</groupId>
					<artifactId>lettuce-core</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>
  <hr/>
  
## 저장소
* Redis
1. 각 댓글에 대한 공감/공감취소 반응을 저장.(어떤 댓글에 공감/공감 취소가 되었는지 저장)
2. 사용자의 공감Set을 저장.(댓글 목록 조회 시 현재 사용자가 이미 공감한 댓글을 파악할 수 있습니다.)
3. 댓글리스트를 캐싱하는 저장소로 활용합니다.(빠른 조회의 목적)

    * 사용자의 공감 데이터 저장 자료구조(set)
    key : “empathyUsers: ” + userId
    value : "commentId"
    ※ 사용자가 공감한 댓글들을 저장합니다.

    * 현재 공감/공감취소 된 댓글 데이터 저장 자료구조(List)
    key : "empathyComments"
    value : “commentId"
    ※ 각 댓글에 공감/비공감 요청이 오면 해당 댓글 id를 List에 저장.
    ※ Redis의 List자료구조는 자바의 Queue와 닮음
    
* MySQL

    * Redis의 댓글의 공감/공감취소 요청이 저장된 자료구조에서 배치를 돌려 주기적으로 MySQL에 저장합니다. 
    (공감/공감취소 요청을 메모리인 Redis에 쓰기만 하면 되서 사용자에게 응답을 빨리 줄 수 있음)
    
    ![image](https://user-images.githubusercontent.com/33171233/45252491-2577b780-b392-11e8-9860-20efb3d292e1.png)
    
    ※ empathy = 공감 수치, not_empathy = 비공감 수치
<hr/>

## 시스템 동작 방식
1. 댓글 목록 API
  * 사용자는 해당 게시글을 클릭하여 해당 게시글의 대한 내용과 댓글목록을 조회합니다.
  * Redis에 해당 게시글의 대한 내용과 댓글 목록이 캐싱되어 있는 경우 
    Redis에서 해당 내용과 현재 사용자의 공감 목록을 조립하여 사용자의 요청에 응답하게 됩니다.
  * Redis에 해당 정보의 캐싱이 만료된 경우 댓글 목록만을 join없이 MySQL에서 조회하게 됩니다. 
    (해당 사용자의 댓글에 대한 공감 정보는 Redis에 저장되어 있습니다.)
    
2. 공감/공감취소 API
  * 사용자가 댓글에 공감/공감취소를 클릭합니다.
  * Redis에서 사용자가 이미 공감한 댓글인지 확인 후 아니면 Redis의 사용자에 대한 공감 Set에 
    해당 댓글을 추가하고 댓글에 대한 공감 수치를 반영하기 위해 List 구조에 해당 댓글 id를 저장합니다. 
  * 각 댓글들의 공감수치를 UPDATE하기 위하여 스케쥴러를 이용하여 위의 Redis List구조로 저장된 
    댓글들 id를 주기적으로 종합하여 댓글들의 공감 수치를 MySQL에 반영합니다.
<hr/>

## 시스템 구성
![image](https://user-images.githubusercontent.com/33171233/45252641-f57de380-b394-11e8-938e-6b409d613894.png)


