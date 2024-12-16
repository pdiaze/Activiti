/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
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

package org.activiti.spring.cache.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.CacheType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

@SpringBootTest(
    properties = {
        "debug=true",
        "activiti.spring.cache-manager.provider=simple",
        "activiti.spring.cache-manager.caffeine.caches.foo.enabled=true",
        "activiti.spring.cache-manager.caffeine.caches.foo.spec=initialCapacity=100, maximumSize=1000, expireAfterAccess=60s, recordStats",
        "activiti.spring.cache-manager.caffeine.caches.bar.enabled=false",
        "activiti.spring.cache-manager.caffeine.caches.bar.spec=initialCapacity=100, maximumSize=1000, expireAfterAccess=60s, recordStats",
})
public class ActivitiSpringSimpleCacheManagerTests {

    @SpringBootApplication
    static class TestApplication {}

    @Autowired(required = false)
    private CacheManager cacheManager;

    @Value("${spring.cache.type}")
    private CacheType springCacheType;

    @Test
    void testCacheManager() {
        assertThat(cacheManager)
            .isNotNull()
            .isInstanceOf(ConcurrentMapCacheManager.class);
    }

    @Test
    void testSimpleCacheManager() {
        assertThat(cacheManager.getCacheNames()).containsExactly("foo");
    }

    @Test
    void testCaches() {
        var cache = cacheManager.getCache("foo");

        assertThat(cache).isInstanceOf(ConcurrentMapCache.class);
    }


    @Test
    void testAllowNullValues() {
        assertThat(ConcurrentMapCacheManager.class.cast(cacheManager).isAllowNullValues()).isTrue();
    }

    @Test
    void springCacheType() {
        assertThat(springCacheType).isEqualTo(CacheType.SIMPLE);
    }

}
