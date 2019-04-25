package cn.quickly.project.utility.page;

import java.util.ArrayList;
import java.util.List;

import cn.quickly.project.utility.math.Maths;

public class Pagination extends Pageable {

	private static final long serialVersionUID = 1L;

	protected int total = 0;

	protected List<?> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = Math.max(0, total);
	}

	public int getTotalPage() {
		return (int) Maths.ceil(total, size);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getRows() {
		if (rows == null) {
			return (List<T>) (rows = new ArrayList<T>());
		} else {
			return (List<T>) rows;
		}
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
