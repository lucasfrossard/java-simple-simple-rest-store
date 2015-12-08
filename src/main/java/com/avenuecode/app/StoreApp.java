package com.avenuecode.app;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("store")
public class StoreApp extends ResourceConfig {
	public StoreApp() {
		packages("com.avenuecode.app.rws");
	}
}
