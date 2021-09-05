package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Mr. Hao
 * @date 2021-09-01   22:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    //id  user_id  product_id   count   money  status
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status; //订单状态0：创建中；1已完结

}
