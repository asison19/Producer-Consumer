
public class Producer extends Thread{
	
	Buffer buffer;
	int produceAmount;
	int produced = 0;
	
	public Producer(Buffer buffer, int produceAmount) {
		this.buffer = buffer;
		this.produceAmount = produceAmount;
	}
	public void run() {
		while(produced != produceAmount) {
			if (buffer.isFull()) {

			} else {
				buffer.write();
				produced++;
			}
		}
	}

}
