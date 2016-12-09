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
package org.testfx;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.testfx.Assertions.assertThat;

/**
 * Tests for NodeAssert.
 */
public class NodeAssertTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @Test
    public void node_shouldHaveText() throws Exception {
        // given:
        Button button = FxToolkit.setupFixture(() -> new Button("foo"));

        // expect:
        assertThat(button).hasText("foo");
        assertThatThrownBy(() -> assertThat(button).hasText("bar")).isInstanceOf(AssertionError.class)
                .hasMessage(format("%nExpecting:%n <\"javafx.scene.control.Button\">%nto have text:%n " +
                        "<\"bar\">%n but was: <\"foo\">%n"));
    }
}
