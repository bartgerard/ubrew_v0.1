package be.gerard.core;

import be.gerard.general.importer.TranslationImporter;
import be.gerard.general.interface_v1.model.Translation;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author bartgerard
 */
public class TranslationImporterTest {

    @Test
    public void testImport() throws IOException, InvalidFormatException {
        String changedBy = "test";
        File file = new File("src/test/resources/ubrew_test.xlsx");

        try (FileInputStream fis = new FileInputStream(file)) {
            List<Translation> translations = TranslationImporter.importFromExcel(fis, changedBy);

            Assert.assertFalse("failed to import translations", translations.isEmpty());
        }
    }

}
