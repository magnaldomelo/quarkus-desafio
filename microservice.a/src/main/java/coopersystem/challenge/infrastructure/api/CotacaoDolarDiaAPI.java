package coopersystem.challenge.infrastructure.api;


import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaRequest;
import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@RegisterRestClient(configKey = "bcb-client")
public interface CotacaoDolarDiaAPI {

    @GET
    @Path("CotacaoDolarDia(dataCotacao=@dataCotacao)")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    CotacaoDiaResponse getCotacaoDolarDia(@BeanParam CotacaoDiaRequest cotacaoDiaRequest);
}
