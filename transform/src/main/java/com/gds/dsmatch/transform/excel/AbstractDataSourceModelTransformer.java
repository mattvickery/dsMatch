package com.gds.dsmatch.transform.excel;

import com.gds.dsmatch.model.pojo.type.DefaultDataSourceFieldDefinition;
import com.gds.dsmatch.model.pojo.type.DefaultDataSourceRowDefinition;
import com.gds.dsmatch.model.type.DataSourceFieldDefinition;
import com.gds.dsmatch.model.type.DataSourceRowDefinition;
import com.gds.dsmatch.transform.DataSourceModelTransformer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted;
import static org.springframework.util.Assert.notNull;


/**
 * @author Matt Vickery (matt.d.vickery@greendotsoftware.co.uk)
 * @since 08/05/2017
 */
abstract class AbstractDataSourceModelTransformer<T extends ExcelFileDataSourceParseContext>
        implements DataSourceModelTransformer<T> {

    protected DataSourceRowDefinition getRowDefinition(final Row headerRow, final Row dataRow) {

        notNull(headerRow, "Mandatory argument 'headerRow' is missing.");
        notNull(dataRow, "Mandatory argument 'dataRow' is missing.");
        final List<DataSourceFieldDefinition<?>> fields = new ArrayList<>();
        headerRow.cellIterator().forEachRemaining(cell -> {
            if (cellToFieldDefinitionTransformer(cell, dataRow).isPresent())
                fields.add(cellToFieldDefinitionTransformer(cell, dataRow).get());
        });
        return new DefaultDataSourceRowDefinition(fields);
    }

    private Optional<DataSourceFieldDefinition<?>> cellToFieldDefinitionTransformer(final Cell headerCell,
                                                                                    final Row dataRow) {

        notNull(headerCell, "Mandatory argument 'headerCell' is missing.");
        notNull(dataRow, "Mandatory argument 'dataRow' is missing.");
        final Cell cell = dataRow.getCell(headerCell.getColumnIndex());
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                return Optional.of(
                        new DefaultDataSourceFieldDefinition<>(headerCell.getStringCellValue(), Boolean.class));
            case NUMERIC:
                if (cellAppearsToBeADateType(headerCell, cell))
                    return Optional.of(
                            new DefaultDataSourceFieldDefinition<>(headerCell.getStringCellValue(), Date.class));
                return Optional.of(
                        new DefaultDataSourceFieldDefinition<>(headerCell.getStringCellValue(), BigDecimal.class));
            case FORMULA:
            case ERROR:
                throw new IllegalStateException("Cell type not supported:" + cell.getCellTypeEnum());
            case STRING:
            case BLANK:
            case _NONE:
            default:
                return Optional.of(
                        new DefaultDataSourceFieldDefinition<>(headerCell.getStringCellValue(), String.class));
        }
    }

    private boolean cellAppearsToBeADateType(final Cell headerCell, final Cell dataCell) {

        notNull(headerCell, "Mandatory argument 'headerCell' is missing.");
        notNull(dataCell, "Mandatory argument 'dataCell' is missing.");
        if ((isCellDateFormatted(dataCell) && headerCell.getStringCellValue().toUpperCase().contains("DATE")))
            return true;
        return false;
    }
}
