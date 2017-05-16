package com.gds.dsmatch.transform.excel;

import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class HssfToDataSourceModelTransformer<T extends ExcelFileDataSourceParseContext>
    extends AbstractDataSourceModelTransformer<T> {

    @Override
    public DataSourceRowDefinition transform(final T parseContext) throws IOException {

        notNull(parseContext, "Mandatory argument 'parseContext' is missing.");
        try (final HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(parseContext.getFile())))) {
            return getRowDefinition(workbook.getSheetAt(parseContext.getSheetIndex())
                    .getRow(parseContext.getHeaderRowIndex()),
                    workbook.getSheetAt(parseContext.getSheetIndex())
            .getRow(parseContext.getDataRowIndex()));
        } catch (FileNotFoundException e) {
            throw new IOException("Error processing input file:"+parseContext.getFile().getName(), e);
        }
    }
}
