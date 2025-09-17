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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import org.springframework.core.Ordered;

/**
 * {@link ProjectContributor} that configures Tapsi Artifactory for Gradle plugin
 * repositories and removes gradlePluginPortal.
 *
 * @author Shahryar Safizadeh
 */
public class TapsiBoxSettingsGradleProjectContributor implements ProjectContributor {

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path settingsGradle = projectRoot.resolve("settings.gradle.kts");
		if (!Files.exists(settingsGradle)) {
			settingsGradle = projectRoot.resolve("settings.gradle");
		}
		if (!Files.exists(settingsGradle)) {
			return;
		}

		String content = Files.readString(settingsGradle);
		String credentialsBlock = "maven {\n"
				+ "            url = uri(\"https://artifactory.tapsi.tech:443/artifactory/gradle-plugins\")\n"
				+ "            credentials {\n"
				+ "                username = settings.providers.gradleProperty(\"tapsiArtifactoryUsername\").orNull\n"
				+ "                password = settings.providers.gradleProperty(\"tapsiArtifactoryPassword\").orNull\n"
				+ "            }\n" + "        }";

		// First, replace the maven repository with credentials
		Pattern mavenPattern = Pattern.compile(
				"maven\\s*\\{\\s*url\\s*=\\s*uri\\(\\s*\"https://artifactory\\.tapsi\\.tech:443/artifactory/gradle-plugins\"\\s*\\)\\s*\\}",
				Pattern.DOTALL);
		String newContent = mavenPattern.matcher(content).replaceAll(Matcher.quoteReplacement(credentialsBlock));

		// Then, remove gradlePluginPortal() line
		Pattern gradlePortalPattern = Pattern.compile("\\s*gradlePluginPortal\\(\\)", Pattern.MULTILINE);
		newContent = gradlePortalPattern.matcher(newContent).replaceAll("");

		if (!newContent.equals(content)) {
			Files.writeString(settingsGradle, newContent, StandardOpenOption.WRITE,
					StandardOpenOption.TRUNCATE_EXISTING);
		}
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE - 10;
	}

}
