package mvn_ssh;

import java.util.Arrays;

import org.junit.Test;

import com.chinaedu.ssm.model.Persist;
import com.chinaedu.ssm.model.User;

public class TestAnnotation {

	@Test
	public void test(){
		User user = new User();
		user.setAge(33);
		user.setId((long) 33);
		user.setName("sdf");
		
		Persist persist = new Persist(user);
		
		System.out.println(persist.getTableName().toString());
		System.out.println(Arrays.toString(persist.getColumnNames()));
		System.out.println(Arrays.toString(persist.getColumnEqualField()));
		System.out.println(Arrays.toString(persist.getConditionCF()));
	}
}
