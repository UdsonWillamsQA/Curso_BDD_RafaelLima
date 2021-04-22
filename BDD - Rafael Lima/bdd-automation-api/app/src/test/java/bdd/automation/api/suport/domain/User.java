package bdd.automation.api.suport.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder // permite instanciar um classe
public class User {
   @Builder.Default
    private int id = 10;
   @Builder.Default
    private String username = "udsonwillams";
   @Builder.Default
    private String firstName = "Udson";
   @Builder.Default
    private String lastName = "Willams";
   @Builder.Default
    private String email = "udson@mail";
   @Builder.Default
    private String password = "12345";
   @Builder.Default
    private String phone = "8399899998";
   @Builder.Default
    private int userStatus = 1;
};
