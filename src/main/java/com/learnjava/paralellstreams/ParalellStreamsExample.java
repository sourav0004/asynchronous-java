package com.learnjava.paralellstreams;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.delay;
import static com.learnjava.util.CommonUtil.stopWatch;
import static com.learnjava.util.LoggerUtil.log;

public class ParalellStreamsExample {

    public List<String> stringTransForm(List<String> namesList){
        return namesList
                //.stream()
                .parallelStream()
                .map(this::addNameLengthTransform)
                //.sequential()
                //.parallel()
                .collect(Collectors.toList());
    }

    public List<String> stringTransForm_1(List<String> namesList,boolean isParallel){
        Stream<String> namesStream=namesList.stream();
        if(isParallel)
            namesStream.parallel();
        return   namesStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> stringTransForm_2(List<String> namesList){
        Stream<String> namesStream=namesList.parallelStream();
        return   namesStream
                .map(this::string_toLowerCase)
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {

        stopWatch.start();
        List<String> resultList;
        List<String> names = DataSet.namesList();
        ParalellStreamsExample paralellStreamsExample =new ParalellStreamsExample();
        resultList= paralellStreamsExample.stringTransForm(names);

        stopWatch.stop();
        log("Final Result : "+ resultList);
        log("Total Time Taken : "+ stopWatch.getTime());
    }

    private  String addNameLengthTransform(String name) {
        delay(500);
        return name.length()+" - "+name ;
    }

    private  String string_toLowerCase(String name) {
        delay(500);
        return name.toUpperCase();
    }
}
