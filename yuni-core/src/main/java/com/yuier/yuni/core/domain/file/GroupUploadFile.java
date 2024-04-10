package com.yuier.yuni.core.domain.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Title: GroupUploadFile
 * @Author yuier
 * @Package com.yuier.yuni.core.domain.file
 * @Date 2024/4/10 23:52
 * @description: 群文件上传事件中的 file 字段类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupUploadFile {

    private String id;

    private String name;

    // 文件大小（字节数）
    private Long size;

    // busid 目前不清楚有什么用
    private Long busid;
}
