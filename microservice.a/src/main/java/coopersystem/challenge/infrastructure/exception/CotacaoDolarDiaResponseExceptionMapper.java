package coopersystem.challenge.infrastructure.exception;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import javax.ws.rs.core.Response;

public class CotacaoDolarDiaResponseExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {
        if(response.getStatus()==400) {
            return  new CotacaoDolarDiaNotFoundException("Erro ao consultar cotação do Dolar:" + response.readEntity(String.class));
        }

        return null;
    }
}
