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

package io.spring.start.site.extension.dependency.tapsibox.libs.scheduler;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.buildsystem.DependencyScope;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.version.VersionReference;

import org.springframework.context.annotation.Bean;

/**
 * Project generation configuration for customizing dependencies when the
 * {@code tapsi-scheduler} dependency is requested.
 * <p>
 * Adjusts transitive dependencies, excludes default Quartz, and ensures required MongoDB
 * and Quartz-related libraries are present.
 *
 * @author Shahryar Safizadeh
 */
@ProjectGenerationConfiguration
public class TapsiBoxSchedulerProjectGenerationConfiguration {

	@Bean
	@ConditionalOnRequestedDependency("tapsi-scheduler")
	BuildCustomizer<Build> tapsiBoxSchedulerBuildCustomizer() {
		return (build) -> {
			build.dependencies()
				.get("tapsi-scheduler")
				.getExclusions()
				.add(new Dependency.Exclusion("org.quartz-scheduler", "quartz"));

			if (!build.dependencies().has("spring-boot-starter-data-mongodb-reactive")) {
				build.dependencies()
					.add("spring-boot-starter-data-mongodb-reactive",
							Dependency
								.withCoordinates("org.springframework.boot",
										"spring-boot-starter-data-mongodb-reactive")
								.scope(DependencyScope.COMPILE));
			}

			if (!build.dependencies().has("quartz")) {
				build.dependencies()
					.add("quartz",
							Dependency.withCoordinates("org.quartz-scheduler", "quartz")
								.version(VersionReference.ofValue("2.3.2"))
								.scope(DependencyScope.COMPILE));
			}

			if (!build.dependencies().has("quartz-mongodb")) {
				build.dependencies()
					.add("quartz-mongodb",
							Dependency.withCoordinates("com.novemberain", "quartz-mongodb")
								.version(VersionReference.ofValue("2.2.0-rc2"))
								.scope(DependencyScope.COMPILE));
			}
		};
	}

}
