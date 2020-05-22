package com.megadel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megadel.client.exception.AppException;
import com.megadel.models.Role;
import com.megadel.models.User;
import com.megadel.models.Wallet;
import com.megadel.models.projectenum.RoleName;
import com.megadel.repository.RoleRepository;
import com.megadel.repository.UserRepository;
import com.megadel.repository.WalletRepository;
import com.megadel.service.LoginRequest;
import com.megadel.service.SignUpRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static java.time.Instant.now;
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
    private RoleRepository roleRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    LoginRequest loginRequest = new LoginRequest();

    List<User> users;
    Optional<User> user;
    Role userRole;

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
    }

    @Test
    public void signUpTest() throws Exception{
        String userJson = asJsonString(request);
        mockMvc.perform(post("/api/auth/signup")
        .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
        .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
                .andDo(getDocument());

    }

    @Test
    public void logInTest() throws Exception{
        user = userRepository.findById((long) 1);
        loginRequest.setUsernameOrEmail(user.get().getEmail());
        loginRequest.setPassword("Nigeria");
        String userJson = asJsonString(loginRequest);
        System.out.println(userJson);
        mockMvc.perform(post("/api/auth/signin")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tokenType").value("Bearer"))
                .andDo(getDocument());
    }

    @Test
    public void getAllUserTest() throws Exception{
        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.users").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.users[*].username").isNotEmpty())
                .andDo(getDocument());
    }

    @Test
    public void getASingleUserTest() throws Exception{
        user = userRepository.findById((long) 1);
        mockMvc.perform(get("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(user.get().getUsername()))
                .andDo(getDocument());
    }

    @Test
    public void updateAUserTest() throws Exception{
        user = userRepository.findById((long) 1);
        User tempUser = new User();
        tempUser.setPhoneNumber("08125999453");
        tempUser.setPassword(user.get().getPassword());
        tempUser.setEmail(user.get().getEmail());
        tempUser.setName(user.get().getName());
        tempUser.setUsername(user.get().getUsername());
        mockMvc.perform(put("/users/1")
                .content(asJsonString(tempUser))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andDo(getDocument());
    }

    @Test
    public void editAUserRoleTest() throws Exception{
        userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        System.out.println(Collections.singleton(userRole));
        mockMvc.perform(patch("/users/1")
                .contentType("application/json-patch+hal+json")
                .content(asJsonString(userRole))
                .accept("application/json-patch+json")).andDo(print())
                .andExpect(status().isOk())
                .andDo(getDocument());

    }

    @Test
    public void createWalletTest() throws Exception{
        Wallet wallet = new Wallet();
        wallet.setPoint(1000);
        wallet.setCeloDollar(0.0);
        wallet.setNaira(0.0);
        String userJson = asJsonString(wallet);

        mockMvc.perform(post("/wallets")
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.point").value(1000))
                .andDo(getDocument());
    }

    @Test
    public void updateAUserWithWalletTest() throws Exception{
        Optional<Wallet> wallet = walletRepository.findById(2);
        String tempUserWallet = asJsonString(wallet);
        System.out.println(tempUserWallet);

        userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        System.out.println(Collections.singleton(userRole));

        mockMvc.perform(patch("/users/6/wallets")
                .content(tempUserWallet)
                .contentType("application/json-patch+json")).andDo(print())
                .andExpect(status().is(202))
                .andDo(getDocument());
    }

    private RestDocumentationResultHandler getDocument() {
        return document("{methodName}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()));
    }

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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
