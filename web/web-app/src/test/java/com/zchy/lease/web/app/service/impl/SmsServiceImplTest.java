package com.zchy.lease.web.app.service.impl;

import com.zchy.lease.web.app.service.SmsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SmsServiceImplTest {

    @Autowired
    private SmsService service;

    @Test
    void sendCode() {
        service.sendCode("13405122389","1234");
    }
}