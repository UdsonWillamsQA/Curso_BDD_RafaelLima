package bdd.automation.api.suport.api;

import bdd.automation.api.suport.domain.User;
import org.apache.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class UserApi {

    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String USER_ENDPOINT = "/v3/user/{name}";

    //metodo de criar um usuario
    public void creatUser(User user){
        given().
                body(user).
        when().
                post(CREATE_USER_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK);
    }
    // metodo de pegar o user name
    public String getUserName(User user) {
        return given().
                pathParam("name", user.getUsername()).
        when().
                get(USER_ENDPOINT).
        thenReturn().
                path("username");
    }
    //metodo de deletar todos os usuarios, no nosso caso o usuario, cadastrado no sistema.
    public void deleteAllUsers(){
        List<String> usersList = Arrays.asList("udsonwillams");

        for(String user: usersList){
            given().
                pathParam("name", user).
            when().
                delete(USER_ENDPOINT).
            then().
                statusCode(HttpStatus.SC_OK);
        }
    }
}
