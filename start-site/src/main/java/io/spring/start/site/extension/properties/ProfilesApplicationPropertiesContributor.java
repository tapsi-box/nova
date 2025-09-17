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
 * {@link ProjectContributor} to add default application properties files for the main
 * Tapsi Box profiles (Local, Prod, Dev) to the generated project.
 *
 * @author Shahryar Safizadeh
 */
public class ProfilesApplicationPropertiesContributor implements ProjectContributor {

	@Override
	public void contribute(Path projectRoot) throws IOException {
		createPropertiesFile(projectRoot, "Local");
		createPropertiesFile(projectRoot, "Prod");
		createPropertiesFile(projectRoot, "Dev");
	}

	private void createPropertiesFile(Path projectRoot, String profile) throws IOException {
		Path targetPath = projectRoot
			.resolve("src/main/resources/application-" + profile.toLowerCase() + ".properties");

		Files.createDirectories(targetPath.getParent());

		Files.write(targetPath, getDefaultContent(profile).getBytes(), StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING);
	}

	private String getDefaultContent(String profile) {
		return "# " + profile + " Profile Properties\n";
	}

}
