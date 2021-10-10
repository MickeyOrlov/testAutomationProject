package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = BookDTO.BookDTOBuilder.class)
public class BookDTO {

    @JsonProperty("bookingid")
    int id;
    @JsonProperty("firstname")
    String firstName;
    @JsonProperty("lastname")
    String lastName;
    @JsonProperty("totalprice")
    int totalPrice;
    @JsonProperty("depositpaid")
    Boolean depositPaid;
    @JsonProperty("bookingdates")
    Object bookingDates;
    @JsonProperty("additionalneeds")
    String additionalNeeds;

    @Value
    @Builder(toBuilder = true)
    @JsonDeserialize(builder = BookingDates.BookingDatesBuilder.class)
    public static class BookingDates {
        @JsonProperty("checkin")
        LocalDate checkin;
        @JsonProperty("checkout")
        LocalDate checkout;
    }

}