package com.herusantoso.tokonezia.util;

import com.herusantoso.tokonezia.domain.OauthClientDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitDB {

    @Autowired
    private OauthClientDetailRepository oauthClientDetailRepository;

    @PostConstruct
    public void init() {
        initOauthClientDetails();
    }

    public void initOauthClientDetails(){
        List<OauthClientDetail> oauthClientDetailList = oauthClientDetailRepository.findAll();
        if(oauthClientDetailList.size() == 0){
            OauthClientDetail oauthClientDetail = new OauthClientDetail();
            oauthClientDetail.setClientId("adminapp");
            oauthClientDetail.setResourceIds("mw/adminapp,ms/admin,ms/user");
            oauthClientDetail.setClientSecret("{bcrypt}$2a$10$EOs8VROb14e7ZnydvXECA.4LoIhPOoFHKvVF/iBZ/ker17Eocz4Vi");
            oauthClientDetail.setAuthorizedGrantTypes("authorization_code,password,refresh_token,implicit");
            oauthClientDetail.setAccessTokenValidity(9000L);
            oauthClientDetail.setRefreshTokenValidity(3600L);
            oauthClientDetail.setAdditionalInformation("{}");
            oauthClientDetail.setScope("write,read");
            oauthClientDetailRepository.save(oauthClientDetail);
        }
    }
}
