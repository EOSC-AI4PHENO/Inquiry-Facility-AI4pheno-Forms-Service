package com.siseth.forms.module.internal.forms.feign.user;


import com.siseth.forms.module.internal.forms.feign.user.dto.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "users-service")
public interface UserService {

    @GetMapping("/api/internal/access/users/getUserInfo")
    UserInfoDTO getUserInfo(@RequestParam String id,
                            @RequestParam String realm);

    @PostMapping("/api/internal/access/users/getUsersInfo")
    List<UserInfoDTO> getUsersInfo(@RequestBody List<String> ids,
                            @RequestParam String realm);

}
