package com.example.challenge.model;

//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class User {

    //    @ApiModelProperty(notes = "Database id of the User", name = "id")
    private final Integer id;
    //    @ApiModelProperty(notes = "Nickname of the User", name = "nickname", value = "jonny3248359253")
    private final String nickname;
    //    @ApiModelProperty(notes = "Email of the User", name = "email", required = true, value = "letsgo763@gmail.com")
    private final String email;
    //    @ApiModelProperty(notes = "Password of the User", name = "password", required = true, value = "somePass32")
    private final String password;
    private List<FullChallenge> challenges;

}
