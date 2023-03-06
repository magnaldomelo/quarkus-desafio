package coopersystem.challenge.infrastructure.api;

import coopersystem.challenge.domain.model.external.CotacaoDolarAPI.CotacaoDolarDiaResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@RegisterRestClient(configKey = "bcb-client")
public interface CotacaoDolarAPI {

    @GET
    @Path("/dolar_cotacao")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CotacaoDolarDiaResponse findCotacaoDolar(@QueryParam("data_cotacao") String dataCotacao);
}
