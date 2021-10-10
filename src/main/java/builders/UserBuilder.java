package builders;

import dto.TokenDTO;

public class UserBuilder {

    public TokenDTO AdminUser() {
        return TokenDTO.builder()
                .userName("admin")
                .password("password123")
                .build();
    }

}