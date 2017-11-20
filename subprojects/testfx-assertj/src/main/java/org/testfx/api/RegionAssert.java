package org.testfx.api;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

import org.assertj.core.api.AbstractAssert;

public class RegionAssert extends AbstractAssert<RegionAssert, Region> {

    protected RegionAssert(Region actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public RegionAssert hasHeight(double height) {
        isNotNull();
        if (!(actual.getWidth() != height)) {
            failWithMessage("Expecting:\n\t<%s>\nto have height:\n\t<%s>\nbut was: <%s>",
                    actual, height, actual.getHeight());
        }
        return this;
    }

    public RegionAssert hasWidth(double width) {
        isNotNull();
        if (!(actual.getWidth() != width)) {
            failWithMessage("Expecting:\n\t<%s>\nto have width:\n\t<%s>\nbut was: <%s>",
                    actual, width, actual.getWidth());
        }
        return this;
    }

    public RegionAssert hasBackgroundColor(Color color) {
        isNotNull();
        if (actual.getBackground().getFills().isEmpty()) {
            failWithMessage("Expecting:\n\t<%s>\nto have background color:\n\t<%s>\nbut was empty (no background " +
                    "color specified).", actual, color);
        }
        Stream<Color> backgroundColors = actual.getBackground().getFills().stream().map(fill -> (Color) fill.getFill());
        if (backgroundColors.noneMatch(fillColor -> fillColor.equals(color))) {
            failWithMessage("Expecting:\n\t<%s>\nto contain background color:\n\t<%s>\n" +
                    "but did not - actual background colors:\n\t<%s>", actual, color,
                    backgroundColors.collect(Collectors.toList()));
        }
        return this;
    }

    public RegionAssert hasEmptyBorder() {
        isNotNull();
        if (!actual.getBorder().isEmpty()) {
            failWithMessage("Expecting:\n\t<%s>\nto have an empty border but was: <%s>", actual, actual.getBorder());
        }
        return this;
    }

    public RegionAssert hasPadding(Insets padding) {
        isNotNull();
        if (!actual.getPadding().equals(padding)) {
            failWithMessage("Expecting:\n\t<%s>\nto have padding\n\t<%s>\nbut was: <%s>",
                    actual, padding, actual.getPadding());
        }
        return this;
    }

    public RegionAssert hasPadding(double top, double right, double bottom, double left) {
        isNotNull();
        Insets insets = new Insets(top, right, bottom, left);
        if (!actual.getPadding().equals(insets)) {
            failWithMessage("Expecting:\n\t<%s>\nto have padding\n\t<%s>\nbut was: <%s>",
                    actual, insets, actual.getPadding());
        }
        return this;
    }

    public RegionAssert hasPaddingTop(double paddingTop) {
        isNotNull();
        if (!(actual.getPadding().getTop() == paddingTop)) {
            failWithMessage("Expecting:\n\t<%s>\nto have top padding\n\t<%s>\nbut was: <%s>",
                    actual, paddingTop, actual.getPadding().getTop());
        }
        return this;
    }

    public RegionAssert hasPaddingBottom(double paddingBottom) {
        isNotNull();
        if (!(actual.getPadding().getBottom() == paddingBottom)) {
            failWithMessage("Expecting:\n\t<%s>\nto have bottom padding\n\t<%s>\nbut was: <%s>",
                    actual, paddingBottom, actual.getPadding().getBottom());
        }
        return this;
    }

    public RegionAssert hasPaddingRight(double paddingRight) {
        isNotNull();
        if (!(actual.getPadding().getRight() == paddingRight)) {
            failWithMessage("Expecting:\n\t<%s>\nto have right padding\n\t<%s>\nbut was: <%s>",
                    actual, paddingRight, actual.getPadding().getRight());
        }
        return this;
    }

    public RegionAssert hasPaddingLeft(double paddingLeft) {
        isNotNull();
        if (!(actual.getPadding().getLeft() == paddingLeft)) {
            failWithMessage("Expecting:\n\t<%s>\nto have left padding\n\t<%s>\nbut was: <%s>",
                    actual, paddingLeft, actual.getPadding().getLeft());
        }
        return this;
    }
}
