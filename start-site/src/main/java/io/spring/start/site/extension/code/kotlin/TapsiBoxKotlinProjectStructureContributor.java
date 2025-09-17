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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * {@link ProjectContributor} that creates TapsiBox's standard project structure.
 *
 * @author Shahryar Safizadeh
 */
public class TapsiBoxKotlinProjectStructureContributor implements ProjectContributor {

	private final ProjectDescription description;

	public TapsiBoxKotlinProjectStructureContributor(ProjectDescription description) {
		this.description = description;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		String language = this.description.getLanguage().id();
		String basePackagePath = this.description.getPackageName().replace('.', '/');

		Path mainSource = projectRoot.resolve("src/main/" + language + "/" + basePackagePath);
		Files.createDirectories(mainSource);

		Files.createDirectories(mainSource.resolve("application"));
		Files.createDirectories(mainSource.resolve("domain"));
		Files.createDirectories(mainSource.resolve("infrastructure"));

		Path testSource = projectRoot.resolve("src/test/" + language + "/" + basePackagePath);
		Files.createDirectories(testSource);

		Files.createDirectories(testSource.resolve("application"));
		Files.createDirectories(testSource.resolve("domain"));
		Files.createDirectories(testSource.resolve("infrastructure"));
	}

}
