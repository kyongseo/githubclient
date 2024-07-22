package org.example.completablefuture.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class AsyncService {
    /**
     * CompletableFuture 사용
     * CompletableFuture.completedFuture
     */
    @Async
    public CompletableFuture<String> asyncMethod1() throws InterruptedException {
        System.out.println("asyncMethod1 시작!!");
        // api 호출이나 시간이 걸리는 작업들을 진행
        Thread.sleep(2000); // 2초
        System.out.println("asyncMethod1 종료!!");
        return CompletableFuture.completedFuture("asyncMethod1 결과 값입니다.");
    }

    @Async
    public CompletableFuture<String> asyncMethod2() throws InterruptedException {
        System.out.println("asyncMethod2 시작!!");
        // api 호출이나 시간이 걸리는 작업들을 진행
        Thread.sleep(3000); // 2초
        System.out.println("asyncMethod2 종료!!");
        return CompletableFuture.completedFuture("asyncMethod2 결과 값입니다.");
    }
}
