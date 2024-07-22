package org.example.githubclientasync.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.githubclientasync.dto.Repository;
import org.example.githubclientasync.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GithubApiService {

    @Value("${github.api.url}")
    private String githubApiUrl;

    @Value("${github.api.token}")
    private String githubApiToken;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GithubApiService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    // 해당 사용자의 깃허브 리포지토리
    @Async("taskExecutor")
    public CompletableFuture<List<Repository>> getUserRepos(String username) {
        System.out.println("getUserRepos() 시작 --> 사용자의 깃허브 레포지토리");
        return CompletableFuture.supplyAsync(() -> {
            String url = githubApiUrl + "/users/" + username + "/repos";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + githubApiToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            try {
                return objectMapper.readValue(response.getBody(), new TypeReference<List<Repository>>() {});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    // 해당 사용자의 깃허브 Follower
    @Async("taskExecutor")
    public CompletableFuture<List<User>> getUserFollowers(String username) {
        System.out.println("getUserFollowers() 시작 --> 사용자의 깃허브 팔로워 리스트");
        return CompletableFuture.supplyAsync(() -> {
            String url = githubApiUrl + "/users/" + username + "/followers";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + githubApiToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            try {
                return objectMapper.readValue(response.getBody(), new TypeReference<List<User>>() {});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    // 해당 사용자의 깃허브 Following
    @Async("taskExecutor")
    public CompletableFuture<List<User>> getUserFollowing(String username) {
        System.out.println("getUserFollowing() 시작 --> 사용자의 깃허브 팔로잉 리스트");
        return CompletableFuture.supplyAsync(() -> {
            String url = githubApiUrl + "/users/" + username + "/following";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + githubApiToken);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            try {
                return objectMapper.readValue(response.getBody(), new TypeReference<List<User>>() {});
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
