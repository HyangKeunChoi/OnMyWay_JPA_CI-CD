# 내맘대로 JPA & CI/CD

### 아키텍쳐

![image](https://user-images.githubusercontent.com/49984996/187562494-7f5ed770-edf9-4f89-b743-faad28575000.png)


### Skills
+ Java11, SpringBoot, SpringDataJpa
+ Junit5, lombok
+ Gradle, intelliJ
+ Github Action, AWS EC2, S3, CodeDeploy

### 비용 계산
+ S3 : 스토리지 GB당 0.025달러
+ EC2 : t2.small (vCPU : 1, 메모리 2GiB) ⇒ 약 월 20달러,데이터 전송 비용 별도
+ CodeDeploy : 온프레미스 서버에 코드를 배포할 때만 비용이 부과됨. 요금은 온프레미스 서버 업데이트당 0.02 달러

### @RuntWith(Springnner.class)
+ JUnit 프레임워크의 테스트 실행방법을 확장할 때 사용하는 어노테이션
+ ApplicationContext를 만들고 관리하는 작업을 @RunWith(SpringRunner.class)에 설정된 class로 이용하겠다
+ Junit5에서는 @SpringBootTest에 포함되어있음

### N+1 해결방안

1. fetch join
2. EntityGraph
3. BatchSize

### fetch join vs EntityGraph
1. fetch join은 InnerJoin, EntityGraph는 OuterJoin
2. 공통점으로 카테시안 곱(Cartesian Product)이 발생하야 중복이 발생
   - distinct, set을 이용

### fetch join의 단점
1. 페이징 쿼리에서 사용할 수 없음
2. 별칭을 두어 where조건에서 사용 할 수 없음 (무조건 전부 조회)

### 


### Reference
https://jojoldu.tistory.com/165
https://jojoldu.tistory.com/457
https://incheol-jung.gitbook.io/docs/q-and-a/spring/n+1
