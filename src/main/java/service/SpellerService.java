package service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static entities.RequestOptions.PARAM_TEXT;
import static entities.RequestOptions.SPELLER_URI;
import static io.restassured.RestAssured.given;

public class SpellerService {

    private String text;

    private RequestSpecification REQUEST_SPECIFICATION;

    public SpellerService(String text) {
        this.text = text;
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addParam(PARAM_TEXT, text)
                .build();
    }

    public Response getWithNoParams() {

        return given(REQUEST_SPECIFICATION).get(SPELLER_URI);
    }

    public Response getWithParams(Map<String, Object> params) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            specification.param(param.getKey(), param.getValue());
        }

        return specification.get(SPELLER_URI);
    }
}