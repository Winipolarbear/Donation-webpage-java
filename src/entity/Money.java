package entity;

import java.math.BigDecimal;
import java.util.Date;

import dao.UserDao;

public class Money {

	private Integer id;
	private String identifier;
	private BigDecimal money;
	private Date creatTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public int getCount(){
		return UserDao.getByIdentifier(identifier).getCount();
	}
	@Override
	public String toString() {
		return "Money{" + "id=" + id + ", identifier=" + identifier + ", money=" + money + ", creatTime=" + creatTime
				+ "}";
	}
}
