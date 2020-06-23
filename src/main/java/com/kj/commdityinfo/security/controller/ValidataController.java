package com.kj.commdityinfo.security.controller;

import cn.hutool.http.ContentType;
import com.kj.commdityinfo.security.bean.ImageCode;
import com.kj.commdityinfo.security.utils.JedisUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @author kj
 * @Date 2020/4/22 21:17
 * @Version 1.0
 * 验证码生成
 */
@RestController
//@Api(tags = "生成图形验证码接口")
public class ValidataController {

    public final static String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";
    private final static Integer EXPIRETIME = 60;

    @GetMapping("/code/img")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpeg");
        System.out.println();
        ImageCode imageCode = createImageCode();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        JedisUtils.setex(uuid, EXPIRETIME, imageCode.getCode());
        Cookie uCookie = new Cookie("uuid", uuid);
        uCookie.setPath("/");
//        uCookie.setMaxAge(EXPIRETIME);
//        String domain  = request.getServerName().equals("localhost") ? "127.0.0.1" : request.getServerName();
//        uCookie.setDomain(domain);
        response.addCookie(uCookie);

        ImageIO.write(imageCode.getImage(), "jpeg", response.getOutputStream());
        System.out.println(imageCode.getCode());
    }

    private ImageCode createImageCode() {

        int width = 100; // 验证码图片宽度
        int height = 36; // 验证码图片长度
        int length = 4; // 验证码位数

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }
        g.dispose();
        return new ImageCode(image, sRand.toString(), EXPIRETIME);
    }

    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
