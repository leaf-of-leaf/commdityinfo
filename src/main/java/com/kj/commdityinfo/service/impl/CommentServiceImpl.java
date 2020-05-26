package com.kj.commdityinfo.service.impl;

import com.kj.commdityinfo.bean.Comment;
import com.kj.commdityinfo.bean.CommentExample;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.mapper.CommentMapper;
import com.kj.commdityinfo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kj
 * @Date 2020/5/9 13:53
 * @Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired(required = false)
    private CommentMapper commentMapper;

    @Override
    public List<Comment> findCommentByUserId(Integer userId) throws Exception{
        if(userId == null){
            throw new SystemException("userId为空");
        }
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andUserIdEqualTo(userId);
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public List<Comment> findCommentByItemId(Integer itemId) throws Exception {
        if(itemId == null){
            throw new SystemException("itemId为空");
        }
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andItemIdEqualTo(itemId);
        return commentMapper.selectByExample(commentExample);
    }

    @Override
    public void deleteCommentByCommentId(Integer commentId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andItemIdEqualTo(commentId);
        commentMapper.deleteByExample(commentExample);
    }

    @Override
    public void deleteCommentByUserId(Integer userId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andUserIdEqualTo(userId);
        commentMapper.deleteByExample(commentExample);
    }

    @Override
    public void deleteCommentByItemId(Integer itemId) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andItemIdEqualTo(itemId);
        commentMapper.deleteByExample(commentExample);
    }

    @Override
    public void saveComment(Comment comment) throws Exception {
        Integer commentId = comment.getCommentId();
        if(commentId <= 0){
            throw new SystemException("commentId不正确");
        }
        if(commentId == null){
            commentMapper.insertSelective(comment);
        }
        if(commentMapper.isExistsByCommentId(commentId) != null) {
            commentMapper.updateByPrimaryKeySelective(comment);
        }
    }
}
