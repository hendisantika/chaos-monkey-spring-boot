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

package de.codecentric.spring.boot.chaos.monkey.assaults;

import de.codecentric.spring.boot.chaos.monkey.configuration.ChaosMonkeySettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author Thorsten Deelmann
 */
public class KillAppAssault implements ChaosMonkeyAssault{

    private static final Logger LOGGER = LoggerFactory.getLogger(KillAppAssault.class);
    @Autowired
    private ApplicationContext context;

    private ChaosMonkeySettings settings;

    public KillAppAssault(ChaosMonkeySettings settings) {
        this.settings = settings;
    }

    @Override
    public boolean isActive() {
        return settings.getAssaultProperties().isKillApplicationActive();
    }

    @Override
    public void attack() {
        try {
            LOGGER.info("Chaos Monkey - I am killing your Application!");

            int exit = SpringApplication.exit(context, new ExitCodeGenerator() {
                public int getExitCode() {
                    return 0;
                }
            });
            System.exit(exit);
        } catch (Exception e) {
            LOGGER.info("Chaos Monkey - Unable to kill the App, I am not the BOSS!");
        }
    }
}
