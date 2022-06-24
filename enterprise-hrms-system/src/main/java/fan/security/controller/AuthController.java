package fan.security.controller;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import fan.utils.Const;
import fan.utils.RedisUtil;
import fan.utils.Result;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName AuthController
 * @Description TODO
 * @Author 赵俊杰
 * @Date 2022/5/4 10:04
 * @Version 1.0
 */
@RestController
public class AuthController {

    @Resource
    private Producer producer;

    @Resource
    private RedisUtil redisUtil;

    /**
     * @Author 赵俊杰
     * @Description 获取验证码
     * @Date 2022/5/10 18:57
     * @return: fan.utils.Result
     */
    @GetMapping("/api/getCaptcha")
    public Result getCaptcha() throws IOException {

        String token = UUID.randomUUID().toString();
        String captcha = producer.createText(); // 生成验证码字符串

        BufferedImage image = producer.createImage(captcha);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream); // 生成图片字节数组

        Base64Encoder base64Encoder = new Base64Encoder();
        // 转换为base64，生成图片验证码
        String captchaImg = "data:image/jpg;base64," + base64Encoder.encode(byteArrayOutputStream.toByteArray());

        redisUtil.hashSet(Const.CAPTCHA_KEY, token, captcha, 120); // 将验证码存入redis

        return Result.success(MapUtil.builder().put("token", token).put("captchaImg", captchaImg).build());
    }

    /**
     * @Author 赵俊杰
     * @Description 密码加密
     * @Date 2022/5/10 18:57
     * @return: fan.utils.Result
     */
    @GetMapping("/api/password")
    public Result password() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("111");
        return Result.success(encode);
    }
}
