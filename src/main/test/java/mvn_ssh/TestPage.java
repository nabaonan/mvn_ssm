package mvn_ssh;

import org.junit.Test;

import com.chinaedu.ssm.model.PageInfo;

public class TestPage {

	@Test
	public void testPage(){
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordsPerPage(50);
		pageInfo.setCurrentPage(2);
		pageInfo.setTotalRecords(100);

		System.out.println("打印结果");
		System.out.println(pageInfo.getIndexStart());
		System.out.println(pageInfo.getTotalPage());
		
	}
}
