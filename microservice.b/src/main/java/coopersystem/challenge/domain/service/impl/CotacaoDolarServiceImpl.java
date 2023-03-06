package coopersystem.challenge.domain.service.impl;

import coopersystem.challenge.domain.model.external.CotacaoDolarAPI.CotacaoDolarDiaResponse;
import coopersystem.challenge.domain.model.internal.Cotacao;
import coopersystem.challenge.domain.model.internal.CotacaoResponse;
import coopersystem.challenge.domain.service.CotacaoDolarService;
import coopersystem.challenge.infrastructure.api.CotacaoDolarAPI;
import coopersystem.challenge.infrastructure.api.exception.BadRequestNotBusinessDayException;
import coopersystem.challenge.infrastructure.api.exception.DateEnteredGreaterThanCurrentDayException;
import lombok.val;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.jboss.logging.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@ApplicationScoped
public class CotacaoDolarServiceImpl implements CotacaoDolarService {

    private static final Logger LOG = Logger.getLogger(CotacaoDolarServiceImpl.class);

    private static final String DATA_COTACAO_FORMAT = "MM-dd-yyyy";
    private static final String BAD_REQUEST_MESSAGE_NOT_BUSINESS_DAY = "A Data informada não é um dia útil";
    private static final String DATE_ENTERED_GREATER_THAN_THE_CURRENT_DATE = "A Data informada é posterior a Data Atual";

    @Inject
    @RestClient
    CotacaoDolarAPI cotacaoDolarAPI;

    @Override
    public CotacaoResponse findCotacaoDolar(String dataCotacao) {
        validateDataCotacao(dataCotacao);

        val cotacaoResponse = CotacaoResponse.builder().build();
        val cotacaoSolicitada = cotacaoDolarAPI.findCotacaoDolar(dataCotacao);

        if(Objects.nonNull(cotacaoSolicitada)){
            cotacaoResponse.setCotacaoSolicitada(toCotacao(cotacaoSolicitada));

            LOG.info("Cotação para o dia " + dataCotacao +" retornada com Success");
        }

        val dataUlimoDiaUtilAnterior = retornaUltimoDiaUtilAnterior(dataCotacao);
        val cotacaoUltimoDiaUtilAnterior = cotacaoDolarAPI.findCotacaoDolar(retornaUltimoDiaUtilAnterior(dataCotacao));

        if (Objects.nonNull(cotacaoUltimoDiaUtilAnterior)){
            cotacaoResponse.setCotacaoUltimoDiaUtilAnterior(toCotacao(cotacaoUltimoDiaUtilAnterior));

            LOG.info("Cotação para o ultimo dia útil anterior: " + dataUlimoDiaUtilAnterior +" retornada com Success");
        }

        return cotacaoResponse;
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

    private String retornaUltimoDiaUtilAnterior(String dataCotacao){
        val dateTimeFormatter = DateTimeFormatter.ofPattern(DATA_COTACAO_FORMAT);
        val dataSolicitada = LocalDate.parse(dataCotacao, dateTimeFormatter);

        if(dataSolicitada.getDayOfWeek() == DayOfWeek.MONDAY){
            return dataSolicitada.minusDays(3).format(dateTimeFormatter);
        }

        return dataSolicitada.minusDays(1).format(dateTimeFormatter);
    }

    private Cotacao toCotacao(CotacaoDolarDiaResponse cotacaoDolarDiaResponse){
        return Cotacao.builder()
                        .cotacaoCompra(cotacaoDolarDiaResponse.getCotacaoCompra())
                        .cotacaoVenda(cotacaoDolarDiaResponse.getCotacaoVenda())
                        .dataHoraCotacao(cotacaoDolarDiaResponse.getDataHoraCotacao()).build();
    }
}
