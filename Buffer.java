
public class Buffer {
	int max; //how large the buffer is
	Data[] data;
	int indexW = 0;
	int indexR = 0;
	int value = 1;
	int sleepAmount = 1; // in milliseconds
	int producerAmount;
	int totalValues;
	int amountRead = 0;
	boolean isDone = false;
	
	public Buffer(int max, int producerAmount, int produceAmount, Data[] arr) {
		this.max = max;
		arr = new Data[max];
		data = arr;
		this.producerAmount = producerAmount;
		totalValues = producerAmount * produceAmount;
	}
	
	public boolean isFull() {
		for (int i = 0 ; i < max; i++) {
			if(data[i] == null)
				return false;
		}
		return true;
	}
	
	public boolean isEmpty() {
		for (int i = 0 ; i < max; i++) {
			if(data[i] != null)
				return false;
		}
		return true;
	}
	
	public void read() {
		//if the element is empty, tick the next one for checking, return then check again
		if(data[indexR % max] == null) { 
			indexR++;
			return;
		}
		System.out.print(data[indexR % max].value + " ");
		data[indexR % max] = null; //empty the element
		indexR++;
		amountRead++;
		try {
			Thread.sleep(sleepAmount); //sleep 1 second
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(amountRead == totalValues) {
			isDone = true;
		}
	}
	
	public boolean write() {
		if(data[indexW % max]!= null) {
			indexW++;
			return false;
		}

		data[indexW % max] = new Data(value);
		indexW++;
		value++;
		return true;
	}
}
