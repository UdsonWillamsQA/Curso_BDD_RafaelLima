package bdd.automation.api.steps;

import bdd.automation.api.suport.api.UserApi;
import bdd.automation.api.suport.domain.User;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserStepDefinitions {

    private static final String USER_ENDPOINT = "/v3/user/{name}";
    private User expectedUser;
    private UserApi userApi = new UserApi();

    public UserStepDefinitions(){
        userApi = new UserApi();
    }

    @Quando("crio um usuário")
    public void criaUsuário() {
        expectedUser = User.builder().build(); // Aqui cria um usuario

        userApi.creatUser(expectedUser); // Aqui joga esse usuario dentro da API
    }

    @Então("o usuário é salvo no sistema")
    public void validarUsuarioSalvoNoSistema() {
        String acturalUserName = userApi.getUserName(expectedUser); // Aqui pedimos o nome do usuario

        assertThat(acturalUserName, is(expectedUser.getUsername())); //aqui acertamos se o nome do usuario atual é o mesmo do usuario que foi criado.
    }
}
