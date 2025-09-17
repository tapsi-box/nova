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

package io.spring.start.site.extension.build.gradle;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.MavenRepository;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

import org.springframework.core.Ordered;

/**
 * {@link BuildCustomizer} that configures Tapsi Artifactory repositories for Gradle
 * builds.
 * <p>
 * This customizer removes the default Maven Central repository and adds the Tapsi
 * Doroshke Gradle repository to ensure all dependencies are resolved through Tapsi's
 * internal Artifactory instance.
 *
 * @author Shahryar Safizadeh
 */
public class TapsiBoxGradleBuildCustomizer implements BuildCustomizer<Build> {

	@Override
	public void customize(Build build) {
		build.repositories().remove("maven-central");

		build.repositories()
			.add(MavenRepository
				.withIdAndUrl("doroshke-gradle", "https://artifactory.tapsi.tech/artifactory/doroshke-gradle")
				.build());
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE - 20;
	}

}
