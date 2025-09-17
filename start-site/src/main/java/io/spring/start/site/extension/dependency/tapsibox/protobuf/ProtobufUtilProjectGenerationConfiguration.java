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

package io.spring.start.site.extension.dependency.tapsibox.protobuf;

import io.spring.initializr.generator.buildsystem.gradle.GradleBuild;
import io.spring.initializr.generator.buildsystem.gradle.GradleBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnBuildSystem;
import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;

import org.springframework.context.annotation.Bean;

/**
 * Project generation configuration for adding the gRPC Spring Boot Gradle plugin when the
 * {@code protobuf-util} dependency is requested.
 *
 * @author Shahryar Safizadeh
 */
@ProjectGenerationConfiguration
public class ProtobufUtilProjectGenerationConfiguration {

	@Bean
	@ConditionalOnRequestedDependency("protobuf-util")
	@ConditionalOnBuildSystem(GradleBuildSystem.ID)
	BuildCustomizer<GradleBuild> protobufUtilBuildCustomizer() {
		return (build) -> build.plugins()
			.add("io.github.lognet.grpc-spring-boot", (plugin) -> plugin.setVersion("5.2.0"));
	}

}
