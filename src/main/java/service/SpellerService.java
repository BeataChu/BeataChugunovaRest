package service;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static entities.RequestOptions.*;
import static io.restassured.RestAssured.given;

public class SpellerService {

    private RequestSpecification REQUEST_SPECIFICATION;


    public SpellerService() {
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }


    private RequestSpecification getSpecWithOptions(Map<String, Object> params) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            specification.param(param.getKey(), param.getValue());
        }
        return specification;
    }

    public Response checkText(String[] text, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(PARAM_TEXT, text[0]);
        return getSpecWithOptions(params).get(SPELLER_URI + SINGLE_CHECK_URI);
    }

    public Response checkTexts(String[] texts, Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        RequestSpecification specification = getSpecWithOptions(params);
        for (String line : texts) {
            specification.param(PARAM_TEXT, line);
        }
        return specification.get(SPELLER_URI + MULTIPLE_CHECK_URI);
    }
}