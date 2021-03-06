package com.imooc.bootsell.service;

import com.imooc.bootsell.dto.CartDTO;
import com.imooc.bootsell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo save(ProductInfo productInfo);

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 查询所有的在架商品
     *
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 商品上架
     *
     * @param productInfoId
     * @return
     */
    ProductInfo onSale(String productInfoId);

    /**
     * 商品下架
     *
     * @param productInfoId
     * @return
     */
    ProductInfo offSale(String productInfoId);

    /**
     * 减库存
     */
    void decreaseStock(List<CartDTO> dtoList);

    /**
     * 加库存
     */
    void increaseStock(List<CartDTO> dtoList);
}
