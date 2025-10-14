package cn.bugstack.xfg.dev.tech.api;

import cn.bugstack.xfg.dev.tech.api.response.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Auther: yangjian
 * @Date: 2025-10-14 - 10 - 14 - 17:24
 * @Description: cn.bugstack.xfg.dev.tech.api
 * @version: 1.0
 */
public interface IRAGService {
    Response<List<String>> queryRagTagList();

    Response<String> uploadFile(String ragTag, List<MultipartFile> files);
}
