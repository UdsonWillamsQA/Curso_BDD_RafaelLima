package bdd.automation.api.steps;

import bdd.automation.api.suport.api.PetApi;
import bdd.automation.api.suport.domain.Pet;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import java.util.List;

public class PetsStepDefinitions {

    private PetApi petApi;
    private List<Pet> actualPets;
    private Response actualPetsResponse;

    public PetsStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que eu possua animais {word}")
    public void queEuPossuaAnimaisAvailable(String status) {
    }

    @Quando("eu pesquiso por todos os animais {word}")
    public void euPesquisoPorTodosOsAnimaisAvailable(String status) {
        actualPets = petApi.getPetsByStatus(status);
    }

    @Então("eu recebo a lista de animais available")
    public void euReceboAListaDeAnimaisAvailable() {
        assertThat(actualPets, is(not(empty())));
    }

    @Então("eu recebo a lista com {} animal/animais")
    public void euReceboAListaComAnimais(int petsQuantity) {
        assertThat(actualPets.size(), is(petsQuantity));
    }

    @Dado("que eu não possua animais {word}")
    public void queEuNãoPossuaAnimaisSold(String status) {
        petApi.deletePetByStatus(status);
    }

    @Quando("pesquiso por todos os animais {word}")
    public void pesquisoPorTodosOsAnimaisAvailable(String status) {
        actualPetsResponse = petApi.getPetsResponseByStatus(status);
    }

    @Entao("recebo a lista com {int} animais {word}")
    public void receboAListaComAnimaisAvailable(int petsQuantity, String status) {
        actualPetsResponse.
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "size()", is(petsQuantity),
                        "findAll { it.status == '" + status + "' }.size()", is(petsQuantity) //utilizando o grove colecctions
                );
    }

    @E("{int} animais poossuem o nome {word}")
    public void animaisPoossuemONomeLion(int petsQuantity, String petName) {
        actualPetsResponse.
                then().
                body(
                        "findAll {it.name.contains('" + petName + "')}.size()", is(petsQuantity) //utilizando o grove colecctions
                );
    }

    @E("eu recebo uma outra lista de animais {word}")
    public void euReceboUmaOutraListaDeAnimaisAvailable(String status) {
        Response actualPetsResponse = petApi.getPetsResponseByStatus(status);

        actualPets = actualPetsResponse.body().jsonPath().getList("", Pet.class);

        actualPetsResponse.
                then().
                statusCode(HttpStatus.SC_OK).
                body("size()", is(actualPets.size()),
                        "findAll { it.status == '" + status + "' }.size()", is(actualPets.size())
                );
    }
}
