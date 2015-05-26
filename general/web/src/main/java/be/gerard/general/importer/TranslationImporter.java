package be.gerard.general.importer;

import be.gerard.general.interface_v1.exception.TranslationServiceException;
import be.gerard.general.interface_v1.exception.error.TranslationServiceError;
import be.gerard.general.interface_v1.model.Translation;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * TranslationImporter
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class TranslationImporter implements Serializable {

    private static final int APPLICATION_INDEX = 0;
    private static final int KEY_INDEX = 1;
    private static final int VALUE_FIRST_INDEX = 2;

    private TranslationImporter() {
    }

    public static List<Translation> importFromExcel(final InputStream is, final String changedBy) throws IOException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(is));
            
            List<Translation> translations = new ArrayList<>();
            
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {
                    if (sheet.getFirstRowNum() != row.getRowNum()) {
                        String application = row.getCell(APPLICATION_INDEX).getStringCellValue();
                        String key = row.getCell(KEY_INDEX).getStringCellValue();
                        for (int j = VALUE_FIRST_INDEX; j < row.getLastCellNum(); j++) {
                            String language = sheet.getRow(sheet.getFirstRowNum()).getCell(j).getStringCellValue();
                            String value = row.getCell(j).getStringCellValue();
                            translations.add(new Translation(application, language, key, value, changedBy));
                        }
                    }
                }
            }
            
            is.close();
            
            return translations;
        } catch (InvalidFormatException ex) {
            throw new TranslationServiceException(Translation.class.getSimpleName(), TranslationServiceError.IMPORT_FAILED, new String[]{});
        }
    }

}
