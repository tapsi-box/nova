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

package io.spring.start.site.extension.dependency.tapsibox.mockito;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.buildsystem.DependencyScope;
import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.language.kotlin.KotlinLanguage;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.version.VersionReference;

import org.springframework.context.annotation.Bean;

/**
 * Project generation configuration for adding the Mockito-Kotlin dependency to
 * Kotlin-based projects.
 *
 * @author Shahryar Safizadeh
 */
@ProjectGenerationConfiguration
public class MockitoKotlinProjectGenerationConfiguration {

	@Bean
	@ConditionalOnLanguage(KotlinLanguage.ID)
	BuildCustomizer<Build> mockitoKotlinBuildCustomizer() {
		return (build) -> build.dependencies()
			.add("mockito",
					Dependency.withCoordinates("org.mockito.kotlin", "mockito-kotlin")
						.version(VersionReference.ofValue("5.2.1"))
						.scope(DependencyScope.TEST_COMPILE)
						.build());
	}

}
