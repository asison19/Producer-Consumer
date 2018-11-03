
public class Buffer {
	int max;
	Data[] arr;
	int indexW = 0;
	int indexR = 0;
	int value = 1;
	int sleepAmount = 1; // in milliseconds
	int producerAmount;
	int totalValues;
	int amountRead = 0;
	boolean isDone = false;
	
	public Buffer(int max, int producerAmount, int produceAmount) {
		this.max = max;
		this.arr = new Data[max];
		
		this.producerAmount = producerAmount;
		totalValues = producerAmount * produceAmount;
	}
	
	public boolean isFull() {
		for (int i = 0 ; i < max; i++) {
			if(arr[i] == null)
				return false;
		}
		return true;
	}
	
	public boolean isEmpty() {
		for (int i = 0 ; i < max; i++) {
			if(arr[i] != null)
				return false;
		}
		return true;
	}
	
	public void read() {
		//if the element is empty, tick the next one for checking, return then check again
		if(arr[indexR % max] == null) { 
			indexR++;
			return;
		}
		System.out.print(arr[indexR % max].value + " ");
		arr[indexR % max] = null; //empty the element
		indexR++;
		amountRead++;
		try {
			Thread.sleep(sleepAmount); //sleep 1 second
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(amountRead == totalValues)
			isDone = true;
	}
	
	public boolean write() {
		if(arr[indexW % max]!= null) {
			indexW++;
			return false;
		}

		arr[indexW % max] = new Data(value);
		indexW++;
		value++;
		return true;
	}
}
