package com.zuicoding.platform.blog.dao;

import com.zuicoding.platform.blog.modal.WpTerm;
import com.zuicoding.platform.blog.modal.WpTermKey;

public interface WpTermMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int deleteByPrimaryKey(WpTermKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int insert(WpTerm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int insertSelective(WpTerm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    WpTerm selectByPrimaryKey(WpTermKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int updateByPrimaryKeySelective(WpTerm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_term
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int updateByPrimaryKey(WpTerm record);
}