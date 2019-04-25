package cn.quickly.project.utility.mock;

import java.util.Date;
import java.util.List;

import cn.quickly.project.utility.annotation.Alias;
import cn.quickly.project.utility.annotation.Format;
import cn.quickly.project.utility.annotation.Ignore;
import cn.quickly.project.utility.json.JSONSerializer;

@MockService
public class MockDemo {

	@Alias("xname")
	private String name = "hello";

	private List<Long> blocks;

	@Ignore
	private List<MockDemo> inners;

	@Format(pattern = "yyyyMMddHHmmss")
	private Date date = new Date();

	private MockList mockList;

	private Date transactionTime;

	public MockDemo() {
	}

	public MockDemo(CharSequence name) {
		this.name = name.toString();
	}

	@Alias("xname")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<Long> blocks) {
		this.blocks = blocks;
	}

	@Ignore
	public List<MockDemo> getInners() {
		return inners;
	}

	public void setInners(List<MockDemo> inners) {
		this.inners = inners;
	}

	@Format(pattern = "yyyyMMddHHmmss")
	public final Date getDate() {
		return date;
	}

	public final void setDate(Date date) {
		this.date = date;
	}

	public final MockList getMockList() {
		return mockList;
	}

	public final void setMockList(MockList mockList) {
		this.mockList = mockList;
	}

	public final Date getTransactionTime() {
		return transactionTime;
	}

	public final void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	@Override
	public String toString() {
		return JSONSerializer.stringify(this);
	}

}
