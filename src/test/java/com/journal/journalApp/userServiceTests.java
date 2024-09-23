package com.journal.journalApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.journal.journalApp.entity.userEntry;
import com.journal.journalApp.repository.userRepository;

@SpringBootTest
public class userServiceTests {

   @Autowired
   private userRepository UserRepository;

   @Disabled  // --> for disable this test only
   @Test
   public void testFindByUserName() {
    userEntry user = UserRepository.findByUserName("Ram");
    assertNotNull(UserRepository.findByUserName("Ram"));
    assertTrue(!user.getJournalEntries().isEmpty());
   }


   @ParameterizedTest
   @CsvSource({
             "Ram",
             "Ori",
             "Sanya",
             "Radha"
   })
   public void testFindByUserName1(String name){
    assertNotNull(UserRepository.findByUserName(name),"failed for:" + name);
   }

//    @BeforeEach --> must read all notation
//    @BeforeAll
//    @AfterAll
//    @AfterEach
 
//    @Disabled  //--> For disable the test cases
   @ParameterizedTest
   @CsvSource({
             "1,1,2",
             "2,2,4",
             "3,3,6"
   })
   public void test(int a, int b, int expected){
    assertEquals(expected, a+b);
   }

}
