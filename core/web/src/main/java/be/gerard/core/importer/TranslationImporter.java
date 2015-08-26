package be.gerard.core.importer;

import be.gerard.core.interface_v1.enums.TranslationType;
import be.gerard.core.interface_v1.model.Translation;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.*;

/**
 * TranslationImporter
 *
 * @author bartgerard
 * @version v0.0.1
 */
public class TranslationImporter implements Serializable {

    private TranslationImporter() {
    }

    public static List<Translation> importFromExcel(final InputStream is, final String changedBy) throws IOException {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(OPCPackage.open(is));

            List<Translation> translations = new ArrayList<>();

            for (Sheet sheet : workbook) {

                Map<Col, Integer> indices = retrieveIndices(sheet.getRow(sheet.getFirstRowNum()));

                for (Row row : sheet) {
                    if (sheet.getFirstRowNum() != row.getRowNum()) {
                        String application = row.getCell(indices.get(Col.APPLICATION)).getStringCellValue();
                        String prefix = row.getCell(indices.get(Col.PREFIX)).getStringCellValue();
                        TranslationType type = TranslationType.valueOf(row.getCell(indices.get(Col.TYPE)).getStringCellValue().toUpperCase());
                        String language = row.getCell(indices.get(Col.LANGUAGE)).getStringCellValue();
                        String key = row.getCell(indices.get(Col.KEY)).getStringCellValue();
                        String value = row.getCell(indices.get(Col.VALUE)).getStringCellValue();
                        translations.add(new Translation(application, language, prefix, type, key, value, changedBy));
                    }
                }
            }

            is.close();

            return translations;
        } catch (InvalidFormatException ex) {
            throw new IllegalArgumentException("TODO"); // TODO
            //throw new TranslationServiceException(Translation.class.getSimpleName(), TranslationServiceError.IMPORT_FAILED, new String[]{});
        }
    }

    private enum Col {
        APPLICATION,
        PREFIX,
        TYPE,
        LANGUAGE,
        KEY,
        VALUE;

        static Col get(String name) {
            if (StringUtils.hasText(name)) {
                String toSearch = name.toUpperCase().replace(' ', '_');

                for (Col col : Col.values()) {
                    if (Objects.equals(col.name(), toSearch)) {
                        return col;
                    }
                }
            }

            return null;
        }

    }

    private static Map<Col, Integer> retrieveIndices(Row firstRow) {
        final Map<Col, Integer> result = new HashMap<>();

        for (int i = firstRow.getFirstCellNum(); i <= firstRow.getLastCellNum(); i++) {
            Cell cell = firstRow.getCell(i);
            String value = cell != null ? cell.getStringCellValue() : null;
            Col col = Col.get(value);

            if (col != null) {
                result.put(col, i);
            }
        }

        return result;
    }

}
