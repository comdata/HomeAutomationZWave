package cm.homeautomation.zwave;

import org.springframework.stereotype.Component;

import com.oberasoftware.home.zwave.SerialZWaveConnector;

@Component
public class HASerialZWaveConnector extends SerialZWaveConnector {

	private String portName;

	public HASerialZWaveConnector(String portName) {
		this.portName = portName;
	}

	public String getPortName() {
		return portName;
	}

	public void setPortName(String portName) {
		this.portName = portName;
	}

}
