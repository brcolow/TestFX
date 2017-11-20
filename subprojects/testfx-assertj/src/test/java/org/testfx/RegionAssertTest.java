package org.testfx;

import static org.testfx.api.Assertions.assertThat;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class RegionAssertTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        stage.show();
    }

    @Test
    public void shouldHaveWidth() throws Exception {
        // given:
        Pane stackPane = FxToolkit.setupFixture(() -> new StackPane());

        // when:
        stackPane.setPrefWidth(50);

        // then:
        assertThat(stackPane).hasWidth(50);
    }
}
