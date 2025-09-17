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

package io.spring.start.site.extension.properties;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * {@link ProjectContributor} to add Gradle properties with artifactory credentials to
 * project.
 *
 * @author Shahryar Safizadeh
 */
public class GradlePropertiesProjectContributor implements ProjectContributor {

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path gradleProperties = projectRoot.resolve("gradle.properties");
		if (!Files.exists(gradleProperties)) {
			Files.createFile(gradleProperties);
		}

		String propertiesContent = "# Tapsi Artifactory Credentials" + System.lineSeparator()
				+ "# Provide your username and password for Tapsi's private repository." + System.lineSeparator()
				+ "tapsiArtifactoryUsername=YOUR_USERNAME" + System.lineSeparator()
				+ "tapsiArtifactoryPassword=YOUR_PASSWORD" + System.lineSeparator();

		Files.write(gradleProperties, propertiesContent.getBytes(), StandardOpenOption.APPEND);
	}

}
