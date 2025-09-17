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

package io.spring.start.site.extension.dependency.tapsibox.mongock;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.spring.build.BuildMetadataResolver;
import io.spring.initializr.metadata.InitializrMetadata;

import org.springframework.context.annotation.Bean;

/**
 * Project generation configuration for adding the Mongock reactive MongoDB driver when
 * the {@code mongock} dependency is requested.
 *
 * @author Shahryar Safizadeh
 */
@ProjectGenerationConfiguration
public class MongockProjectGenerationConfiguration {

	@Bean
	@ConditionalOnRequestedDependency("mongock")
	public BuildCustomizer<Build> addMongockReactiveDriverCustomizer(InitializrMetadata metadata,
			ProjectDescription description) {
		BuildMetadataResolver buildResolver = new BuildMetadataResolver(metadata, description.getPlatformVersion());

		return (build) -> {
			if (buildResolver.hasFacet(build, "reactive")) {
				build.dependencies()
					.add("mongock-mongodb-reactive-driver",
							Dependency.withCoordinates("io.mongock", "mongodb-reactive-driver"));
			}
		};
	}

}
