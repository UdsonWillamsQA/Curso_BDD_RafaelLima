package bdd.automation.api.steps;

import bdd.automation.api.suport.api.PetApi;
import bdd.automation.api.suport.api.StoreApi;
import bdd.automation.api.suport.domain.Order;
import bdd.automation.api.suport.domain.Pet;
import bdd.automation.api.suport.domain.builders.OrderBuilder;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;

public class StoreStepdefinitions {

    PetApi petApi;
    StoreApi storeApi;

    Pet expectedPet;
    Order expectedOrder;

    public StoreStepdefinitions(){
        petApi = new PetApi();
        storeApi = new StoreApi();
    }

    @Dado("que eu possua animal {word}")
    public void queEuPossuaAnimalAvailable(String status) {
       Pet pet = Pet.builder().
                id(333).
                status(status).
                build();
        expectedPet = petApi.createPet(pet);

    }

    @Quando("faço o pedido desse animal")
    public void iOrderThatPet() {
        Order order = new OrderBuilder()
                .withId(888)
                .withPetId(expectedPet.getId())
                .build();

        expectedOrder = storeApi.createOrder(order);
    }


    @Entao("o pedido é aprovado")
    public void oPedidoÉAprovado() {
        Response actualOrderResponse = storeApi.getOrder(expectedOrder.getId());
        actualOrderResponse.
            then().
                body("id", is(expectedOrder.getId()),
                        "petId", is(expectedPet.getId()),
                        "quantity", is(expectedOrder.getQuantity()),
                        "status", is("approved")
                    );
    }
}
