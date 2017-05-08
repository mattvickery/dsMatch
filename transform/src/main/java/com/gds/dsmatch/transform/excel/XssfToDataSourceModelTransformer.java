package com.gds.dsmatch.transform.excel;

import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class XssfToDataSourceModelTransformer<T extends ExcelFileDataSourceParseContext>
    extends AbstractDataSourceModelTransformer<T> {

    @Override
    public DataSourceRowDefinition transform(final T parseContext) throws IOException {

        notNull(parseContext, "Mandatory argument 'parseContext' is missing.");
        try (final XSSFWorkbook workbook = new XSSFWorkbook(parseContext.getFile())) {
            return getRowDefinition(workbook.getSheetAt(parseContext.getSheetIndex())
                    .getRow(parseContext.getHeaderRowIndex()),
                    workbook.getSheetAt(parseContext.getSheetIndex())
            .getRow(parseContext.getDataRowIndex()));
        } catch (InvalidFormatException e) {
            throw new IOException("Error processing input file:"+parseContext.getFile().getName(), e);
        }
    }
}
