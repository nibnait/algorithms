package localtest.simpleTest;

import com.alibaba.fastjson.JSON;

import junit.framework.TestCase;
import localtest.Person;
import org.junit.Test;

/*

Created by nibnait on 2020-05-18
 */
public class MethodTest extends TestCase {

	@Test
	public void testCase() {
		Person person = new Person();

		test(person);

		System.out.println(JSON.toJSONString(person));
	}

	private void test(Person person) {
		person.setAge(1);
	}

}