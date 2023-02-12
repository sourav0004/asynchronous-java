package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CompletableFutureHelloWorldTest {
    HelloWorldService hws=new HelloWorldService();
    CompletableFutureHelloWorld cfhw=new CompletableFutureHelloWorld(hws);


    @Test
    void helloWorld() {

        CompletableFuture<String> completableFuture=cfhw.helloWorld();
        completableFuture.thenAccept((s->{

            assertEquals("HELLO WORLD",s);
        }))
                .join();
    }

    @Test
    void helloworld_multiple_async_call(){
        String helloWorld=cfhw.helloworld_multiple_async_call();
        assertEquals("HELLO WORLD!",helloWorld);
    }

    @Test
    void helloworld_3_multiple_async_call(){
        String helloWorld=cfhw.helloworld_3_multiple_async_call();
        assertEquals("HELLO  WORLD!HI!",helloWorld);
    }

    @Test
    void helloworld_completableFuture_async_call(){
        CompletableFuture<String> completableFuture=cfhw.helloworld_completableFuture_async_call();
        completableFuture.thenAccept(s->{
            assertEquals("Hello World!",s);
        });
    }
}