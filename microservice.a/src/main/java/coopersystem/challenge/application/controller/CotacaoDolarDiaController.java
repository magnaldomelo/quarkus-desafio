package coopersystem.challenge.application.controller;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.mapper.CotacaoDolarDiaMapper;
import coopersystem.challenge.domain.service.CotacaoDolarService;
import coopersystem.challenge.infrastructure.exception.BadRequestNotBusinessDayException;
import coopersystem.challenge.infrastructure.exception.DateEnteredGreaterThanCurrentDayException;
import io.quarkus.runtime.util.StringUtil;
import lombok.val;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Path("/dolar_cotacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CotacaoDolarDiaController {

    @Inject
    private CotacaoDolarService cotacaoDolarService;

    @Inject
    CotacaoDolarDiaMapper cotacaoDolarDiaMapper;

    private static final Logger LOG = Logger.getLogger(CotacaoDolarDiaController.class);

    private static final String BAD_REQUEST_MESSAGE = "A Data informada é inválida. Formato válido: MM-dd-yyyy";
    private static final String BAD_REQUEST_MESSAGE_NOT_BUSINESS_DAY = "A Data informada não é um dia útil";
    private static final String DATE_ENTERED_GREATER_THAN_THE_CURRENT_DATE = "A Data informada é posterior a Data Atual";

    private static final String DATE_QUOTATION_FORMAT = "MM-dd-yyyy";

    @GET
    public Response findCotacaoDolar(@QueryParam("data_cotacao") String dataCotacao) {
        validLocalDateParam(dataCotacao);

        try{
            val cotacaoDolarDia = cotacaoDolarDiaMapper.toCotacaoResponse(cotacaoDolarService.findCotacaoDolar(dataCotacao));

            LOG.info("Cotação para a Data " + dataCotacao + " retornada com Success.");

            return Response.ok(cotacaoDolarDia).build();
        }catch (BadRequestNotBusinessDayException e){
            throw new WebApplicationException(BAD_REQUEST_MESSAGE_NOT_BUSINESS_DAY, 400);
        }catch (DateEnteredGreaterThanCurrentDayException e){
            throw new WebApplicationException(DATE_ENTERED_GREATER_THAN_THE_CURRENT_DATE, 400);
        }
    }

    private void validLocalDateParam(String param){
        if(StringUtil.isNullOrEmpty(param)){
            LOG.error(BAD_REQUEST_MESSAGE);
            throw new WebApplicationException(BAD_REQUEST_MESSAGE, 400);
        }

        try{
            val date = LocalDate.parse(param, DateTimeFormatter.ofPattern(DATE_QUOTATION_FORMAT));
        }catch(DateTimeException e){
            LOG.error(BAD_REQUEST_MESSAGE);
            throw new WebApplicationException(BAD_REQUEST_MESSAGE, 400);
        }
    }
}
