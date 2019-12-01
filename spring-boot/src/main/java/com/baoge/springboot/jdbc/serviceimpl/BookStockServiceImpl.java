package com.baoge.springboot.jdbc.serviceimpl;

import com.baoge.springboot.jdbc.service.BookStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2019/12/1
 */

@Slf4j
@Service
public class BookStockServiceImpl implements BookStockService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public void stockHandle() {

        List<Map<String, Object>> result = jdbcTemplate.queryForList("select  stock from t_book_stock where  id = 1");
        Integer stock = (Integer) result.get(0).get("stock");
        if (result == null || stock <= 0) {
            log.info("库存已为0.");

            return;
        }

        stock--;

        jdbcTemplate.update("update t_book_stock set stock = ? where  id = 1", stock);
        log.info("库存还剩余：{}", stock);

    }

}
