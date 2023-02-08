package com.learnjava.forkjoin;

import com.learnjava.util.DataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ForkJoinUsingRecursion extends RecursiveTask<List<String>> {
    private  List<String> inputList;

    public ForkJoinUsingRecursion(List<String> inputList) {
        this.inputList = inputList;
    }

    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList;
        List<String> names = DataSet.namesList();

        //Using Fork Join Pool
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinUsingRecursion forkJoinUsingRecursion=new ForkJoinUsingRecursion(names);
        resultList=forkJoinPool.invoke(forkJoinUsingRecursion);


        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }

    private static String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    @Override
    protected List<String> compute() {
        if(inputList.size()<=1)
        {
           List<String> result=new ArrayList<>();
           inputList.forEach(names->result.add(addNameLengthTransform(names)));
           return result;
        }
        int midpoint=inputList.size()/2;
        ForkJoinTask<List<String>> leftInputlist=new ForkJoinUsingRecursion(inputList.subList(0,midpoint)).fork();
        inputList=inputList.subList(midpoint,inputList.size()); //represnts the right side
        List<String> rightResult=compute(); //recursion happens
        List<String> leftResult=leftInputlist.join();
        leftResult.addAll(rightResult);

        return leftResult;
    }
}
