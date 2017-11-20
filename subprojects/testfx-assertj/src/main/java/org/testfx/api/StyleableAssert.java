package org.testfx.api;

import javafx.css.Styleable;

import org.assertj.core.api.AbstractAssert;

public class StyleableAssert extends AbstractAssert<StyleableAssert, Styleable>
{
    protected StyleableAssert(Styleable actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public StyleableAssert hasId(String id) {
        isNotNull();
        if (!actual.getId().equals(id)) {
            failWithMessage("Expecting:\n\t<%s>\nto have css id:\n\t<%s>\nbut was: <%s>", actual, id, actual.getId());
        }
        return this;
    }

    public StyleableAssert hasTypeSelector(String typeSelector) {
        isNotNull();
        if (!actual.getTypeSelector().equals(typeSelector)) {
            failWithMessage("Expecting:\n\t<%s>\nto have css type selector:\n\t<%s>\nbut was: <%s>", actual,
                    typeSelector, actual.getTypeSelector());
        }
        return this;
    }

}

