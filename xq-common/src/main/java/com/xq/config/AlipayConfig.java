package com.xq.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092700605413";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC+23KnCSqoJDAZiefXBGTx5RdCunacNLDdtvJpkUhUlMAuavVCoXG/GeN7E+/R5C/XccsmUuCZLPxmB90c6lMESW5otI4pi+yRK8GBjz5uVRL+u9i+fKJdKif6Rh5CQCeTiHz31nNjjV8XrKsLxOfnzIV35Ehaf9BEtjoiBBSvqa5wXddKcr2cBwHMmDKS3zWJACY+RlGxoBtkfR5jCI3QVgsxPITY2p/w2RnfLKPJX9vUcJJpur2jEyXz3puqyJ6iZcoZ73hFoGhFIsBvkUCpJasdVqPkqKPWpLLhIXK8Gzt53mnEnRx1UCmgqoreCTb6bcyxtTGvKOaPQHe982uNAgMBAAECggEAZ8hC5yun43iSI0EHLbe5AIR3ipSEbNqGFnomBVu3/i5I9rS4mzvY83araUDlFrhDf57kPfmxIfoBYaYDCXcuH1Wi0RLlfxgUUrh9Z+sxoqNmSdhxutthGtISnRhF2fayzpnYgpg9dLPqMqB0/lhbVoOzTAd5hKSd5s9EezzG1uG4Oxon9N/okAqkJ2STCNGSfZ+E5znnTnUfUFQO6ltCnydkODsXhRcVVN+EIaPi8PxozGMf1fQ9IcPECQFm2PptvHz5NRa8SNJP/NKjPk5SNv8aS46IGGc9EHuTJlxCfVdZI9H39AXZI8co27rYhh0J96r649Ap/aSAFWuvlpMs4QKBgQDpDZiAxSRS+JkQ6+aKD9Ja9bnfFL8yP7kPhztkSfDzb2350wCUaWvvEwMA0cG2/n8i2ltjfjyjB1AVtER8J8IaOvpGJmCx6Bvml/qlfKhcxgG/RoB0FoUZqKwENmD2lEeCRezkSc5VS7Ic7QAVcPRTaIr/bJcMX7kzF6Iwmaov9QKBgQDRpkATWrdOuw3/ABSx2QLnAjPn5y6WyFbLk1QFjQ4OZ3jg1OPDUkFz/H8tS2WtQxs2aQTPdQrmm1MSzN0CmGbWEwXIxPGwiaYmAZZqUfyxBK9ow3TqtuBIt8pShFaqTjeSPs3fAElfBoCHIbv4aUGDNj7vGUpiKARF6V07F9MGOQKBgEW3aHiVAPHFJlz2hMemRgIp4gmG9k882sp3ZN/ycp8QJXEHmvPKuZwSLuLO3Mk2RqgRdCdGOzN125IylA4kAp/AJuBXWLO/wKDxs+xPZzohjaI/n+uaVQTA/XxhHA6HewDQlDYSkiPX+hdHGrBPUveknXgdbu4nsIpFNqKdRVkFAoGBAIrPCJ3H8pauQxYTd6BJjyJk0vk4mCA9hTpE0dzbB/OBjwdN7X/ldnDVPV9ADJIrXFkqL4UseYs2TEqmWa7eOTiEX9AOlffNK7T+WBkNL8IE2B8FSuQdMWL0TYFVHgykvSaXbre3+Nxx8aeHLm3LQxUdVY35sMZMuIeRe6kknRQBAoGALHYengedvhjQTZmpOfNPFXssbXkRTIvp4hOhfrGzMpDWv2wKQl7Bb1ByESqwoDLoomk6ovf7HvNoW6aYEKHtqnzeDcfkYOoZqTJvOugCNa9Y6PDcUUtMgoyWNJtZD8QO+9khfwyKeoYvJY7TDUJPcZ/IcUQZf0Z9HJdHEpIcSyo=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1CTnwk/GXjK/1xMSX8gI//E8MzkOohm9hpCOoJZ6kzEqm3oGfWVWCM5fTnZy3/Rd8LBGVboHhTKc+j6fNUBsOoAxNEc9nvUd8L/XHc+2AhWjQcZ1x3Ub/vUxnu/k+FP0V56l2eadWs+wR++pn88cSf11z4jiZzQ754DG5gw7jF5Y+r5FtdQqhGNi/dyBc8/0tUH3IOkOkAzKy5NFNPuntxqHJXqGmC+wZBF7uwOZUmZXY6VN+kBeNQOZXdB3qKGVnvBDLJZTJiqq6LwAm4x3d+alGcUqwt/zVhv+likIuN7msMnOhYpumnMErBmttoZA5aGd8bvaKr2SbewwWoKRWQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://www.yuexiu.com/alipay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://www.yuexiu.com/alipay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

