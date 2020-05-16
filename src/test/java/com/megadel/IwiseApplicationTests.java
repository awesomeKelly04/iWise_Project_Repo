//package com.megadel;
//
//import com.megadel.models.User;
//import com.megadel.repository.UserRepository;
//import com.megadel.service.ApiResponse;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.extension.RegisterExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//
//@SpringBootTest
//@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
//class IwiseApplicationTests {
//
//    @RegisterExtension
//    final RestDocumentationExtension restDocumentation = new RestDocumentationExtension ("custom");
//
//    @Autowired
//    private UserRepository userRepository;
//
//    List<User> users;
//    User user;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setUp(WebApplicationContext webApplicationContext,
//                      RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(documentationConfiguration(restDocumentation))
//                .build();
//
//        if(!userRepository.existsByUsername("awesomekelly")){
//            user = new User("Awesome Kelly", "awesomekelly",
//                    "awesomekelly04@outlook.com", "top$ecret");
//        }
//        else {
//            users = userRepository.findAll();
//            user = userRepository.findAll().get(2);
//        }
//    }
//
//    @Test
//    void contextLoads() throws Exception {
//    }
//}
