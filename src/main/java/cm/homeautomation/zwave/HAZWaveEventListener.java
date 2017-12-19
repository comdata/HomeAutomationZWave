package cm.homeautomation.zwave;

import com.oberasoftware.home.zwave.api.events.ZWaveEvent;
import com.oberasoftware.home.zwave.api.events.devices.DeviceSensorEvent;

public interface HAZWaveEventListener {

	void receiveZWaveEvent(ZWaveEvent event);

	void receiveZWaveSensorEvent(DeviceSensorEvent sensorEvent);

}
