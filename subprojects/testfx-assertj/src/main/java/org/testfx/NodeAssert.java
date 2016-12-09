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

import javafx.scene.Node;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.error.ShouldHaveText.shouldHaveText;

/**
 * Assertions for JavaFX {@link Node} type.
 */
public class NodeAssert extends AbstractAssert<NodeAssert, Node> {

    protected NodeAssert(Class<NodeAssert> selfType, Node actual) {
        super(actual, selfType);
    }

    protected Node getActual() {
        return actual;
    }

    public NodeAssert hasText(String text) {
        isNotNull();
        if (!(NodeMatchers.hasText(text).matches(actual))) {
            throw Failures.instance().failure(info, shouldHaveText(actual, text));
        }
        return this;
    }
}
