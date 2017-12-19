package cm.homeautomation.zwave;

import com.oberasoftware.base.event.EventHandler;
import com.oberasoftware.base.event.EventSubscribe;
import com.oberasoftware.home.zwave.api.ZWaveSession;
import com.oberasoftware.home.zwave.api.events.ZWaveEvent;
import com.oberasoftware.home.zwave.api.events.devices.DeviceSensorEvent;
import com.oberasoftware.home.zwave.exceptions.HomeAutomationException;

public class ZWaveController {

	public static class MyEventListener implements EventHandler {

		@EventSubscribe
		public void handleSensorEvent(final DeviceSensorEvent sensorEvent) {
			System.out.println("Received a sensor: " + sensorEvent.getSensorType() + " value: "
					+ sensorEvent.getValue().doubleValue() + " for node: " + sensorEvent.getNodeId());
		}

		@EventSubscribe
		public void receive(final ZWaveEvent event) throws Exception {

			System.out.println("Received an event: {}" + event);
		}
	}

	/**
	 * Initialises the binding. This is called after the 'updated' method has been
	 * called and all configuration has been passed.
	 */
	public static void doZwaveStuff() {
		System.out.println("Application startup");
		try {
			final ZWaveSession s = new HALocalZwaveSession("/dev/tty.usbserial-A70250X1");
			s.connect();
			s.subscribe(new MyEventListener());
		} catch (final HomeAutomationException e) {
			System.out.println("Error occurred in ZWave processing " + e);
		}
	}

	public static void main(final String[] args) {
		System.out.println("Starting Local ZWAVE App");

		doZwaveStuff();
	}
}
