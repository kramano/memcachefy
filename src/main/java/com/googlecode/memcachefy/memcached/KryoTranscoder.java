/*
 * Copyright 2012 neppo.com.br. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.memcachefy.memcached;

import com.esotericsoftware.kryo.Kryo;
import net.spy.memcached.CachedData;
import net.spy.memcached.transcoders.Transcoder;
import org.apache.log4j.Logger;

/**
 * KryoTranscoder transcode and wrap the user data into memcached cache data
 * @param <T>
 */
public class KryoTranscoder<T> extends KryoSerializer<T> implements Transcoder<T> {

	private static final Logger log = Logger.getLogger(KryoTranscoder.class);

	public static final int COMPRESSED = 2;
	public static final int KRYO = 4;

	public KryoTranscoder(Kryo kryo) {
		super(kryo);
	}

	@Override
	public T decode(CachedData data) {
		if (data == null || (data.getFlags() & KRYO) == 0) {
			log.error("Illegal data, don't know how to decode");
			return null;
		}
		byte[] input = data.getData();
		boolean isCompressed = (data.getFlags() & COMPRESSED) != 0;
		final byte[] unzippedData;
		if (isCompressed) {
			unzippedData = unzip(input);
		} else {
			unzippedData = input;
		}

		return decodeBytes(unzippedData);
	}

	@Override
	public CachedData encode(T object) {
		byte[] data = encodeBytes(object);
		if (data == null || data.length <= 0) {
			return null;
		} else {
			boolean compress = shouldCompress(data);
			if (compress) {
				data = zip(data);
			}

			int flags = 0;
			flags |= KRYO;
			if (compress) {
				flags |= COMPRESSED;
			}
			return new CachedData(flags, data, data.length);
		}
	}

	@Override
	public int getMaxSize() {
		return CachedData.MAX_SIZE;
	}

	@Override
	public boolean asyncDecode(CachedData data) {
		return (data.getFlags() & KRYO) != 0;
	}
}
