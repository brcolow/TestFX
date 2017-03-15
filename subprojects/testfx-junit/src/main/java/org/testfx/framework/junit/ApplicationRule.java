/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2015 The TestFX Contributors
 *
 * Licensed under the EUPL, Version 1.1 or - as soon they will be approved by the
 * European Commission - subsequent versions of the EUPL (the "Licence"); You may
 * not use this work except in compliance with the Licence.
 *
 * You may obtain a copy of the Licence at:
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the Licence is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the Licence for the
 * specific language governing permissions and limitations under the Licence.
 */
package org.testfx.framework.junit;

import java.awt.image.BufferedImage;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

public class ApplicationRule extends FxRobot implements ApplicationFixture, TestRule {

    private final Consumer<Stage> start;

    public ApplicationRule(Consumer<Stage> start) {
        this.start = start;
    }

    @Override
    public void init() throws Exception {
        // do nothing
    }

    @Override
    public void start(Stage stage) throws Exception {
        start.accept(stage);
    }

    @Override
    public void stop() throws Exception {
        // do nothing
    }

    /**
     * Invoked when a test succeeds.
     */
    protected void succeeded(Description description) {
        // do nothing
    }

    /**
     * Invoked when a test fails.
     */
    protected void failed(Throwable e, Description description) throws Throwable {
        DateTimeFormatter errorFormatter = DateTimeFormatter.ofPattern(System.getProperty(
                "testfx.capture.datetimeformat", "yyyyMMdd.HHmmss.SSS"));
        Rectangle2D primaryScreenRegion = Screen.getPrimary().getBounds();
        ZonedDateTime errorDateTime = ZonedDateTime.now();
        saveCapture(primaryScreenRegion, Paths.get(description.getDisplayName() + "-" +
                errorFormatter.format(errorDateTime) + ".png"));
        throw e;
    }

    /**
     * Invoked when a test is skipped due to a failed assumption.
     */
    protected void skipped(AssumptionViolatedException e, Description description) {
        // do nothing
    }

    /**
     * Invoked when a test method finishes (whether passing or failing).
     */
    protected void finished(Description description) {
        // do nothing
    }

    private void before() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> new ApplicationAdapter(this));
    }

    private void after() throws Exception {
        FxToolkit.cleanupApplication(new ApplicationAdapter(this));
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                before();
                try {
                    base.evaluate();
                    succeeded(description);
                } catch (AssumptionViolatedException  e) {
                    skipped(e, description);
                } catch (Throwable e) {
                    failed(e, description);
                } finally {
                    finished(description);
                    after();
                }
            }
        };
    }

}
