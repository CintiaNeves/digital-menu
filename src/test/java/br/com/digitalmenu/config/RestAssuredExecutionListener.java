package br.com.digitalmenu.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class RestAssuredExecutionListener implements TestExecutionListener {

    @Override
    public void beforeTestMethod(final TestContext testContext) throws Exception {

        final var port = testContext.getApplicationContext()
                .getEnvironment().getProperty("local.server.port", Integer.class, 8090);

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setPort(port)
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
