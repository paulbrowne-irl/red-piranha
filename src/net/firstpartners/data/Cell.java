package net.firstpartners.data;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * JavaBean equivalent of a cell in an Excel Spreadsheet. Since we also map from
 * other sources into these classes, a Cell could be a cell from a table in a
 * Word Document. For simplicity, we map Paragraphs from a word document into a
 * cell (to making writing rules easier)
 * 
 */
public class Cell implements PropertyChangeListener, Serializable {

	private static final long serialVersionUID = -763504507901540819L;

	private String name = null;

	private String nextName =null;
	
	public String getNextName() {
		return nextName;
	}

	public void setNextName(String nextName) {
		this.nextName = nextName;
	}

	private transient PropertyChangeSupport changes = new PropertyChangeSupport(this);

	private String comment;

	private boolean modified = false;

	private int originalCellCol = -1;

	private int originalCellRow = -1;

	private String originalTableReference = null;
	private Object value;

	/**
	 * Default constructor - needed to keep this a Javabean
	 */
	public Cell() {
	}

	/**
	 * Most Basic useful Cell - with a name and value
	 * 
	 * @param name
	 * @param value
	 */
	public Cell(String name, Object value) {
		super();
		this.name = name;
		this.value = value;
	}

	public void addPropertyChangeListener(final PropertyChangeListener l) {
		this.changes.addPropertyChangeListener(l);
	}

	/**
	 * Check for equality with another Object / cell
	 * 
	 * @param obj - the object to compare us to
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (modified != other.modified)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public String getComment() {
		return comment;
	}

	/**
	 * checks if a name starts with this text we supply*
	 * 
	 * @return true if it is
	 */
	public boolean getNameStartsWith(String textToCompareAgainst) {
		if (name != null) {
			return name.startsWith(textToCompareAgainst);

		}

		// otherwise
		return false;
	}

	public int getOriginalCellCol() {
		return originalCellCol;
	}

	/**
	 * Get the original Cell reference (if available) - e.g. the Cell address from
	 * the Original (Apache Poi) Spreadsheet
	 * 
	 * @return - null if this is not available
	 */
	public String getOriginalCellReference() {
		if (this.originalCellCol > -1 && this.originalCellRow > -1) {
			return ("R" + originalCellRow + "C" + originalCellCol);
		}

		return null;
	}

	public int getOriginalCellRow() {
		return originalCellRow;
	}

	/**
	 * Get the original Table reference (if available) - e.g.from the Original (Word
	 * Table, sheet in Spreadsheet) that this came from
	 * 
	 * @return
	 */
	public String getOriginalTableReference() {

		return originalTableReference;
	}

	public Object getValue() {
		return value;
	}

	/**
	 * If possible, get the value of the Cell as a Boolean
	 * 
	 * @return null if this conversion is not possible
	 */
	public Boolean getValueAsBoolean() {

		if (value == null) {
			return null;
		}

		if (value instanceof Boolean) {
			return ((Boolean) value);
		}

		if (value.toString().equalsIgnoreCase("true")) {
			return true;
		}

		if (value.toString().equalsIgnoreCase("false")) {
			return false;
		}

		// Default
		return null;
	}

	/**
	 * If possible, get the value of the Cell as an Long
	 * 
	 * @return null if this conversion is not possible
	 */
	public Long getValueAsLong() {

		if (value == null) {
			return null;
		}

		if ((value instanceof Number)) {
			return ((Number) value).longValue();
		}

		if (NumberUtils.isCreatable(value.toString())) {
			return NumberUtils.createDouble(value.toString()).longValue();
		}

		// Default
		return null;
	}

