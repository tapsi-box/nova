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

package io.spring.start.site.extension.code.kotlin;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.spring.build.BuildMetadataResolver;
import io.spring.initializr.metadata.InitializrMetadata;

/**
 * A {@link BuildCustomizer} that automatically adds Jackson Kotlin extensions when a
 * dependency with the {@code web} facet is selected.
 *
 * @author Shahryar Safizadeh
 */
public class JacksonKotlinExtensionsCustomizer implements BuildCustomizer<Build> {

	private final BuildMetadataResolver buildResolver;

	public JacksonKotlinExtensionsCustomizer(InitializrMetadata metadata, ProjectDescription description) {
		this.buildResolver = new BuildMetadataResolver(metadata, description.getPlatformVersion());
	}

	@Override
	public void customize(Build build) {
		if (this.buildResolver.hasFacet(build, "web")) {
			build.dependencies()
				.add("jackson-datatype-jsr310",
						Dependency.withCoordinates("com.fasterxml.jackson.datatype", "jackson-datatype-jsr310"));

			build.dependencies()
				.add("jackson-module-kotlin",
						Dependency.withCoordinates("com.fasterxml.jackson.module", "jackson-module-kotlin"));
		}
	}

}
