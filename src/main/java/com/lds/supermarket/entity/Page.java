package com.lds.supermarket.entity;

import java.util.List;

public class Page<T> {
    private Integer nowPage;//当前页
    private Integer pageSum;//总页码
    private Integer countNum;//显示条数
    private Integer countSum;//总共记录数
    private List<T> list;

    @Override
    public String toString() {
        return "Page{" +
                "nowPage=" + nowPage +
                ", pageSum=" + pageSum +
                ", countNum=" + countNum +
                ", countSum=" + countSum +
                ", list=" + list +
                '}';
    }

    /**
     * 得到当前页码
     * @return
     */
    public Integer getNowPage() {
        return nowPage;
    }
    /**
     * 设置当前页码
     * @return
     */
    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    /**
     * 得到总页码
     * @return
     */
    public Integer getPageSum() {
        return pageSum;
    }

    /**
     * 设置总页码
     * @return
     */
    public void setPageSum() {
        if(countSum % countNum == 0){
            this.pageSum = countSum / countNum;
        }else {
            this.pageSum = countSum / countNum + 1;
        }
    }
    /**
     * 得到显示记录条数
     * @return
     */
    public Integer getCountNum() {
        return countNum;
    }

    /**
     * 设置显示记录条数
     * @param countNum
     */
    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    /**
     * 得到总记录条数
     * @return
     */
    public Integer getCountSum() {
        return countSum;
    }
    /**
     * 设置总记录条数
     * @param countSum
     */
    public void setCountSum(Integer countSum) {
        this.countSum = countSum;
    }

    /**
     * 得到数据列表
     * @return
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置数据列表
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }
}
