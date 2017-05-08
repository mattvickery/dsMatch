package com.gds.dsmatch.transform.excel;

import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.transform.DataSourceModelTransformer;

import java.io.IOException;

import static org.springframework.util.Assert.notNull;

/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
public class ExcelToDataSourceModelTransformer<T extends ExcelFileDataSourceParseContext>
        implements DataSourceModelTransformer<T> {

    @Override
    public DataSourceRowDefinition transform(final T parseContext) throws IOException {

        notNull(parseContext, "Mandatory argument 'parseContext' is missing.");
        if (parseContext.getFile().getName().endsWith(".xlsx"))
            return new XssfToDataSourceModelTransformer<>().transform(parseContext);
        if (parseContext.getFile().getName().endsWith(".xls"))
            return new HssfToDataSourceModelTransformer<>().transform(parseContext);
        throw new UnsupportedOperationException("File type ["+parseContext.getFile().getName()+"] is" +
                " invalid, only *.xlsx and *.xls types are supported.");
    }
}