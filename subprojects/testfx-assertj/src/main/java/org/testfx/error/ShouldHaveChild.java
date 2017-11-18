package org.testfx.error;


import javafx.scene.control.Labeled;

import org.assertj.core.error.BasicErrorMessageFactory;
import org.assertj.core.error.ErrorMessageFactory;
import org.assertj.core.internal.ComparisonStrategy;
import org.assertj.core.internal.StandardComparisonStrategy;

public class ShouldHaveChild extends BasicErrorMessageFactory
{
    public static ErrorMessageFactory shouldHaveChild(Object actual, Object other, Object children) {
        return new ShouldHaveChild(actual, other, children, StandardComparisonStrategy.instance());
    }

    public static ErrorMessageFactory shouldHaveChild(Object actual, Object other,
                                                      Object children, ComparisonStrategy comparisonStrategy) {
        return new ShouldHaveChild(actual, other, children, comparisonStrategy);
    }

    private ShouldHaveChild(Object actual, Object other, Object children, ComparisonStrategy comparisonStrategy) {
        super("%nExpecting:%n <%s>%nto have child:%n <%s>%n but did not%nActual children: <%s>%n%s",
                actual.getClass().getName(),
                other,
                actual instanceof Labeled ? ((Labeled) actual).getText() : actual,
                children,
                comparisonStrategy);
    }
}