	/**
	 * Get the value of the Cell as a String
	 * 
	 * @return null if this conversion is not possible
	 */
	public String getValueAsText() {

		if (value != null) {
			return value.toString();
		}

		// default
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + (modified ? 1231 : 1237);
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * Flag that allows us to signal if we have modified the cell
	 * 
	 * @return
	 */
	public boolean isModified() {
		return modified;
	}

	public void propertyChange(PropertyChangeEvent arg0) {

		// Pass on the event to the registered listener
		changes.firePropertyChange(arg0);

	}

	/**
	 * Puts quotes around internal, but ensure null is still null
	 * 
	 * @param value to quote
	 * @return internal value with quotes around it (escaped) as approprate
	 */
	private String quoteInternalValueIfNotNumber() {

		if (value == null) {
			return null;
		}

		// if we can return this as a number, do so without quotes.
		Long tmpNum = getValueAsLong();
		if (tmpNum != null) {
			return tmpNum.toString();
		}

		Boolean tmpBool = getValueAsBoolean();
		if (tmpBool != null) {
			return tmpBool.toString();
		}

		// default treat as string and return
		return "\"" + value.toString() + "\"";
	}

	/**
	 * Puts quotes around strings, but ensure null is still null
	 * 
	 * @param value to quote
	 * @return same value with quots around it (escaped)
	 */
	private String quoteString(Object value) {

		if (value == null) {
			return null;
		}

		return "\"" + value.toString() + "\"";
	}

	public void removePropertyChangeListener(final PropertyChangeListener l) {
		this.changes.removePropertyChangeListener(l);
	}

//	public void setHoldingRange(Range holdingRange) {
//		this.holdingRange = holdingRange;
//	}

	public void setName(String name) {

		String oldValue = this.name;
		this.name = name;
		this.modified = true;
		this.changes.firePropertyChange("name", oldValue, name);

	}

	public void setComment(String comment) {
		String oldValue = this.comment;
		this.comment = comment;
		this.modified = true;
		this.changes.firePropertyChange("comment", oldValue, comment);
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	/**
	 * * set the original Cell reference (if available) - e.g. the Cell address from
	 * the Original (Apache Poi) Spreadsheet
	 * 
	 * @param cellCol
	 * @param cellRow
	 */
	public void setOriginalCellReference(int cellRow, int cellCol) {

		Object oldColValue = this.originalCellCol;
		Object oldRowValue = this.originalCellRow;

		this.originalCellCol = cellCol;
		this.originalCellRow = cellRow;

		this.modified = true;
		this.changes.firePropertyChange("originalCellReCol", oldColValue, cellCol);
		this.changes.firePropertyChange("originalCellReCol", oldRowValue, cellRow);
	}

	/**
	 * Set the original Table (Word Table, sheet in Spreadsheet) that this came from
	 * 
	 * @param newOriginalSheetReference
	 */
	public void setOriginalTableReference(String newOriginalSheetReference) {

		Object oldValue = this.originalTableReference;
		this.originalTableReference = newOriginalSheetReference;
		this.modified = true;
		this.changes.firePropertyChange("value", oldValue, value);
	}

	public void setValue(Object value) {
		Object oldValue = this.value;
		this.value = value;
		this.modified = true;
		this.changes.firePropertyChange("originalsheetreference", oldValue, value);
	}

	/**
	 * Print an internal representation of the Cell contents. This is the long
	 * version. If used for every cell in a large dataset it could cause an
	 * OutOfMemoryError. This version should be copy-pastable into rules / drl files
	 * 
	 * @see toString()
	 */
	public String toLongString() {

		return "$cell : Cell (\n    name==" + quoteString(name) + "\n    value=="
				+ quoteInternalValueIfNotNumber() + "\n    comment==" + quoteString(comment) + "\n    modified=="
				+ modified + "\n    originalCellReference==" + quoteString(getOriginalCellReference())
				+ "\n    originalTableReference==" + quoteString(originalTableReference) + "\n    valueAsBoolean=="
				+ getValueAsBoolean() + "\n    valueAsLong==" + getValueAsLong() + "\n)";

	}

	/**
	 * @see toLongString() if more comprehensive data needed This is the short
	 *      version.
	 */
	@Override
	public String toString() {

		return "Cell ( name==\"" + name + "\" )";
	}
	
	/**
	 * Appends textToAdd to existing value.
	 * Converts existing cell value to string if it already is there.
	 * If cell is null, then textToAdd will be the new value
	 * @param textToAdd
	 */
	public void appendValue(String textToAdd) {
		
		//for property change listener
		Object oldValue =value;
		
		if(value ==null) {
			value="";
		}
		
		StringBuffer tmpValue = new StringBuffer();
		tmpValue.append(value); //handles version of numbers
		tmpValue.append(textToAdd);
		
		value=tmpValue.toString();
		
		this.changes.firePropertyChange("value", oldValue, value);
		
	}

}
