/*
 * Copyright 2018 the original author or authors.
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

package de.codecentric.spring.boot.chaos.monkey;

import de.codecentric.spring.boot.demo.chaos.monkey.ChaosDemoApplication;
import de.codecentric.spring.boot.chaos.monkey.component.ChaosMonkey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Benjamin Wilms
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChaosDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test-default-profile.properties")
public class ChaosDemoApplicationDefaultProfileTest {

    @Autowired(required = false)
    private ChaosMonkey chaosMonkey;

    @Autowired
    private Environment env;

    @Test
    public void contextLoads() {

        assertNull(chaosMonkey);
    }

    @Test
    public void checkEnvWatcherController() {
        assertThat(env.getProperty("chaos.monkey.watcher.controller"),is("true"));
    }

    @Test
    public void checkEnvAssaultLatencyRangeStart() {
        assertThat(env.getProperty("chaos.monkey.assaults.latency-range-start"),is("100"));
    }

    @Test
    public void checkEnvAssaultLatencyRangeEnd() {
        assertThat(env.getProperty("chaos.monkey.assaults.latency-range-end"),is("200"));
    }


}