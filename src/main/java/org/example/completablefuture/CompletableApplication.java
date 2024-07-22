package org.example.completablefuture;

import org.example.completablefuture.service.AsyncService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableAsync // @Async 가 붙은 클래스들을 비동기화 해주는 작업
public class CompletableApplication{

    public static void main(String[] args) {
        SpringApplication.run(CompletableApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(AsyncService asyncService) {
        return args -> {
            System.out.println("async 메서드 호츌");

            CompletableFuture<String> result1 = asyncService.asyncMethod1();
            CompletableFuture<String> result2 = asyncService.asyncMethod2();

            // 비동기 메서드를 호출 한 후에 다른 작업 수행
            System.out.println("다른 작업 수행!!");

            // 비동기 메서드 결과를 조합
            CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(result1, result2)
                    .thenRun(() -> {
                        System.out.println("asyncMethod1 결과 :: " + result1.join()); // 내부적으로 Exception 처리함 get() 과 다름
                        System.out.println("asyncMethod2 결과 :: " + result2.join()); // join() -->
                    });
            // 여기에서도 모든 작업이 완료 될때까지 기다림.
            combinedFuture.join();
        };
    }
}
