package learningtest.net.sf.ehcache.jmx;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import net.sf.ehcache.management.CacheStatisticsMBean;

public class EhCacheJmxClient {

	private final JMXConnector jmxc;
	private final MBeanServerConnection mbsc;

	public EhCacheJmxClient(String host, int port) {
		try {
			JMXServiceURL url = new JMXServiceURL(
					"service:jmx:rmi:///jndi/rmi://" + host + ":" + port
							+ "/jmxrmi");
			this.jmxc = JMXConnectorFactory.connect(url, null);
			this.mbsc = jmxc.getMBeanServerConnection();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void monitorForever(String cacheName) {
		ObjectName mbeanName;
		try {
			mbeanName = new ObjectName(
					"net.sf.ehcache:type=CacheStatistics,CacheManager=__DEFAULT__,name="
							+ cacheName);
			CacheStatisticsMBean cacheStatisticsProxy = JMX.newMBeanProxy(mbsc,
					mbeanName, CacheStatisticsMBean.class);

			while (true) {
				long cacheHits = cacheStatisticsProxy.getCacheHits();
				long cacheMisses = cacheStatisticsProxy.getCacheMisses();
				double cacheHitPercentage = cacheStatisticsProxy
						.getCacheHitPercentage();
				double cacheMissPercentage = cacheStatisticsProxy
						.getCacheMissPercentage();

				System.out.println(cacheHits);
				System.out.println(cacheMisses);
				System.out.println(cacheHitPercentage);
				System.out.println(cacheMissPercentage);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void close() {
		try {
			jmxc.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public static void main(String[] args) throws IOException,
			MalformedObjectNameException {
		String host = "localhost";
		int port = 10000;

		String cacheName = "someCache";

		EhCacheJmxClient client = new EhCacheJmxClient(host, port);
		client.monitorForever(cacheName);

		client.close();
	}

}
