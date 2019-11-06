package com.offcn.customer.service;

import com.offcn.customer.bean.Customer;

import java.util.List;

public interface CustomerService {

    //添加客户信息
    public void insertInfo(Customer customer);
    //查询所有客户信息
    public List<Customer> setectCustomer();
    //查询客户的详情
    public Customer selectCustomerById(Integer id);
    //编辑要先查询客户信息
    public Customer edit(Integer id);
    //修改客户信息
    public void update(Customer customer);
    //批量删除客户信息
    public boolean batchDelete(Integer[] ids);
    //按条件查询
    public List<Customer> search(Integer cid, String keyword, Integer orderby);
}
