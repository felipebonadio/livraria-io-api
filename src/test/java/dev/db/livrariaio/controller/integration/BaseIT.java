package dev.db.livrariaio.controller.integration;

import dev.db.livrariaio.LivrariaIoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = LivrariaIoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseIT {
    @LocalServerPort
    protected int port;

    protected String baseUrl = "http://localhost";

    @Autowired
    protected TestRestTemplate restTemplate;
}
