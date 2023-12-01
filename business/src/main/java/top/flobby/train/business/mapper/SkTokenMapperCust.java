package top.flobby.train.business.mapper;

import java.util.Date;

/**
 * @author : Flobby
 * @program : train
 * @description :
 * @create : 2023-12-01 09:29
 **/

public interface SkTokenMapperCust {

    int decrease(Date date, String trainCode);
}
