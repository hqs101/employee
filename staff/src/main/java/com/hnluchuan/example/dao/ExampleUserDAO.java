package com.hnluchuan.example.dao;

import org.springframework.stereotype.Repository;

import com.hnluchuan.core.dao.BaseDAO;
import com.hnluchuan.example.model.ExampleUser;

@Repository
public class ExampleUserDAO extends BaseDAO<ExampleUser> {

	@Override
	public Long create(ExampleUser t) {
		if (t.getMoney() == null) {
			t.setMoney(0d);
		}
		return super.create(t);
	}

	public void addMoney(Long id, double amount) {
		String hql = "update ExampleUser set money = money + ? where id = ? ";
		super.executeUpdate(hql, new Object[] {amount, id});
	}
}
