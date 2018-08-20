package cm.homeautomation.zwave;

import org.springframework.stereotype.Component;

import com.oberasoftware.home.zwave.SerialZWaveConnector;

@Component
public class HASerialZWaveConnector extends SerialZWaveConnector {

	private String portName;

	public HASerialZWaveConnector(String portName) {
		this.portName = portName;
	}

	@Override
	public String getPortName() {
		return portName;
	}

	@Override
	public void setPortName(String portName) {
		this.portName = portName;
	}

}
