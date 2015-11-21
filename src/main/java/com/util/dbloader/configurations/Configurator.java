package com.util.dbloader.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.yaml.snakeyaml.Yaml;

public class Configurator {

	private final Configuration configuration;

	public Configurator(File config) throws FileNotFoundException {
		configuration = new Yaml().loadAs(new FileInputStream(config), Configuration.class);
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
}
