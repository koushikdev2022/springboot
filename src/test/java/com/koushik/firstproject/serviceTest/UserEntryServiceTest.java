package com.koushik.firstproject.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koushik.firstproject.entity.UserEntry;
import com.koushik.firstproject.repositary.UserEntryRepo;

@SpringBootTest
public class UserEntryServiceTest {
    @Autowired
    private UserEntryRepo userEntryRepo;

    public void testFindByUserName(){
        assertNotNull(userEntryRepo.findByUserName("kfldlfmlkfjfglkjgfjl"));
 
    }

    // @ParameterizedTest
    // @CsvSource({
    //     "1,1,2",
    //     "2,2,6",
    //     "3,3,13"
    // })
    // public void test(int a,int b, int exp){
    //     assertEquals(exp, a+b);
    // }
}
