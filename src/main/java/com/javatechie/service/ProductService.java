package com.javatechie.service;

import com.javatechie.dto.Product;
import com.javatechie.entity.StudentInfo;
import com.javatechie.entity.UserInfo;
import com.javatechie.enums.Action;
import com.javatechie.model.base.BaseResponse;
import com.javatechie.model.base.Status;
import com.javatechie.model.request.UpdateStatusRequest;
import com.javatechie.model.request.UserNewRequest;
import com.javatechie.repository.UserInfoRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    List<Product> productList = null;

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void loadProductsFromDB() {
        productList = IntStream.rangeClosed(1, 100)
                .mapToObj(i -> Product.builder()
                        .productId(i)
                        .name("product " + i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000)).build()
                ).collect(Collectors.toList());
    }


    public List<Product> getProducts() {
        return productList;
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getProductId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product " + id + " not found"));
    }


    public BaseResponse addUser(UserNewRequest data, String username) {
        BaseResponse response = new BaseResponse();
        response.getStatus().setCode(Status.SUCCESS);
        if (StringUtils.isBlank(username)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        Optional<UserInfo> user = repository.findByName(data.getName());
        if (user.isPresent()) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng đã tồn tại");
            return response;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(data.getEmail());
        userInfo.setName(data.getName());
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfo.setRoles(data.getRoles().getValue());
        repository.save(userInfo);
        return response;
    }

    public BaseResponse updateStatus(UpdateStatusRequest data2, String username) {
        BaseResponse response = new BaseResponse();
        response.getStatus().setCode(Status.SUCCESS);
        if (StringUtils.isBlank(username)) {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Người dùng không đúng");
            return response;
        }
        Optional<UserInfo> data = repository.findById(Integer.parseInt(data2.getId()));
        if (data.isPresent()) {
            UserInfo info2 = data.get();
            if (data2.getStatus().getValue().equals(Action.ACTIVE.getValue())) {
                info2.setLoginFail(0);
                info2.setTrangThai(Action.ACTIVE.getValue());
            }
            info2.setTrangThai(data2.getStatus().getValue());
            repository.save(info2);
            response.getStatus().setMessage("Cập nhật thành công");
        } else {
            response.getStatus().setCode(Status.FAIL);
            response.getStatus().setMessage("Id không tồn tại");
        }
        return response;
    }
}
