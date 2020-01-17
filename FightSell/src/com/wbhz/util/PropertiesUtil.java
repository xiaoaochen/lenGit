package com.wbhz.util;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取全部配置文件
 */
public class PropertiesUtil {
    private static Logger logger = Logger.getLogger(PropertiesUtil.class);

    public static Properties getProperties(String propertiesFileName){
        logger.debug("method getProperties" + propertiesFileName);

        Properties properties = new Properties();
        InputStream inputStream = null;
        inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFileName);
        try{
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if (inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return properties;
    }

    /**
     * 根据键名获取文件配置
     * @param propertiesFileName 资源文件名
     * @param key 配置的键名
     * @return
     */
    public static Object getProperties(String propertiesFileName,String key){
        Properties properties = new Properties();
        InputStream inputStream = null;
        Object result = null;
        try {
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesFileName);
            properties.load(inputStream);
            result = properties.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
