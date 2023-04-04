package com.yunbot.windog.common.conf;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 谷歌验证码配置文件
 * @author fuce 
 * @date: 2018年9月12日 上午11:02:13
 */
@Configuration
public class KaptchaConfig {
	 @Bean(name = "captchaProducer")
	    public DefaultKaptcha getKaptchaBean()
	    {
	        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
	        Properties properties = new Properties();
	        //是否有边框  默认为true  我们可以自己设置yes，no  
	        properties.setProperty("kaptcha.border", "yes");
	        //边框颜色   默认为Color.BLACK  
	        properties.setProperty("kaptcha.border.color", "105,179,90");
	        //验证码文本字符颜色  默认为Color.BLACK  
	        properties.setProperty("kaptcha.textproducer.font.color", "blue");
	        //验证码图片宽度  默认为200
	        properties.setProperty("kaptcha.image.width", "200");
	        //验证码图片高度  默认为50
	        properties.setProperty("kaptcha.image.height", "50");
	        // 验证码文本字符大小  默认为40
	        properties.setProperty("kaptcha.textproducer.font.size", "40");
	        //session key
	        properties.setProperty("kaptcha.session.key", "kaptchaCode");
	        // 验证码文本字符间距  默认为2  
	        properties.setProperty("kaptcha.textproducer.char.spac", "35");
	        //验证码文本字符长度  默认为5  
	        properties.setProperty("kaptcha.textproducer.char.length", "4");
	        //验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize) 
	        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
	        //验证码噪点颜色   默认为Color.BLACK  
	        properties.setProperty("kaptcha.noise.color", "white");
	        Config config = new Config(properties);
	        defaultKaptcha.setConfig(config);
	        return defaultKaptcha;
	    }

	    @Bean(name = "captchaProducerMath")
	    public DefaultKaptcha getKaptchaBeanMath()
	    {
	        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
	        Properties properties = new Properties();
	        //是否有边框 默认为true 我们可以自己设置yes，no
	        properties.setProperty("kaptcha.border", "yes");
	        //边框颜色 默认为Color.BLACK
	        properties.setProperty("kaptcha.border.color", "158,167,179");
	        //验证码文本字符颜色 默认为Color.BLACK
	        properties.setProperty("kaptcha.textproducer.font.color", "blue");
	        //验证码图片宽度 默认为200
	        properties.setProperty("kaptcha.image.width", "100");
	        //验证码图片高度 默认为50
	        properties.setProperty("kaptcha.image.height", "30");
	        //验证码文本字符大小 默认为40
	        properties.setProperty("kaptcha.textproducer.font.size", "30");
	        //KAPTCHA_SESSION_KEY
	        properties.setProperty("kaptcha.session.key", "kaptchaCodeMath");
	        //验证码文本生成器
	        properties.setProperty("kaptcha.textproducer.impl", "com.yunbot.windog.common.support.KaptchaTextCreator");
	        //验证码文本字符间距 默认为2
	        properties.setProperty("kaptcha.textproducer.char.spac", "5");
	        //验证码文本字符长度 默认为5
	        properties.setProperty("kaptcha.textproducer.char.length", "6");
	        //验证码文本字体样式 默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
	        properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
	        //验证码噪点颜色 默认为Color.BLACK
	        properties.setProperty("kaptcha.noise.color", "white");
	        //干扰实现类
	        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
	        //图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
	        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
	        Config config = new Config(properties);
	        defaultKaptcha.setConfig(config);
	        return defaultKaptcha;
	    }
}
