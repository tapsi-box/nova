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

import io.spring.initializr.generator.buildsystem.MavenRepository;
import io.spring.initializr.generator.buildsystem.gradle.KotlinDslGradleBuildWriter;

/**
 * Custom Gradle build writer that adds credentials to Tapsi repositories.
 *
 * @author Shahryar Safizadeh
 */
public class TapsiBoxKotlinDslGradleBuildWriter extends KotlinDslGradleBuildWriter {

	@Override
	protected String repositoryAsString(MavenRepository repository) {
		if (repository.getUrl().contains("artifactory.tapsi.tech")) {
			return "maven {\n" + "        url = uri(\"" + repository.getUrl() + "\")\n" + "        credentials {\n"
					+ "            username = project.findProperty(\"tapsiArtifactoryUsername\") as String?\n"
					+ "            password = project.findProperty(\"tapsiArtifactoryPassword\") as String?\n"
					+ "        }\n" + "    }";
		}
		return super.repositoryAsString(repository);
	}

}
