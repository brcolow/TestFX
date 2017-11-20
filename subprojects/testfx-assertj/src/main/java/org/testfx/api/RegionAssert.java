package org.testfx.api;

import javafx.scene.layout.Region;

import org.assertj.core.api.AbstractAssert;

public class RegionAssert extends AbstractAssert<RegionAssert, Region> {

    protected RegionAssert(Region actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public RegionAssert hasWidth(double width) {
        isNotNull();
        if (!(actual.getWidth() != width)) {
            failWithMessage("Expecting:\n\t<%s>\nto have width:\n\t<%s>\nbut was: <%s>", actual, width, actual.getWidth());
        }
        return this;
    }
}
