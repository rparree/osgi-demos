package demo.blueprint;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

import java.util.Dictionary;

/**
 * todo
 */
public class HttpAppTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/config.xml";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected String useOverridePropertiesWithConfigAdmin(Dictionary props) throws Exception {
        props.put("uriSystemA", "mock:a");
        props.put("ep", "jetty:http://localhost:9092/test-service");
        return "camel-demo";
    }

    @Test
    public void testInlinedRoute() throws InterruptedException {
        MockEndpoint mockEndpoint = getMockEndpoint("mock:a");
        mockEndpoint.expectedBodiesReceived("jennifer@work.com");
        mockEndpoint.expectedMessageCount(1);

        template.sendBody("jetty:http://localhost:9090/service", "jennifer");

        assertMockEndpointsSatisfied();

    }


    @Test
    public void smokeTestRouteBuilderRouteUsesOverriddenEndpoint() throws InterruptedException {
        template.sendBody("jetty:http://localhost:9092/test-service", "jennifer");
    }

    @Test(expected = CamelExecutionException.class)
    public void smokeTestRouteBuilderRouteDoesNotUseDefault() {
        template.sendBody("jetty:http://localhost:9091/service", "jennifer");
    }


}
