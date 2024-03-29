FROM openjdk:17 as builder

# JDK 1.8 버전을 베이스로 설정한 후 builder로 alias 처리합니다.
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
COPY Wallet_YummyThings Wallet_YummyThings

# Spring Boot 프로젝트 내의 gradle 설정 파일과 소스코드를 이미지로 가져옵니다.

RUN chmod +x ./gradlew
RUN ./gradlew bootjar

FROM openjdk:17
COPY --from=builder build/libs/*.jar /getto.jar
COPY --from=builder Wallet_YummyThings /Wallet_YummyThings

EXPOSE 9099
ENV ENC_KEY=1018
#ENTRYPOINT ["nohup","java", "-Djasypt.encryptor.password=${KEY}", "-jar", "/getto.jar", ">", "out.log", "2>&1","&"]
ENTRYPOINT exec java -Djasypt.encryptor.password=${ENC_KEY} -jar /getto.jar

# gradlew 에 실행권한을 부여하고 프로젝트를 jar 형식의 파일로 빌드합니다.
#
#COPY build/libs/*.jar getto.jar
#COPY --from=builder build/libs/*.jar getto.jar
#
#RUN ["ls"]
#COPY --from=builder build/libs/*.jar getto.jar

#FROM openjdk:17
# 위에서 빌드한 jar 파일을 실행해 주기 위해 다시 JDK 1.8 버전을 베이스로 설정합니다.

#COPY --from=builder build/libs/*.jar getto.jar
#VOLUME /tmp
#EXPOSE 8080
# builder를 통해 생성된 jar 파일을 이미지로 가져옵니다.
# 8080 포트를 공개한다고 명시합니다.


# 가져온 jar 파일을 실행시킵니다.