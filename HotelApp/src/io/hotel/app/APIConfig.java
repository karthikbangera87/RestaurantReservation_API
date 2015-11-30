package io.hotel.app;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/api")
public class APIConfig extends ResourceConfig {

	public APIConfig() {
		packages("io.hotel.rest");
		
		register(io.swagger.jaxrs.listing.ApiListingResource.class);
		register(io.swagger.jaxrs.listing.SwaggerSerializers.class);
		
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("1.0.0");
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setBasePath("/HotelApp/api");
		beanConfig.setResourcePackage("io.hotel");
		beanConfig.setDescription("REST API for io.hotel");
		beanConfig.setScan(true);
		beanConfig.setTitle("io.hotel");
		
	}
}

