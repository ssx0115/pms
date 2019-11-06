package com.offcn;


import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AppTest {
    @Test
    public void test01(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        GoodsServiceImpl bean = context.getBean(GoodsServiceImpl.class);
        List<Goods> goodsList = bean.getGoodsList();
        for (Goods goods : goodsList) {
            System.out.println(goods);
        }
    }
}
