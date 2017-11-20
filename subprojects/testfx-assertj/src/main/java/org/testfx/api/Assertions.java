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

import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * The entry point for all TestFX assertions.
 */
public class Assertions extends org.assertj.core.api.Assertions {
    protected Assertions() {}

    public static NodeAssert assertThat(Node node) {
        return new NodeAssert(node, NodeAssert.class);
    }

    public static RegionAssert assertThat(Region region) {
        return new RegionAssert(region, RegionAssert.class);
    }

    public static StyleableAssert assertThat(Styleable styleable) {
        return new StyleableAssert(styleable, StyleableAssert.class);
    }
}

