package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Mr. Hao
 * @date 2021-09-05   0:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    //id  user_id   total    used  residue
    private Long id;
    private Long userId;
    private BigDecimal total;
    private BigDecimal used;
    private BigDecimal residue;
}
