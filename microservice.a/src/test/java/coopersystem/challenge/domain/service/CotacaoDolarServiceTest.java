package coopersystem.challenge.domain.service;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoValueDTO;
import coopersystem.challenge.infrastructure.exception.BadRequestNotBusinessDayException;
import coopersystem.challenge.infrastructure.exception.DateEnteredGreaterThanCurrentDayException;
import io.quarkus.test.junit.QuarkusTest;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@QuarkusTest
public class CotacaoDolarServiceTest {

    @Inject
    CotacaoDolarService cotacaoDolarService;

    public static final CotacaoValueDTO COTACAO_VALUE = new CotacaoValueDTO(
            new BigDecimal("5.04730"), new BigDecimal("5.04790"), "2023-03-03 12:00:00.000");

    private static final String DATA_COTACAO_FORMAT = "MM-dd-yyyy";

    @Test
    void testGetCotacaoDolarDia() {
        Assertions.assertEquals(COTACAO_VALUE, cotacaoDolarService.findCotacaoDolar("03-03-2023"));
    }

    @Test
    void testGetCotacaoDolarDiaDataPosterior() {
        val date = LocalDate.now().plusDays(1);

        Throwable t = Assertions.assertThrows(DateEnteredGreaterThanCurrentDayException.class, () -> cotacaoDolarService.findCotacaoDolar(
                date.format(DateTimeFormatter.ofPattern(DATA_COTACAO_FORMAT))));

        Assertions.assertEquals(DateEnteredGreaterThanCurrentDayException.class, t.getClass());
    }

    @Test
    void testGetCotacaoDolarDiaNaoUtil() {
        Throwable t = Assertions.assertThrows(BadRequestNotBusinessDayException.class, () -> cotacaoDolarService.findCotacaoDolar(
                "03-05-2023"));

        Assertions.assertEquals(BadRequestNotBusinessDayException.class, t.getClass());
    }

    private LocalDate dataProximoDomingo(){
        val dataAtual = LocalDate.now();

        switch (dataAtual.getDayOfWeek()){
            case MONDAY:
                dataAtual.plusDays(5);
                break;
            case TUESDAY:
                dataAtual.plusDays(4);
                break;
            case WEDNESDAY:
                dataAtual.plusDays(3);
                break;
            case THURSDAY:
                dataAtual.plusDays(2);
                break;
            case FRIDAY:
                dataAtual.plusDays(1);
                break;
        }

        return dataAtual;
    }
}
