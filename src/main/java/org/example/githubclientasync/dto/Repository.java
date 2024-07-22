package org.example.githubclientasync.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * json 의 값을 가져올 때 여러 값들이 전부 가져오는 상황이 발생
 * ignoreUnknown = true --> 내가 정의하지 않은 것들은 무시하겠다.!
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class Repository {

    private String name;
}
