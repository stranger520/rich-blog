package com.zuicoding.platform.blog.dao;

import com.zuicoding.platform.blog.base.Pager;
import com.zuicoding.platform.blog.modal.WpPost;
import com.zuicoding.platform.blog.modal.WpPostKey;
import com.zuicoding.platform.blog.modal.WpPostWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WpPostMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_post
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int deleteByPrimaryKey(WpPostKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_post
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int insert(WpPostWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_post
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int insertSelective(WpPostWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_post
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    WpPostWithBLOBs selectByPrimaryKey(WpPostKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_post
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int updateByPrimaryKeySelective(WpPostWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_post
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(WpPostWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wp_post
     *
     * @mbg.generated Fri Aug 04 11:24:08 CST 2017
     */
    int updateByPrimaryKey(WpPost record);

    Integer selectCountWithPage(Date date);
    List<WpPost> selectListWithPage(@Param("pager")Pager pager,
                                    @Param("date") Date date,
                                    @Param("userId")Integer userId);


    /**
     * 某个用户的 文章总数
     * @param userId
     * @return
     */
    int selectPostCount(int userId);

    List<WpPost> selectListByPager(WpPost post);

    int selectCountTester();
}