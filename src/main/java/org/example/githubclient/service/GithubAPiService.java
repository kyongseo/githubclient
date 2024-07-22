package org.example.githubclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;


/**
 * 프로젝트에서 API 프로젝트를 해도 되는...
 *  Spring Boot 애플리케이션에서 GitHub API를 호출하여 특정 사용자의 리포지토리 목록을 가져오는 서비스 클래스
 *  GitHub API를 호출하여 특정 사용자의 리포지토리 목록을 가져오는 역할
 * 1. API URL 구성: getUserRepos 메서드가 호출되면, 사용자의 리포지토리 목록을 가져오기 위한 URL을 구성합니다.
 * 2. HTTP 헤더 설정: Authorization 헤더에 GitHub API 토큰을 설정합니다.
 * 3. API 호출: restTemplate.exchange를 사용하여 GitHub API에 GET 요청을 보냅니다.
 * 4. 응답 처리: 응답의 본문을 반환합니다.
 */
@Service
public class GithubAPiService {

    @Value("${github.api.url}")
    private String githubApiUrl; // GitHub API의 기본 URL을 저장

    @Value("${github.api.token}")
    private String githubApiToken; // GitHub API 접근을 위한 토큰을 저장

    // 스프링에서 Http 요청/응답에 대해서 처리하기 위한 탬플릿
    private final RestTemplate restTemplate;

    public GithubAPiService() {
        this.restTemplate = new RestTemplate();
    }


    // 주어진 username에 해당하는 GitHub 사용자의 리포지토리 목록을 가져옵니다.
    public String getUserRepos(String username) {
        String url = this.githubApiUrl + "/users/" + username + "/repos"; // url: GitHub API의 사용자 리포지토리 엔드포인트 URL을 생성
        HttpHeaders httpHeaders = new HttpHeaders(); // HttpHeaders: HTTP 헤더를 설정합니다. 여기서 Authorization 헤더를 설정하여 GitHub API 토큰을 포함
        httpHeaders.set("Authorization", "Bearer " + githubApiToken);

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders); // 요청 헤더를 포함하는 엔터티를 생성
        // restTemplate.exchange 메서드를 사용하여 GET 요청을 보내고, 응답을 ResponseEntity 객체로 받습니다.
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    /**
     * 비동기 방식으로 진행
     */
//    public CompletableFuture<ResponseEntity<String>> getUserRepos(String username) {
//        String url = this.githubApiUrl + "/users/" + username + "/repos"; // url: GitHub API의 사용자 리포지토리 엔드포인트 URL을 생성
//        HttpHeaders httpHeaders = new HttpHeaders(); // HttpHeaders: HTTP 헤더를 설정합니다. 여기서 Authorization 헤더를 설정하여 GitHub API 토큰을 포함
//        httpHeaders.set("Authorization", "Bearer " + githubApiToken);
//
//        HttpEntity<String> entity = new HttpEntity<>(httpHeaders); // 요청 헤더를 포함하는 엔터티를 생성
//        // restTemplate.exchange 메서드를 사용하여 GET 요청을 보내고, 응답을 ResponseEntity 객체로 받습니다.
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//
//        return CompletableFuture.completedFuture(response);
//    }
}
