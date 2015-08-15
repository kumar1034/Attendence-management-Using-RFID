import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
public class RFID {
	private String PORT_NAME;
	private static final int TIME_OUT = 2000;
	private int DATA_RATE;
	private SerialPort serialPort;
public static void main(String[] args) throws Exception {
	RFID demo = new RFID();
	demo.setPort("COM1");
	demo.setRate(9600);
	demo.initialize();
	System.out.println("Started");
	// type something in the console to close the program
	if (System.in.read() != -1) {
		demo.close();
	}
}
public void setPort(String PORT_NAME){
	this.PORT_NAME = PORT_NAME;
}
public void setRate(int DATA_RATE){
	this.DATA_RATE = DATA_RATE;
}
public void initialize(){
	CommPortIdentifier portId = null;
	try{
		portId = CommPortIdentifier.getPortIdentifier(PORT_NAME);
	}catch (NoSuchPortException e){
		e.printStackTrace();
	}
	try{
		this.serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
		this.serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
		SerialPort.PARITY_NONE);
		this.serialPort.addEventListener(new MyListener(this.serialPort));
		this.serialPort.notifyOnDataAvailable(true);
	}catch (Exception e){
		e.printStackTrace();
	}
}

public synchronized void close(){
	
	if (this.serialPort != null) {
		this.serialPort.removeEventListener();
		this.serialPort.close();
	}
	
}
}
class MyListener implements SerialPortEventListener{
	private final SerialPort port;
	StringBuilder builder = new StringBuilder();
public MyListener(SerialPort port){
	super();
	this.port = port;
}
public void serialEvent(SerialPortEvent event){
	if(event.getEventType() == SerialPortEvent.DATA_AVAILABLE){
		try{
			int available = this.port.getInputStream().available();
			byte chunk[] = new byte[available];
			this.port.getInputStream().read(chunk, 0, available);
			String input=new String(chunk);
			builder.append(input);
			if(builder.length() == 12){
				writeLog();
				builder.delete(0,builder.length());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
public void writeLog(){
	try{
		URL u=new URL("http://localhost:8888/RFIDAttendence/attendence");
		URLConnection con=u.openConnection();
		con.setDoOutput(true);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		out.write("t1="+builder.toString().trim());
		out.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String msg=br.readLine();
		if(msg != null){
			int i=Integer.parseInt(msg);
			if(i == 1){
				System.out.println("Attendence Accepted "+builder.toString());
			}
			if(i == 3){
				System.out.println("Invalid tag "+builder.toString());
			}
			if(i == 0){
				System.out.println("Attendence Not Accepted "+builder.toString());
			}
		}
	}catch(Exception e){
		e.printStackTrace();	
	}	
}
}
