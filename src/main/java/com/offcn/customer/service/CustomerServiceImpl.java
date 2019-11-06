package com.offcn.customer.service;

import com.offcn.customer.bean.Customer;
import com.offcn.customer.bean.CustomerExample;
import com.offcn.customer.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;
    @Override
    public void insertInfo(Customer customer) {
        customer.setAddtime(new Date());
        customerMapper.insert(customer);
    }
    //查询所有客户信息
    @Override
    public List<Customer> setectCustomer() {
        return customerMapper.selectByExample(new CustomerExample());
    }
    //查询客户详情信息
    @Override
    public Customer selectCustomerById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }
    //编辑要先查询客户信息
    @Override
    public Customer edit(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }
    //修改客户信息
    @Override
    public void update(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }
    //批量删除客户信息
    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> idsList = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(idsList);
        int i = customerMapper.deleteByExample(example);
        return i == ids.length;
    }
    //按条件查询
    @Override
    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();

        if(cid == 0){//按照默认的进行查询
            criteria.andComnameLike("%"+keyword+"%");
            CustomerExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCompanypersonLike("%"+keyword+"%");
            example.or(criteria1);
        }else if(cid == 1){
            criteria.andComnameLike("%"+keyword+"%");
        }else {
            criteria.andCompanypersonLike("%"+keyword+"%");
        }
        if(orderby == 1){
            example.setOrderByClause("id desc");
        }
        List<Customer> customerList = customerMapper.selectByExample(example);
        return customerList;
    }

}
