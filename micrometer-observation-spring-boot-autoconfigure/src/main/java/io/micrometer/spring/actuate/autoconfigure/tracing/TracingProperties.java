/*
 * Copyright 2012-2022 the original author or authors.
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

package io.micrometer.spring.actuate.autoconfigure.tracing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for tracing.
 *
 * @author Moritz Halbritter
 * @since 3.0.0
 */
@ConfigurationProperties("management.tracing")
public class TracingProperties {

	/**
	 * Sampling configuration.
	 */
	private final Sampling sampling = new Sampling();

	/**
	 * Baggage configuration.
	 */
	private final Baggage baggage = new Baggage();

	/**
	 * Propagation configuration.
	 */
	private final Propagation propagation = new Propagation();

	public Sampling getSampling() {
		return this.sampling;
	}

	public Baggage getBaggage() {
		return this.baggage;
	}

	public Propagation getPropagation() {
		return this.propagation;
	}

	public static class Sampling {

		/**
		 * Probability in the range from 0.0 to 1.0 that a trace will be sampled.
		 */
		private float probability = 0.10f;

		public float getProbability() {
			return this.probability;
		}

		public void setProbability(float probability) {
			this.probability = probability;
		}

	}

	public static class Baggage {

		/**
		 * Whether to enable correlation of the baggage context with logging contexts.
		 */
		private boolean correlationEnabled = true;

		/**
		 * List of fields that should be propagated over the wire.
		 */
		private List<String> correlationFields = new ArrayList<>();

		/**
		 * List of fields that should be accessible within the JVM process but not
		 * propagated over the wire.
		 */
		private List<String> localFields = new ArrayList<>();

		/**
		 * List of fields that are referenced the same in-process as it is on the wire.
		 * For example, the field "x-vcap-request-id" would be set as-is including the
		 * prefix.
		 */
		private List<String> remoteFields = new ArrayList<>();

		/**
		 * List of fields that should automatically become tags.
		 */
		private List<String> tagFields = new ArrayList<>();

		public boolean isCorrelationEnabled() {
			return this.correlationEnabled;
		}

		public void setCorrelationEnabled(boolean correlationEnabled) {
			this.correlationEnabled = correlationEnabled;
		}

		public List<String> getCorrelationFields() {
			return this.correlationFields;
		}

		public void setCorrelationFields(List<String> correlationFields) {
			this.correlationFields = correlationFields;
		}

		public List<String> getLocalFields() {
			return this.localFields;
		}

		public void setLocalFields(List<String> localFields) {
			this.localFields = localFields;
		}

		public List<String> getRemoteFields() {
			return this.remoteFields;
		}

		public void setRemoteFields(List<String> remoteFields) {
			this.remoteFields = remoteFields;
		}

		public List<String> getTagFields() {
			return this.tagFields;
		}

		public void setTagFields(List<String> tagFields) {
			this.tagFields = tagFields;
		}

	}

	public static class Propagation {

		/**
		 * Tracing context propagation types.
		 */
		private PropagationType type = PropagationType.W3C;

		public PropagationType getType() {
			return this.type;
		}

		public void setType(PropagationType type) {
			this.type = type;
		}

		enum PropagationType {

			/**
			 * AWS propagation type.
			 */
			AWS,

			/**
			 * B3 propagation type.
			 */
			B3,

			/**
			 * W3C propagation type.
			 */
			W3C,

			/**
			 * Custom propagation type. If picked, requires bean registration overriding
			 * the default propagation mechanisms.
			 */
			CUSTOM

		}

	}

}
