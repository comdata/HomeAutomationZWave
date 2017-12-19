package cm.homeautomation.zwave;

import org.springframework.stereotype.Component;

import com.oberasoftware.home.spring.LocalSpringContainer;
import com.oberasoftware.home.zwave.SerialZWaveConnector;
import com.oberasoftware.home.zwave.ZWaveController;
import com.oberasoftware.home.zwave.exceptions.ZWaveException;
import com.oberasoftware.home.zwave.local.LocalZwaveSession;

@Component
public class HALocalZwaveSession extends LocalZwaveSession {

	private final String portName;

	public HALocalZwaveSession(String portName) {
		this.portName = portName;

	}

	@Override
	public void connect() throws ZWaveException {
		final SerialZWaveConnector connector = LocalSpringContainer.getBean(SerialZWaveConnector.class);
		connector.setPortName(getPortName());

		connector.connect();
		LocalSpringContainer.getBean(ZWaveController.class).initializeNetwork();
	}

	public String getPortName() {
		return portName;
	}
}
