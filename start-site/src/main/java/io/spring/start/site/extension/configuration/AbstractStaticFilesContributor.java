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

package io.spring.start.site.extension.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * Base implementation for copying static resource files to generated projects.
 *
 * @author Shahryar Safizadeh
 */
public abstract class AbstractStaticFilesContributor implements ProjectContributor {

	private static final String RESOURCE_BASE_PATH = "/project/static/";

	@Override
	public void contribute(Path projectRoot) throws IOException {
		for (Map.Entry<String, String> entry : getResourceMappings().entrySet()) {
			copyResource(projectRoot, entry.getKey(), entry.getValue());
		}
	}

	protected abstract Map<String, String> getResourceMappings();

	private void copyResource(Path projectRoot, String source, String target) throws IOException {
		String resourcePath = RESOURCE_BASE_PATH + source;
		Path targetPath = projectRoot.resolve(target);

		Files.createDirectories(targetPath.getParent());

		try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
			if (in == null) {
				return;
			}
			Files.copy(in, targetPath);
		}
	}

}
