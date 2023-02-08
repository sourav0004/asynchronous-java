package com.learnjava.paralellstreams;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.*;
import static com.learnjava.util.LoggerUtil.log;
import static org.junit.jupiter.api.Assertions.*;

class ParalellStreamsExampleTest {

    ParalellStreamsExample paralellStreamsExample=new ParalellStreamsExample();

    @Test
    void stringTransForm() {
        //given
        List<String> inputList= DataSet.namesList();
        //when
        startTimer();
        List<String> resultList=paralellStreamsExample.stringTransForm(inputList);
        timeTaken();

        //then
        assertEquals(inputList.size(),resultList.size());
        resultList.forEach(name->{
            assertTrue(name.contains("-"));
        });
    }

    @ParameterizedTest
    @ValueSource(booleans = {true,false})
    void stringTransForm_1(boolean isParallel) {
        stopWatchReset();
        //given
        List<String> inputList= DataSet.namesList();
        //when
        startTimer();
        List<String> resultList=paralellStreamsExample.stringTransForm_1(inputList,isParallel);
        timeTaken();

        //then
        assertEquals(inputList.size(),resultList.size());
        resultList.forEach(name->{
            assertTrue(name.contains("-"));
        });
    }

    @Test
    void stringTransForm_2() {
        //given
        List<String> inputList= DataSet.namesList();
        //when
        startTimer();
        List<String> resultList=paralellStreamsExample.stringTransForm_2(inputList);
        timeTaken();

        //then
        assertEquals(inputList.size(),resultList.size());
        resultList.forEach(name->{
            assertTrue(Character.isUpperCase(name.charAt(0)));
        });
        log("Final Result : "+ resultList);
    }
}