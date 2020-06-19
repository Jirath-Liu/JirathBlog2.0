package com.jirath.jirathblog2.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Jirath
 * @date 2020/6/14
 * @description:
 */
@Data
public class CommentToDel {
    @NotNull(message = "评论id不能为空")
    private Integer commentId;
    @Email()
    @NotNull(message = "邮箱不为空")
    private String mail;
}
