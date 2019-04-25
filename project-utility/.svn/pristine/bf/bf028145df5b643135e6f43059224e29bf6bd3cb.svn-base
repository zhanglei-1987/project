package cn.quickly.project.utility.xml;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import cn.quickly.project.utility.text.Formats;
import cn.quickly.project.utility.time.Dates;

public class MockAdapter extends XmlAdapter<String, Date> {

	@Override
	public String marshal(Date arg0) throws Exception {

		// System.out.println(arg0);

		return Formats.format((Date) arg0, "yyyy-MM-dd");
	}

	@Override
	public Date unmarshal(String arg0) throws Exception {

		// System.out.println(arg0);

		return Dates.date((String) arg0, "yyyy-MM-dd");
	}

}
