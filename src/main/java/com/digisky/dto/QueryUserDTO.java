package com.digisky.dto;

import java.util.List;

import com.digisky.po.SysUser;
/**
 * 
 * @ClassName: QueryUserDTO 
 * @Description: 用户信息查询参数封装类
 * @author dengbin
 * @date 2014年12月4日 上午10:37:26
 */
public class QueryUserDTO {

    private long total;
    private List<SysUser> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<SysUser> getRows() {
        return rows;
    }
    public void setRows(List<SysUser> rows) {
        this.rows = rows;
    }
    
}
