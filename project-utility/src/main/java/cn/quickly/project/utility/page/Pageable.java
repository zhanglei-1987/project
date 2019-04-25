package cn.quickly.project.utility.page;

import java.io.Serializable;

public class Pageable implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int page = 1;

	protected int size = 20;

	public int getStartOffset() {
		return size * (page - 1);
	}

	public int getEndOffset() {
		return page * size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int currentPage) {
		this.page = Math.max(currentPage, 1);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int pageSize) {
		if (pageSize != this.size) {
			this.page = 1;
		}
		this.size = Math.max(pageSize, 1);
	}

}
