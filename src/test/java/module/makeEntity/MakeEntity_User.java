package module.makeEntity;

import org.openqa.selenium.support.PageFactory;

import actionFactory.ElActions;
import driverFactory.Driver;
import pageFactory.pages.CustomersAddPage;

public class MakeEntity_User extends MakeEntity {
	
	private final CustomersAddPage customersAddpage;

	private final String fname;
	private final String lname;
	private final String em;
	private final String pw;
	private final String mobilenum;
	private final String country;
	private final String ad1;
	private final String ad2;
	private final String news;

	public MakeEntity_User(String fname, String lname, String em, String pw, String mobilenum, String country, String ad1, String ad2, String news) throws Exception {
	    this.customersAddpage = PageFactory.initElements(Driver.get(), CustomersAddPage.class);
	
	    this.fname = fname;
		this.lname = lname;
		this.em = em;
		this.pw = pw;
		this.mobilenum = mobilenum;
		this.country = country;
		this.ad1 = ad1;
		this.ad2 = ad2;
		this.news = news;
	     
	}

	@Override
	void setFirstName() throws Exception {

		ElActions act = new ElActions();
		act.sendkey(customersAddpage.firstname, fname);
		
	}

	@Override
	void setLastName() throws Exception {

		ElActions act = new ElActions();
		act.sendkey(customersAddpage.lastname, lname);
			
	}

	@Override
	void setUserEmail() throws Exception {

		ElActions act = new ElActions();
		act.sendkey(customersAddpage.email, em);
		
	}

	@Override
	void setUserPW() throws Exception {

		ElActions act = new ElActions();
		act.sendkey(customersAddpage.password, pw);
		
	}

	@Override
	void setCountry() throws Exception {

		ElActions act = new ElActions();
		
		act.click(customersAddpage.inputCountry);
		act.sendkey(customersAddpage.inputCountry, country);
		act.click(customersAddpage.resultCountryList.get(0));
		
	}

	@Override
	void setMobileNum() throws Exception {
		ElActions act = new ElActions();
		act.sendkey(customersAddpage.mobile, mobilenum);
		
	}
	
	@Override
	void setAddress1() throws Exception {

		ElActions act = new ElActions();
		act.sendkey(customersAddpage.address1, ad1);
		
	}

	@Override
	void setAddress2() throws Exception {

		ElActions act = new ElActions();
		act.sendkey(customersAddpage.address2, ad2);
			
	}

	@Override
	void setSubScriber() throws Exception {

		ElActions act = new ElActions();
		if(news.contains("TRUE")) act.click(customersAddpage.newssub);
	
	}

	@Override
	void wrongSaveEntity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void saveEntity() throws Exception {
		
		ElActions act = new ElActions();
		act.click(customersAddpage.updatesettingbtn);
		customersAddpage.checkAlert();
		
	}

	@Override
	void closeMakeEntity(String btntype) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
