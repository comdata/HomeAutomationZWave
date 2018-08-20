package cm.homeautomation.zwave;

import com.oberasoftware.base.event.EventHandler;
import com.oberasoftware.base.event.EventSubscribe;
import com.oberasoftware.home.zwave.api.ZWaveSession;
import com.oberasoftware.home.zwave.api.events.ZWaveEvent;
import com.oberasoftware.home.zwave.api.events.devices.DeviceSensorEvent;
import com.oberasoftware.home.zwave.exceptions.HomeAutomationException;

public class ZWaveController {

	public class HAEventListener implements EventHandler {

		private HAZWaveEventListener eventListener;

		public HAEventListener() {
			this.eventListener = null;
		}

		public HAEventListener(HAZWaveEventListener eventListener) {
			this.eventListener = eventListener;

		}

		public HAZWaveEventListener getEventListener() {
			return eventListener;
		}

		@EventSubscribe
		public void handleSensorEvent(final DeviceSensorEvent sensorEvent) {
			System.out.println("Received a sensor: " + sensorEvent.getSensorType() + " value: "
					+ sensorEvent.getValue().doubleValue() + " for node: " + sensorEvent.getNodeId());

			if (getEventListener() != null) {
				getEventListener().receiveZWaveSensorEvent(sensorEvent);
			}
		}

		@EventSubscribe
		public void receive(final ZWaveEvent event) {

			System.out.println("Received an event: {}" + event);
			if (getEventListener() != null) {
				getEventListener().receiveZWaveEvent(event);
			}
		}

		public void setEventListener(HAZWaveEventListener eventListener) {
			this.eventListener = eventListener;
		}
	}

	public static void main(final String[] args) {
		System.out.println("Starting Local ZWAVE App");

		new ZWaveController();

	}

	public ZWaveController() {
		init(null);
	}

	public ZWaveController(HAZWaveEventListener eventListener) {
		init(eventListener);
	}

	/**
	 * Initialises the binding. This is called after the 'updated' method has been
	 * called and all configuration has been passed.
	 */
	public void init(HAZWaveEventListener eventListener) {
		System.out.println("Application startup");
		try {
			final ZWaveSession s = new HALocalZwaveSession("/dev/tty.usbserial-A70250X1");
			s.connect();
			s.subscribe(new HAEventListener(eventListener));
		} catch (final HomeAutomationException e) {
			System.out.println("Error occurred in ZWave processing " + e);
		}
	}
}
