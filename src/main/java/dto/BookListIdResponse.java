package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@JsonInclude(NON_NULL)
public class BookListIdResponse {

    @JsonProperty("bookingid")
    int id;

}