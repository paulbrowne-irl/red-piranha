package net.firstpartners.core.spreadsheet;

import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import net.firstpartners.core.log.RpLogger;

/**
 * A class that helps up map Excel Worksheets into Javabeans.
 * 
 * In general, we operate at the Named Range level, so the function is primarily ensuring that we can convert JavaBeans back into Excel
 * @author PBrowne
 *
 */
public class SheetConvertor {

	private static final Logger log = RpLogger.getLogger(SheetConvertor.class.getName());
	
	/**
	 * Get or create a cell in a row, if it is not found
	 * @param row
	 * @param cellReference
	 * @return
	 */
	static Cell getOrCreateCell(Row row, CellReference cellReference) {

		log.info("Looking for:" + cellReference);
		Cell cell = row.getCell(cellReference.getCol());

		if(cell==null) {
			cell = row.createCell(cellReference.getCol());
		}
		
		//log.info("found:" + cell.getStringCellValue());

		return cell;
	}

	/**
	 * Get or create a row in a sheet, if it is not found
	 * 
	 * @param thisSheet
	 * @param cellReference
	 * @return
	 */
	static Row getOrCreateRow(Sheet thisSheet, CellReference cellReference) {

		log.info("Looking for:" + cellReference);

		Row row = thisSheet.getRow(cellReference.getRow());

		if (row == null) {
			row = thisSheet.createRow(cellReference.getRow());
		}

		log.info("found:" + row.getRowNum());

		return row;

	}

	/**
	 * Get or create a sheet in a workbook, if is not found
	 * 
	 * @param wb
	 * @param thisRedCell
	 * @return
	 */
	static Sheet getOrCreateSheet(Workbook wb, net.firstpartners.data.Cell thisRedCell) {

		log.info("trying to find sheet:" + thisRedCell.getOriginalSheetReference());

		Sheet thisSheet = wb.getSheet(thisRedCell.getOriginalSheetReference());

		if (thisSheet == null) {
			thisSheet = wb.createSheet(thisRedCell.getOriginalSheetReference());
		}

		log.info("Found:" + thisSheet.getSheetName());
		return thisSheet;
	}

}
