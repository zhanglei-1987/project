package cn.quickly.project.utility.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import cn.quickly.project.utility.collection.Collections;
import cn.quickly.project.utility.lang.Quiet;

public class InetAddresses {

	public static InetAddress[] getInterfaceAddresses() {

		Set<InetAddress> addresses = new HashSet<InetAddress>();

		Quiet.tryCatch(() -> {

			Enumeration<NetworkInterface> enumn = NetworkInterface.getNetworkInterfaces();

			while (enumn.hasMoreElements()) {

				NetworkInterface ni = enumn.nextElement();

				Enumeration<InetAddress> enumeration = ni.getInetAddresses();

				while (enumeration.hasMoreElements()) {

					addresses.add(enumeration.nextElement());

				}

			}

		});

		return Collections.toArray(InetAddress.class, addresses);

	}

}
