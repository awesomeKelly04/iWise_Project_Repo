package com.megadel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megadel.models.User;
import com.megadel.repository.UserRepository;
import com.megadel.service.LoginRequest;
import com.megadel.service.SignUpRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class IwiseApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private static SignUpRequest request;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    LoginRequest loginRequest = new LoginRequest();

    List<User> users;
    Optional<User> user;

    private MockMvc mockMvc;

    @BeforeAll
    static void beforeAll() {
        request = new SignUpRequest();
        request.setName(generateRandomWords() +" "+ generateRandomWords());
        request.setUsername(generateRandomWords());
        request.setEmail(generateRandomWords() + "@outlook.com");
        request.setPassword(generateRandomWords()+"$");
    }

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        loginRequest.setUsernameOrEmail(request.getEmail());
        loginRequest.setPassword(request.getPassword());

        users = userRepository.findAll();
        user = userRepository.findById((long) 1);
    }

    @Test
    public void signUpTest() throws Exception{
        String userJson = new ObjectMapper().writeValueAsString(request);
        mockMvc.perform(post("/api/auth/signup")
        .content(userJson)
        .contentType("application/hal+json")).andDo(print())
        .andExpect(status().is(201))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void logInTest() throws Exception{
        String userJson = new ObjectMapper().writeValueAsString(loginRequest);
        mockMvc.perform(post("/api/auth/signin")
                .content(userJson)
                .contentType("application/hal+json")).andDo(print())
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void getAllUserTest() throws Exception{
        mockMvc.perform(get("/users")
                .contentType("application/hal+json")).andDo(print())
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(tempUsers)))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void getASingleUserTest() throws Exception{
        mockMvc.perform(get("/users/1")
                .contentType("application/hal+json")).andDo(print())
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(user)))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void updateAUserTest() throws Exception{
        User tempUser = new User();
        tempUser.setPhoneNumber("08125123453");
        tempUser.setPassword(user.get().getPassword());
        tempUser.setEmail(user.get().getEmail());
        tempUser.setName(user.get().getName());
        tempUser.setUsername(user.get().getUsername());
        mockMvc.perform(put("/users/1")
                .content(new ObjectMapper().writeValueAsString(tempUser))
                .contentType("application/hal+json")).andDo(print())
                .andExpect(status().is(204))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

//    @Test
//    public void editAUserRoleTest() throws Exception{
//        User tempUser = new User();
////        Role role = URI.create("http://localhost:7070/roles/1");
//        tempUser.setRoles(new HashSet<>());
//        mockMvc.perform(put("/users/1")
//                .content(String.valueOf(tempUser))
//                .contentType("application/hal+json")).andDo(print())
//                .andExpect(status().is(202))
//                .andDo(document("{methodName}",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())));
//    }

    public static String generateRandomWords()
    {
        String randomStrings;
        Random random = new Random();
        char[] word = new char[random.nextInt(6)+6]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for(int j = 0; j < word.length; j++)
        {
            word[j] = (char)('a' + random.nextInt(26));
        }
        randomStrings = new String(word);
        return randomStrings;
    }
}
