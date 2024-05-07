package com.yuier.yuni.function.plugins;

import com.yuier.yuni.common.annotation.Plugin;
import com.yuier.yuni.common.detector.base.BaseDetectorDefiner;
import com.yuier.yuni.common.domain.message.MessageEvent;
import com.yuier.yuni.common.domain.message.dto.SendGroupMessageDto;
import com.yuier.yuni.common.enums.MessageTypeEnum;
import com.yuier.yuni.common.service.MessageChainService;
import com.yuier.yuni.common.utils.CallOneBot;
import com.yuier.yuni.common.utils.ResponseResult;
import com.yuier.yuni.function.plugins.interf.BaseDetectorPlugin;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Title: Setu
 * @Author yuier
 * @Package com.yuier.yuni.function.plugins
 * @Date 2024/5/8 0:34
 * @description: 群友最喜欢的色图功能
 */
@Slf4j
@Component
@Plugin(id = "Setu", listener = MessageTypeEnum.GROUP)
public class Setu implements BaseDetectorPlugin {


    @Autowired
    CallOneBot callOneBot;
    @Autowired
    MessageChainService messageChainService;

    @Value("${path.setu}")
    private String setuPath;

    @Override
    public BaseDetectorDefiner detectorDefine() {
        return BaseDetectorDefiner.build()
                .addFullMatchText("/色图");
    }

    @Override
    public String description() {
        return "群友最喜闻乐见的色图功能";
    }

    @Override
    public ResponseResult<T> run(MessageEvent messageEvent) {
        Path setuDirectory = Paths.get(setuPath);
        List<Path> files = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(setuDirectory, "*.{jpg,jpeg,png,gif}")) {
            for (Path path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    files.add(path);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (!files.isEmpty()) {
            Random random = new Random();
            Path randomFile = files.get(random.nextInt(files.size()));
            callOneBot.sendGroupMessage(new SendGroupMessageDto(
                    messageEvent.getGroupId(),
                    messageChainService.buildImageChainByLocalFile(randomFile.toFile().getAbsolutePath())
            ));
        } else {
            log.info("目录中没有色图图片！");
        }
        return ResponseResult.okResult();
    }
}
