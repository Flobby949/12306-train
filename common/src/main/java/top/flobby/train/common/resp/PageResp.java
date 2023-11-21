package top.flobby.train.common.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : Flobby
 * @program : train
 * @description : 分页返回
 * @create : 2023-11-21 15:17
 **/

@Data
public class PageResp<T> implements Serializable {

    private Long total;
    private List<T> list;
}
