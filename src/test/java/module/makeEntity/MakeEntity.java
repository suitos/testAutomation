package module.makeEntity;

public abstract class MakeEntity {

	abstract void setFirstName() throws Exception;
	abstract void setLastName() throws Exception;
	abstract void setUserEmail() throws Exception;

	abstract void setUserPW() throws Exception;
	abstract void setMobileNum() throws Exception;
	
	abstract void setCountry() throws Exception;
	abstract void setAddress1() throws Exception;
	abstract void setAddress2() throws Exception;
	
	abstract void setSubScriber() throws Exception;
	
	abstract void wrongSaveEntity();
	abstract void saveEntity() throws Exception;
	abstract void closeMakeEntity(String btntype) throws Exception;

	public final void makeEntity() throws Exception {

		setFirstName();
		setLastName();
		setUserEmail();
		
		setUserPW();
		setMobileNum();
		setCountry();
		setAddress1();
		setAddress2();
		setSubScriber();
		
	}

	public final void saveEntitys() throws Exception {
		
		saveEntity();
	}
	
}
