package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mr. Hao
 * @date 2021-09-04   23:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private Long id;
    /*产品ID*/
    private Long productId;
    /*总库存*/
    private Integer total;
    /*已用库存*/
    private Integer used;
    /*剩余库存*/
    private Integer residue;
}
