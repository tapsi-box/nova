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

import io.spring.initializr.generator.condition.ConditionalOnRequestedDependency;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.start.site.extension.configuration.detekt.DetektStaticFilesContributor;
import io.spring.start.site.extension.configuration.elasticsearch.ElasticsearchStaticFilesContributor;
import io.spring.start.site.extension.configuration.envoy.EnvoyStaticFilesContributor;
import io.spring.start.site.extension.configuration.kafka.KafkaStaticFilesContributor;
import io.spring.start.site.extension.configuration.mongo.MongoStaticFilesContributor;
import io.spring.start.site.extension.configuration.prometheus.PrometheusStaticFilesContributor;
import io.spring.start.site.extension.configuration.redis.RedisStaticFilesContributor;

import org.springframework.context.annotation.Bean;

/**
 * Project generation configuration for TapsiBox static file contributors.
 *
 * @author Shahryar Safizadeh
 */
@ProjectGenerationConfiguration
public class TapsiBoxConfigProjectGenerationConfiguration {

	@Bean
	DetektStaticFilesContributor detektStaticFilesContributor() {
		return new DetektStaticFilesContributor();
	}

	@Bean
	@ConditionalOnRequestedDependency("protobuf-util")
	EnvoyStaticFilesContributor envoyStaticFilesContributor() {
		return new EnvoyStaticFilesContributor();
	}

	@Bean
	@ConditionalOnRequestedDependency("data-mongodb-reactive")
	MongoStaticFilesContributor mongoStaticFilesContributor() {
		return new MongoStaticFilesContributor();
	}

	@Bean
	@ConditionalOnRequestedDependency("data-elasticsearch")
	ElasticsearchStaticFilesContributor elasticsearchStaticFilesContributor() {
		return new ElasticsearchStaticFilesContributor();
	}

	@Bean
	@ConditionalOnRequestedDependency("tapsi-kafka-producer")
	KafkaStaticFilesContributor kafkaStaticFilesContributor() {
		return new KafkaStaticFilesContributor();
	}

	@Bean
	@ConditionalOnRequestedDependency("prometheus")
	PrometheusStaticFilesContributor prometheusStaticFilesContributor() {
		return new PrometheusStaticFilesContributor();
	}

	@Bean
	@ConditionalOnRequestedDependency("redisson")
	RedisStaticFilesContributor redisStaticFilesContributor() {
		return new RedisStaticFilesContributor();
	}

}
