package com.imooc.bootsell.controller;

import com.imooc.bootsell.entity.ProductCategory;
import com.imooc.bootsell.entity.ProductInfo;
import com.imooc.bootsell.service.CategoryService;
import com.imooc.bootsell.service.ProductService;
import com.imooc.bootsell.utils.ResultVoUtil;
import com.imooc.bootsell.utils.StaticUtil;
import com.imooc.bootsell.vo.ProductInfoVo;
import com.imooc.bootsell.vo.ProductVo;
import com.imooc.bootsell.vo.ResultVo;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "/findUpAll", method = RequestMethod.GET, produces = StaticUtil.APPLICATION_JSON_UTF8_VALUE)
    public ResultVo findUpAll() {
        List<ProductInfo> productInfoList = this.productService.findUpAll();
        List<Integer> productTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());

        List<ProductCategory> productCategoryList = this.categoryService.findCategoryTypeIn(productTypeList);

        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productCategory.getCategoryType().equals(productInfo.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
                productVo.setProductInfoVoList(productInfoVoList);
                productVoList.add(productVo);
            }
        }
        return ResultVoUtil.success(productVoList);
    }

//    @ApiOperation("ccehis")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "productName", value = "组名称", required = true, paramType = "body", dataType = "int"),
//   })
//    @RequestMapping(value = "/test.do",method = RequestMethod.PUT)
//    public String test(HttpServletRequest request){
//        String productName = request.getParameter("productName");
//        return productName;
//    }


}
