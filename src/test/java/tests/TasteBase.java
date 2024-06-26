package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class TasteBase {
    public static ApplicationManager app;

    @BeforeEach
    public void setUp() throws IOException {
        if (app == null) {
            app = new ApplicationManager();
        }
        var properties = new Properties();
        properties.load(new FileReader(System.getProperty("target", "local.properties")));

        app.init(System.getProperty("browser", "chrome"),properties);
    }

    @AfterEach
    public void checkDataBaseConsistency(){
        app.getJdbsHelper().checkConsistency();
    }

}
