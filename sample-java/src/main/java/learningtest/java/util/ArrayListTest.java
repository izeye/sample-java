package learningtest.java.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void remove() {
		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(2);

		Iterator<Integer> iterator = arrayList.iterator();
		iterator.next();
		iterator.remove();

		assertThat(arrayList, is(Collections.singletonList(2)));
	}

}
