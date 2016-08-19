package simulating.model;

/**
 * Model for a sortable line.
 * 
 * @author Santiago Nuñez
 * @date 18/08/2016 
 */
public class DosSortableLine implements Comparable<DosSortableLine> {
	private String sortableColumn;
	private String content;

	public DosSortableLine(String sortableColumn, String content) {
		super();
		this.sortableColumn = sortableColumn;
		this.content = content;
	}
	
	public String getSortableColumn() {
		return sortableColumn;
	}

	public String getContent() {
		return content;
	}
	
	@Override
	public int compareTo(DosSortableLine arg0) {
		return sortableColumn.compareTo(arg0.getSortableColumn());
	}
}
