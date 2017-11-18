/*
 * Copyright 2013-2014 SmartBear Software
 * Copyright 2014-2017 The TestFX Contributors
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
package org.testfx.api;

import javafx.scene.Node;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.internal.Failures;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.service.finder.NodeFinder;
import org.testfx.service.query.NodeQuery;

import static org.testfx.error.ShouldHaveChild.shouldHaveChild;
import static org.testfx.error.ShouldHaveText.shouldHaveText;

import java.util.Set;

/**
 * Assertions for JavaFX {@link javafx.scene.Node} type.
 */
public class NodeAssert extends AbstractAssert<NodeAssert, Node> {

    protected NodeAssert(Class<NodeAssert> selfType, Node actual) {
        super(actual, selfType);
    }

    protected Node getActual() {
        return actual;
    }

    /**
     * Verifies that the actual {@link javafx.scene.Node} contains the given text.
     * <p>
     * Example:
     *
     * <pre><code class='java'>
     *     Button button = FxToolkit.setupFixture(() -> new Button("Click Me"));
     *     assertThat(button).containsText("Click Me");
     * </code></pre>
     *
     * @param text the text to look for in actual {@link Node}.
     * @return this {@link NodeAssert} for assertions chaining.
     */
    public NodeAssert hasText(String text) {
        isNotNull();
        if (!(NodeMatchers.hasText(text).matches(actual))) {
            throw Failures.instance().failure(info, shouldHaveText(actual, text));
        }
        return this;
    }

    /**
     * Verifies that the actual {@link javafx.scene.Node} has the given node as
     * a child.
     * <p>
     * Example:
     *
     * <pre><code class='java'>
     *     Button button = FxToolkit.setupFixture(() -> new Button("Click Me"));
     *     assertThat(button).containsText("Click Me");
     * </code></pre>
     *
     * @param child the child to look for in actual {@link Node}.
     * @return this {@link NodeAssert} for assertions chaining.
     */
    public NodeAssert hasChild(NodeQuery childNodeQuery) {
        isNotNull();
        childNodeQuery.query()
        if (!(NodeMatchers.hasChild(childNodeQuery).matches(actual))) {
            throw Failures.instance().failure(info, shouldHaveChild(actual, child, NodeQuery.));
        }
        return this;
    }

    private static Set<Node> getChildren(String node) {
        NodeFinder nodeFinder = FxAssert.assertContext().getNodeFinder();
        NodeQuery nodeQuery = nodeFinder.lookup(node);
        return nodeQuery.lookup(node).queryAll();
    }
}
