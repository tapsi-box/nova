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

package io.spring.start.site.extension.dependency.tapsibox.fixture;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import io.spring.initializr.generator.io.template.TemplateRenderer;
import io.spring.initializr.generator.language.SourceStructure;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.contributor.ProjectContributor;

/**
 * {@link ProjectDescription} that contributes a {@code FixtureTestHelper.kt} file to the
 * generated project's test sources.
 *
 * @author Shahryar Safizadeh
 */
public class FixtureTestHelperContributor implements ProjectContributor {

	private final TemplateRenderer templateRenderer;

	private final ProjectDescription description;

	FixtureTestHelperContributor(TemplateRenderer templateRenderer, ProjectDescription description) {
		this.templateRenderer = templateRenderer;
		this.description = description;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Map<String, Object> model = Map.of("packageName", this.description.getPackageName());

		String fileContent = this.templateRenderer.render("tapsibox/FixtureTestHelper.kt", model);

		SourceStructure testSourceRoot = this.description.getBuildSystem()
			.getTestSource(projectRoot, this.description.getLanguage());

		Path output = testSourceRoot.resolveSourceFile(this.description.getPackageName().replace('.', '/'),
				"FixtureTestHelper");

		Files.createDirectories(output.getParent());
		Files.writeString(output, fileContent);
	}

}
