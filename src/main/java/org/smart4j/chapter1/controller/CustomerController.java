package org.smart4j.chapter1.controller;



import org.smart4j.chapter1.bean.Data;
import org.smart4j.chapter1.bean.Param;
import org.smart4j.chapter1.bean.View;
import org.smart4j.chapter1.model.Customer;
import org.smart4j.chapter1.service.CustomerService;





import java.util.List;
import java.util.Map;


/**
 * 处理客户管理相关请求
 */

public class CustomerController {


    private CustomerService customerService;

    /**
     * 进入 客户列表 界面
     */
    public View index(Param param){
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer_show.jsp").addModel("customerList", customerList);
    }

    /**
     * 显示客户基本信息
     */
    public View show(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_show.jsp").addModel("customer", customer);
    }

    /**
     * 进入 创建客户 界面
     */
    public View create(Param param){
        return new View("customer_create.jsp");
    }

    /**
     * 处理 创建客户 请求
     */
    public Data createSubmit(Param param){
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.createCustomer(fieldMap);
        return new Data(result);
    }

    /**
     * 进入 编辑客户 界面
     */
    public View edit(Param param){
        long id = param.getLong("id");
        Customer customer = customerService.getCustomer(id);
        return new View("customer_edit").addModel("customer",customer);
    }

    /**
     * 处理 编辑客户 请求
     */
    public Data editSubmit(Param param){
        long id = param.getLong("id");
        Map<String, Object> fieldMap = param.getParamMap();
        boolean result = customerService.updateCustomer(id, fieldMap);
        return new Data(result);
    }

    /**
     * 处理 删除客户 请求
     */
    public Data delete(Param param) {
        long id = param.getLong("id");
        boolean result = customerService.deleteCustomer(id);
        return new Data(result);//
    }



}
