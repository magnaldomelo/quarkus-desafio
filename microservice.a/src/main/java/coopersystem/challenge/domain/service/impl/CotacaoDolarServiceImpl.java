package coopersystem.challenge.domain.service.impl;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaRequest;
import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaResponse;
import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoValueDTO;
import coopersystem.challenge.domain.service.CotacaoDolarService;
import coopersystem.challenge.infrastructure.api.CotacaoDolarDiaAPI;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import coopersystem.challenge.infrastructure.exception.BadRequestNotBusinessDayException;
import coopersystem.challenge.infrastructure.exception.DateEnteredGreaterThanCurrentDayException;
import lombok.val;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

@ApplicationScoped
public class CotacaoDolarServiceImpl implements CotacaoDolarService {

    private static final Logger LOG = Logger.getLogger(CotacaoDolarServiceImpl.class);

    private static final String DATA_COTACAO_FORMAT = "MM-dd-yyyy";
    private static final String BAD_REQUEST_MESSAGE_NOT_BUSINESS_DAY = "A Data informada não é um dia útil";
    private static final String DATE_ENTERED_GREATER_THAN_THE_CURRENT_DATE = "A Data informada é posterior a Data Atual";

    @Inject
    @RestClient
    CotacaoDolarDiaAPI cotacaoDolarDiaAPI;

    @Override
    public CotacaoValueDTO findCotacaoDolar(String dataCotacao) {
        validateDataCotacao(dataCotacao);
        CotacaoDiaResponse cotacao = cotacaoDolarDiaAPI.getCotacaoDolarDia(new CotacaoDiaRequest(dataCotacao));

        CotacaoValueDTO cotacaoValueDTO = toCotacaoValue(cotacao);

        LOG.info("Cotação para o dia " + dataCotacao +" retornada com Success");

        return cotacaoValueDTO;
    }

    private void validateDataCotacao(String dataCotacao) {
        val date = LocalDate.parse(dataCotacao, DateTimeFormatter.ofPattern(DATA_COTACAO_FORMAT));
        val dayOfWeek = date.getDayOfWeek();

        if(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            LOG.error(BAD_REQUEST_MESSAGE_NOT_BUSINESS_DAY);
            throw new BadRequestNotBusinessDayException();
        }

        if(date.isAfter(LocalDate.now())){
            LOG.error(DATE_ENTERED_GREATER_THAN_THE_CURRENT_DATE);
            throw new DateEnteredGreaterThanCurrentDayException();
        }
    }

    private CotacaoValueDTO toCotacaoValue(CotacaoDiaResponse cotacaoDiaResponse){
        Set<CotacaoValueDTO> cotacaoValueDTO = cotacaoDiaResponse.getValue();

        if (!cotacaoValueDTO.isEmpty()){
            return cotacaoValueDTO.iterator().next();
        }

        return null;
    }
}
