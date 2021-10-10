package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_NULL)
public class BookResponse {

    @JsonProperty("bookingid")
    int id;
    @JsonProperty("booking")
    BookDTO booking;

}