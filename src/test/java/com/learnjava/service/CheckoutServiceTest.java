package com.learnjava.service;

import com.learnjava.domain.checkout.Cart;
import com.learnjava.domain.checkout.CheckoutResponse;
import com.learnjava.domain.checkout.CheckoutStatus;
import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutServiceTest {
    PriceValidatorService priceValidatorService=new PriceValidatorService();
    CheckoutService checkoutService=new CheckoutService(priceValidatorService);

    @Test
    void checkout_6_items() {

        Cart items=DataSet.createCart(6);
        CheckoutResponse checkoutResponse=checkoutService.checkout(items);
        assertEquals(CheckoutStatus.SUCCESS,checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate()>0);
    }

    @Test
    void checkout_13_items() {

        Cart items=DataSet.createCart(13);
        CheckoutResponse checkoutResponse=checkoutService.checkout(items);
        assertEquals(CheckoutStatus.FAILURE,checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate()>0);
    }

    @Test
    void modify_parallelism() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","100");
        Cart items=DataSet.createCart(100);
        CheckoutResponse checkoutResponse=checkoutService.checkout(items);
        assertEquals(CheckoutStatus.FAILURE,checkoutResponse.getCheckoutStatus());
        assertTrue(checkoutResponse.getFinalRate()>0);
    }

    @Test
    void no_of_cores(){
        System.out.println("No Of Cores= "+Runtime.getRuntime().availableProcessors());
    }

    @Test
    void paralellism(){
        System.out.println("Parallelism "+ ForkJoinPool.getCommonPoolParallelism());
    }
}