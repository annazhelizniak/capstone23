package edu.kmaooad.capstone23.orgs.jobs.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateJobControllerTest {
    @Test
    @DisplayName("Create job: valid input")
    public void testBasicOrgCreation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "Professor");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/create")
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Create job: invalid input")
    public void testOrgCreationWithNameValidation() {
        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "professor_of_linear_algebra");

        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post("/jobs/create")
                .then()
                .statusCode(400);
    }
}