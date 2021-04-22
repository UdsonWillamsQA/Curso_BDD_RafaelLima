package bdd.automation.api.steps;


import bdd.automation.api.suport.api.PetApi;
import bdd.automation.api.suport.api.UserApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class Configs {

    private UserApi userApi;
    private PetApi petApi;

    public Configs() {
        userApi = new UserApi();
        petApi = new PetApi();
    }

    @Before
    public void setup() {

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.baseURI = ("http://localhost:12345");
        RestAssured.basePath = "/api";
        //A especificação do request
        RestAssured.requestSpecification = new RequestSpecBuilder().addHeader("Authorization", getToken()).setContentType(ContentType.JSON).build();

        //A especificação do response
        //RestAssured.responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();

    }

    private String getToken() {
        return "grant access";
    }

    @After("@deleteAllUsers")
    public void deleteAllUsers() {  // metodo para apaghar o ussuario sempre que ele for criado.
        userApi.deleteAllUsers();
    }


    @After("@DeleteExtraPets")
    public void deleteExtraPets() {
        petApi.deleteExtraPets("available");
    }
}
