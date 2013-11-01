package learningtest.javax.management.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.management.AttributeChangeNotification;
import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import learningtest.javax.management.hello.HelloMBean;
import learningtest.javax.management.queue.QueueSample;
import learningtest.javax.management.queue.QueueSamplerMXBean;

public class Client {

	public static class ClientListener implements NotificationListener {
		@Override
		public void handleNotification(Notification notification,
				Object handback) {
			echo("\nReceived notification:");
			echo("\tClassName: " + notification.getClass().getName());
			echo("\tSource: " + notification.getSource());
			echo("\tType: " + notification.getType());
			echo("\tMessage: " + notification.getMessage());
			if (notification instanceof AttributeChangeNotification) {
				AttributeChangeNotification acn = (AttributeChangeNotification) notification;
				echo("\tAttributeName: " + acn.getAttributeName());
				echo("\tAttributeType: " + acn.getAttributeType());
				echo("\tNewValue: " + acn.getNewValue());
				echo("\tOldValue: " + acn.getOldValue());
			}
		}
	}

	public static void main(String[] args) throws IOException,
			MalformedObjectNameException, InstanceNotFoundException {
		echo("\nCreate an RMI connector client and connect it to the RMI connector server");
		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
		JMXConnector jmxc = JMXConnectorFactory.connect(url, null);

		ClientListener listener = new ClientListener();

		echo("\nGet an MBeanServerConnection");
		MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
		waitForEnterPressed();

		echo("\nDomains");
		String[] domains = mbsc.getDomains();
		Arrays.sort(domains);
		for (String domain : domains) {
			echo("\tDomain = " + domain);
		}
		waitForEnterPressed();

		echo("\nMBeanServer default domain = " + mbsc.getDefaultDomain());

		echo("\nMBean count = " + mbsc.getMBeanCount());

		echo("\nQuery MBeanServer MBeans:");
		Set<ObjectName> names = new TreeSet<ObjectName>(mbsc.queryNames(null,
				null));
		for (ObjectName name : names) {
			echo("\tObjectName = " + name);
		}
		waitForEnterPressed();

		echo("\n>>> Perform operations on Hello MBean <<<");

		ObjectName mbeanName = new ObjectName("com.example:type=Hello");

		HelloMBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName,
				HelloMBean.class, true);

		echo("\nAdd notification listener...");
		mbsc.addNotificationListener(mbeanName, listener, null, null);

		echo("\nCacheSize = " + mbeanProxy.getCacheSize());

		mbeanProxy.setCacheSize(150);

		echo("\nWaiting for notification...");
		sleep(2000);

		echo("\nCacheSize = " + mbeanProxy.getCacheSize());

		echo("\nInvoke sayHello() in Hello MBean...");
		mbeanProxy.sayHello();

		echo("\nInvoke add(2, 3) in Hello MBean...");
		echo("\nadd(2, 3) = " + mbeanProxy.add(2, 3));
		waitForEnterPressed();

		echo("\n>>> Perform operations on QueueSampler MXBean <<<");

		ObjectName mxbeanName = new ObjectName("com.example:type=QueueSampler");

		QueueSamplerMXBean mxbeanProxy = JMX.newMXBeanProxy(mbsc, mxbeanName,
				QueueSamplerMXBean.class);

		QueueSample queue1 = mxbeanProxy.getQueueSample();
		echo("\nQueueSample.Date = " + queue1.getDate());
		echo("QueueSample.Head = " + queue1.getHead());
		echo("QueueSample.Size = " + queue1.getSize());

		echo("\nInvoke clearQueue() in QueueSampler MXBean...");
		mxbeanProxy.clearQueue();

		QueueSample queue2 = mxbeanProxy.getQueueSample();
		echo("\nQueueSample.Date = " + queue2.getDate());
		echo("QueueSample.Head = " + queue2.getHead());
		echo("QueueSample.Size = " + queue2.getSize());
		waitForEnterPressed();

		echo("\nClose the connection to the server");
		jmxc.close();
		echo("\nBye! Bye!");
	}

	private static void echo(String msg) {
		System.out.println(msg);
	}

	private static void waitForEnterPressed() {
		try {
			echo("\nPress <Enter> to continue...");
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
