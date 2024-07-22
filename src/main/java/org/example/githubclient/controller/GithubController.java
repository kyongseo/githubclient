package org.example.githubclient.controller;

import org.example.githubclient.service.GithubAPiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1. API URL 구성: getUserRepos 메서드가 호출되면, 사용자의 리포지토리 목록을 가져오기 위한 URL을 구성합니다.
 * 2. HTTP 헤더 설정: Authorization 헤더에 GitHub API 토큰을 설정합니다.
 * 3. API 호출: restTemplate.exchange를 사용하여 GitHub API에 GET 요청을 보냅니다.
 * 4. 응답 처리: 응답의 본문을 반환합니다.
 */
@RestController
public class GithubController {

    @Autowired
    private GithubAPiService githubAPiService;

    @GetMapping("/github/{username}/repos")
    public String repos(@PathVariable("username") String username) {
        return githubAPiService.getUserRepos(username);
    }
}
