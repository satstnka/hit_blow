package jp.trans_it.hit_blow.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Numbers {
	private int[] array;

	public Numbers(int size) {
		int length = Math.max(Math.min(size, 9),  1);
		this.array = new int[length];
		Arrays.fill(this.array, 0);
	}

	public void random(int maxNumber) {
		List<Integer> list = new ArrayList<Integer>();
		for(int n = 1; n <= maxNumber; n++) {
			list.add(n);
		}

		Collections.shuffle(list);

		for(int i = 0; i < this.array.length; i++) {
			this.array[i] = list.get(i);
		}
	}

	public void input(String input) {
		int tmp = Integer.parseInt(input);
		for(int i = 0; i < this.array.length; i++) {
			this.array[this.array.length - 1 - i] = tmp % 10;
			tmp = tmp / 10;
		}
	}

	public int countHit(Numbers other) {
		int hit = 0;
		int length = Math.min(this.array.length, other.array.length);
		for(int i = 0; i < length; i++) {
			if(this.array[i] == other.array[i]) {
				hit++;
			}
		}
		return hit;
	}

	public int countUsed(Numbers other) {
		int blow = 0;
		for(int i = 0; i < this.array.length; i++) {
			for(int j = 0; j < other.array.length; j++) {
				if(this.array[i] == other.array[j]) {
					blow++;
				}
			}
		}
		return blow;
	}

	public Result check(Numbers other) {
		int hit = this.countHit(other);
		int used = this.countUsed(other);
		int blow = used - hit;
		Result result = new Result(hit, blow);

		System.out.println(other + " --- " + result);

		return result;
	}

	public String toString() {
		String string = "Not available";
		if(this.array != null) {
			string = "";
			for(int i = 0; i < this.array.length; i++) {
				string = string + this.array[i];
			}
		}
		return string;
	}
}
