package cn.quickly.project.utility.mock;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import cn.quickly.project.utility.xml.MockAdapter;

@XmlType
@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class MockXml {

	@XmlElement
	private String name;

	@XmlElement
	@XmlJavaTypeAdapter(MockAdapter.class)
	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
