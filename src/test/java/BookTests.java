import dto.BookDTO;
import dto.BookListIdResponse;
import dto.BookResponse;
import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AuthService;
import services.BookingService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookTests {

    AuthService auth = new AuthService();
    BookingService bookingService = new BookingService();

    @Test
    @DisplayName("Создание книги")
    void createBookTest() {
        //given
        String token = auth.getToken();
        BookResponse createBook = bookingService.createBook(token, dtoBuild());
        //then
        assertEquals(createBook.getBooking().getAdditionalNeeds(), dtoBuild().getAdditionalNeeds());
        assertEquals(createBook.getBooking().getFirstName(), dtoBuild().getFirstName());
        assertEquals(createBook.getBooking().getLastName(), dtoBuild().getLastName());
        assertEquals(createBook.getBooking().getTotalPrice(), dtoBuild().getTotalPrice());
        assertEquals(createBook.getBooking().getDepositPaid(), dtoBuild().getDepositPaid());
        assertNotNull(createBook.getId());
    }


    @Test
    @Ignore("api не поддерживает 2 операции подряд")
    @DisplayName("Запрашиваем книгу из каталога и изменяем ее")
    void updateBookTest() {
        //given
        String token = auth.getToken();
        List<BookListIdResponse> getListBook = bookingService.getBooks(token);
        BookDTO getBook = bookingService.getBookId(token, getListBook.get(2).getId());
        //when
        BookDTO updateBook = bookingService.updateBook(dtoBuild().toBuilder()
                .firstName("New first Name")
                .depositPaid(false)
                .totalPrice(43)
                .lastName("New last Name")
                .additionalNeeds("new recommends")
                .build(), token, getListBook.get(2).getId());
        BookDTO getBookUpdate = bookingService.getBookId(token, getListBook.get(2).getId());
        //then
        assertEquals(updateBook.getFirstName(), getBookUpdate.getFirstName());
        assertEquals(updateBook.getLastName(), getBookUpdate.getLastName());
        assertEquals(updateBook.getTotalPrice(), getBookUpdate.getTotalPrice());
        assertEquals(updateBook.getAdditionalNeeds(), getBookUpdate.getAdditionalNeeds());
        assertEquals(updateBook.getDepositPaid(), getBookUpdate.getDepositPaid());
    }

    @Test
    @Ignore("api не поддерживает 2 операции подряд")
    @DisplayName("Удалить книгу")
    void deleteBookTest() {
        String token = auth.getToken();
        List<BookListIdResponse> getListBook = bookingService.getBooks(token);
        bookingService.deleteBook(token, getListBook.get(3).getId());
    }

    @Test
    @Ignore("api не поддерживает 2 операции подряд")
    @DisplayName("обновить часть полей")
    void updatePartialBookTest() {
        //given
        String token = auth.getToken();
        BookResponse createBook = bookingService.createBook(token, dtoBuild());
        var update = bookingService.partialUpdateBook(dtoBuildPartialUpdate(), token, createBook.getId());
        //then
        assertEquals(createBook.getBooking().getFirstName(), dtoBuildPartialUpdate().getFirstName());
        assertEquals(createBook.getBooking().getLastName(), dtoBuildPartialUpdate().getLastName());
        assertEquals(createBook.getBooking().getTotalPrice(), dtoBuildPartialUpdate().getTotalPrice());
    }

    private BookDTO dtoBuild() {
        return BookDTO.builder()
                .firstName("Alex")
                .lastName("John")
                .totalPrice(123)
                .depositPaid(true)
                .bookingDates(BookDTO.BookingDates.builder()
                        .checkin(LocalDate.of(2018, 01, 01))
                        .checkout(LocalDate.of(2019, 01, 01))
                        .build())
                .additionalNeeds("bla bla")
                .build();
    }

    private BookDTO dtoBuildPartialUpdate() {
        return BookDTO.builder()
                .firstName("New Name")
                .lastName("New las name")
                .totalPrice(999)
                .build();
    }

}