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
package org.testfx.error;

import javafx.scene.control.Labeled;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.core.internal.ComparisonStrategy;
import org.assertj.core.internal.StandardComparisonStrategy;

/**
 * Creates an error message indicating that an assertion that verifies that a {@link javafx.scene.Node} has the given
 * text failed.
 */
public class ShouldHaveText extends BasicErrorMessageFactory {

    public static ErrorMessageFactory shouldHaveText(Object actual, Object other) {
        return new ShouldHaveText(actual, other, StandardComparisonStrategy.instance());
    }

    private ShouldHaveText(Object actual, Object other, ComparisonStrategy comparisonStrategy) {
        super("%nExpecting:%n <%s>%nto have text:%n <%s>%n but was: <%s>%n%s",
                actual.getClass().getName(),  other, actual instanceof Labeled ? ((Labeled) actual).getText() : actual,
                comparisonStrategy);
    }
}
