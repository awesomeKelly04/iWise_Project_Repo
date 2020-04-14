package com.megadel.iwise.entity;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EntityTests {

    private Wallet myWallet;

    @BeforeEach
    void setUp() throws Exception {
        myWallet = new Wallet();
    }

    @AfterEach
    void tearDown() throws Exception{

    }

    @BeforeAll
    static void beforeAll() throws Exception {

    }

    @AfterAll
    static void afterAll() throws Exception {

    }

    @Test
    void contextLoads() {
    }

    @Test
    void createWalletEntity() {
        assertNotNull(myWallet);
    }
}
