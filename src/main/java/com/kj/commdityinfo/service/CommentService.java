package com.kj.commdityinfo.service;

import com.kj.commdityinfo.bean.Comment;

import java.util.List;

/**
 * @author kj
 * @Date 2020/5/9 13:52
 * @Version 1.0
 */
public interface CommentService {

    /**
     * 找到对应用户的所有评论
     * @param userId
     * @return
     */
    List<Comment> findCommentByUserId(Integer userId) throws Exception;

    /**
     * 找到对应商品的所有评论
     * @param ItemId
     * @return
     */
    List<Comment> findCommentByItemId(Integer itemId) throws Exception;

    /**
     * 删除对应commentid的评论
     * @param commentId
     */
    void deleteCommentByCommentId(Integer commentId);

    /**
     * 删除对应userId的评论
     * @param userId
     */

    void deleteCommentByUserId(Integer userId);

    /**
     * 删除对应itemId的评论
     * @param itemId
     */
    void deleteCommentByItemId(Integer itemId);

    /**
     * 增加或者修改评论
     * @param comment
     */
    void saveComment(Comment comment) throws Exception;

}
