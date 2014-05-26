package demo.blueprint.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;


/**
 * todo
 */
public class MyRoute extends RouteBuilder {
    
    @EndpointInject (ref="fromEndpoint")
    Endpoint ep;
    
    @Override
    public void configure() throws Exception {
        from(ep).log("received");
    }
}
