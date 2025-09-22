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

package io.spring.start.site.extension.dependency.tapsibox.convention;

import io.spring.initializr.generator.buildsystem.gradle.GradleBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.language.kotlin.KotlinLanguage;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;

import org.springframework.context.annotation.Bean;

/**
 * {@link ProjectGenerationConfiguration} for generation of projects that depend on Tapsi
 * Box Convention plugin.
 *
 * @author Shahryar Safizadeh
 */
@ProjectGenerationConfiguration
public class TapsiBoxConventionProjectGenerationConfiguration {

	private final String conventionVersion;

	TapsiBoxConventionProjectGenerationConfiguration(ProjectDescription description) {
		this.conventionVersion = TapsiBoxConventionVersionResolver.resolve(description.getBuildSystem());
	}

	@Bean
	@ConditionalOnLanguage(KotlinLanguage.ID)
	@ConditionalOnBuildSystem(GradleBuildSystem.ID)
	TapsiBoxConventionGradleBuildCustomizer tapsiBoxConventionGradleBuildCustomizer() {
		return new TapsiBoxConventionGradleBuildCustomizer(this.conventionVersion);
	}

}
