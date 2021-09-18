package jp.trans_it.hit_blow.model;


public class History {
	private int counter;
	private Numbers numbers;
	private Result result;

	public History(int counter, Numbers numbers, Result result) {
		this.counter = counter;
		this.numbers = numbers;
		this.result = result;
	}

	public int getCounter() {
		return counter;
	}

	public Numbers getNumbers() {
		return numbers;
	}

	public Result getResult() {
		return result;
	}
}
