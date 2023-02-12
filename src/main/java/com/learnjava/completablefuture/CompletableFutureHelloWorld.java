package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;

public class CompletableFutureHelloWorld {
    private HelloWorldService hws;
    public CompletableFutureHelloWorld(HelloWorldService hws) {
        this.hws = hws;
    }

    public String helloworld_multiple_async_call(){
        startTimer();
        CompletableFuture<String> hello=CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world=CompletableFuture.supplyAsync(()->hws.world());

        String hw= hello.thenCombine(world,(h,w)->h+" "+w)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();

        return hw;
    }

    public String helloworld_3_multiple_async_call(){
        startTimer();
        CompletableFuture<String> hello=CompletableFuture.supplyAsync(()->hws.hello());
        CompletableFuture<String> world=CompletableFuture.supplyAsync(()->hws.world());
        CompletableFuture<String> hicompletablefuture=CompletableFuture.supplyAsync(()->{
            delay(1000);
            return "Hi!";
        });

        String hw= hello.thenCombine(world,(h,w)->h+" "+w)
                .thenCombine(hicompletablefuture,(prev,curr)->prev+curr)
                .thenApply(String::toUpperCase)
                .join();
        timeTaken();

        return hw;
    }

    public CompletableFuture<String> helloworld_completableFuture_async_call(){
        return CompletableFuture.supplyAsync(hws::hello)
                        .thenCompose((prev)->hws.worldFuture(prev))
                .thenApply(String::toUpperCase);
    }



    public CompletableFuture<String> helloWorld(){
        return CompletableFuture.supplyAsync(hws::helloWorld) //runs this in a common fork-join pool
                .thenApply(String::toUpperCase);
                //.join(); //Block the Main thread
    }


    public static void main(String[] args) {
        HelloWorldService hws=new HelloWorldService();
        CompletableFuture.supplyAsync(hws::helloWorld) //runs this in a common fork-join pool
                .thenApply(String::toUpperCase)
                .thenAccept((res)->{
                    log("Result is= "+res);
                })
                .join(); //Block the Main thread
        log("Done");
        //delay(2000);
    }
}
