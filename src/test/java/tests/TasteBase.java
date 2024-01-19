package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

public class TasteBase {
    public static ApplicationManager app;

    @BeforeEach
    public void setUp() {
        if (app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser", "chrome"));
    }
}
