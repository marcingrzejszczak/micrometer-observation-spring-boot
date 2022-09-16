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

package io.micrometer.spring.actuate.autoconfigure.tracing.zipkin;

import java.util.List;

import zipkin2.Call;
import zipkin2.Callback;
import zipkin2.codec.Encoding;
import zipkin2.reporter.Sender;

class NoopSender extends Sender {

	@Override
	public Encoding encoding() {
		return Encoding.JSON;
	}

	@Override
	public int messageMaxBytes() {
		return 1024;
	}

	@Override
	public int messageSizeInBytes(List<byte[]> encodedSpans) {
		return encoding().listSizeInBytes(encodedSpans);
	}

	@Override
	public Call<Void> sendSpans(List<byte[]> encodedSpans) {
		return new Call.Base<>() {
			@Override
			public Call<Void> clone() {
				return this;
			}

			@Override
			protected Void doExecute() {
				return null;
			}

			@Override
			protected void doEnqueue(Callback<Void> callback) {
			}
		};
	}

}
