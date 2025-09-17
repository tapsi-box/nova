/*
 * Copyright 2012 - present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site;

import io.spring.initializr.metadata.InitializrMetadataBuilder;
import io.spring.initializr.metadata.InitializrProperties;
import io.spring.initializr.web.support.DefaultInitializrMetadataProvider;
import io.spring.initializr.web.support.InitializrMetadataUpdateStrategy;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing the Spring Initializr metadata provider with
 * TapsiBox-specific properties.
 *
 * @author Shahryar Safizadeh
 */
@Configuration
@EnableConfigurationProperties(TapsiBoxInitializrConfiguration.TapsiBoxInitializrProperties.class)
public class TapsiBoxInitializrConfiguration {

	@Bean
	public DefaultInitializrMetadataProvider tapsiBoxInitializrMetadataProvider(
			InitializrProperties initializrProperties, TapsiBoxInitializrProperties tapsiBoxInitializrProperties,
			InitializrMetadataUpdateStrategy initializrMetadataUpdateStrategy) {

		return new DefaultInitializrMetadataProvider(
				InitializrMetadataBuilder.fromInitializrProperties(tapsiBoxInitializrProperties.getInitializr())
					.withInitializrProperties(initializrProperties, true)
					.build(),
				initializrMetadataUpdateStrategy);
	}

	@ConfigurationProperties("tapsibox")
	public static class TapsiBoxInitializrProperties {

		@NestedConfigurationProperty
		private InitializrProperties initializr = new InitializrProperties();

		public InitializrProperties getInitializr() {
			return this.initializr;
		}

		public void setInitializr(InitializrProperties initializr) {
			this.initializr = initializr;
		}

	}

}
