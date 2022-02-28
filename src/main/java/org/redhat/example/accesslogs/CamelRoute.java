package org.redhat.example.accesslogs;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.auto).component("servlet").host("localhost").port(8080).contextPath("/");

        rest("/greetings")
                .consumes("application/json")
                .produces("application/json")
                .get("/").to("direct:log");

        from("direct:log")
                .log("Hello World")
                .setBody(constant("Hello World"))
                .end();
    }
}
