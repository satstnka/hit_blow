package jp.trans_it.hit_blow.model;

public class Result {
	private int hit;
	private int blow;

	public Result(int hit, int blow) {
		this.hit = hit;
		this.blow = blow;
	}

	public int getHit() {
		return hit;
	}

	public int getBlow() {
		return blow;
	}

	public String toString() {
		String string = "H:" + this.hit + " B:" + this.blow;
		return string;
	}
}
