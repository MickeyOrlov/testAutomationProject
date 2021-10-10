package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = TokenDTO.TokenDTOBuilder.class)
public class TokenDTO {

    @JsonProperty("username")
    String userName;
    @JsonProperty("password")
    String password;
    @JsonProperty("token")
    String token;

}