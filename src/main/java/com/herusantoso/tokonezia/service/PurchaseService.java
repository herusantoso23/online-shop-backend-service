package com.herusantoso.tokonezia.service;

import com.herusantoso.tokonezia.domain.Purchase;
import com.herusantoso.tokonezia.dto.PurchaseDTO;
import com.herusantoso.tokonezia.dto.VirtualAccountDTO;

public interface PurchaseService {

    VirtualAccountDTO create(PurchaseDTO purchaseDTO);

}
