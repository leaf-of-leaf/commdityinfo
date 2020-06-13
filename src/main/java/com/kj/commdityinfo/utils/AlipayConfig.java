package com.kj.commdityinfo.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * @author kj
 * @Date 2020/6/10 16:41
 * @Version 1.0
 */
public class AlipayConfig {
//    public static String serverUrl = "https://openapi.alipay.com/gateway.do";
    public static String serverUrl = "https://openapi.alipaydev.com/gateway.do";
    public static String appId = "2016102600766909";
    public static String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDWpBdI//8qulgZ560WHxQzo0/qFjDXs7DQa8wXjUmdRfXUA6e5VwBIz3r/aD+o6HB9GNF1mAOquV/hupK9s3CNQcQTESkU8B3/TzYXuwx/6Pq8rInLPtUtFOTqX/1zfjjkKQ6GrlN2A1nCVWFa15k0uPdigKTq3XKgVWeke2uKt1gXy8acuo5hDhQnYSm0atoK/ACwD+f4MujXCPS0MTRhnw3fLwLrBwIT083q6c3sVcyXbzZE02+H6YUEXkiNk5QwvYqJydeum1RqTZfv8aHbZD7l7bzrw5Ao8LJxPyMYxVngE8cxgUDMIZ2qbRCdJDdajjcq+/wzNvmTM/mRKTolAgMBAAECggEBAKd6uFSUs8TiTBZCiPMm4vt75cJQ+GGG62NliYWjEBqw2SVKiNf8g9NMWbvq2ylv3T4vXFj2eX4AUUlv7EorNppyA42hKT4WMkHhy+JuaE1yPBt2QbqeV3ZqxEgBCGHSzo9xXc1HPwZpjOMu/d0FN51E6nsoyAYfLu7eVRWp2/sKsMKMgtyk5e7hY4Kti1WzSS6+GMKR2s2Jq8zIZJZdtwUzLWIoJYXfmuWMAdUyMdO6n2APeeZ8WbowszSYcISSTvGUNCdLQgEKXaPUDcNga25deoa7QPpftNtKkWnkEcmJY7YRz/rH/XyIM9pCe0xnd1b7oArKMcEv/5zRrunRUA0CgYEA74wuqppVHgCSgVdgcLPtA0pB5EM/U0Iuo4uVVoi5KhWh0X03OpnDyI7KnBHYFGwUHJcnTAu8inmZV7RSw9PAMpmtcVm1PpMPWrPE8cOonSrnWzu288wCJB54p4nfGiFmcx39B6KT2vx0r4LjeW1RyS/F4krTzrq8Tw7Jf+ZOpCsCgYEA5WH9uzMP9LX4poLsNl1thPZJWEKmWiHIK4fgAR4lYGc4wt3SmSygeweOl4rDQ1Di1Iwy5jaYWRS6nGm0DiZhUXsXvTeXt8nzFDMbzV57J+3IohApGao+FtsomKfrbZtqq22IPiBGvcWYO59ZJKbFAAY7lU7NSb5WG2rF2+pw4u8CgYEAoFY6HiLy+gP0C/LKMGWeLOeVNw3z4uyXfww8xxD4rzyPdULUprqpGyLIZrJi6Th2NbTOrs3kQuOlSdj+1Oh06umF+CrNb7bLPjC5kAEgaSKAfMZ5bLGOdVKX+7wCwuC8TC00YdnmEVuNVrTLQUl0xq7dStw+7U58IIaLRLqIxMUCgYBUVlMUIRIcx0tE7Z4r3u4FlQgii17ehM00NCEISC015L3Z5/UmnGHk2kJzNXHVrke4HlQYkLozBowWNkcdbL8bVkbEK0x9JqNZKfFu8O4XIDZEX+y8ovtxK+MhPZwyEJKHtR4CuZtI6v2t7ki4URjdiW0e2HpiQrkmG3vj/67BwQKBgQC2lN/Y0eGi1zNUg7YcykIVc3iTd7byGSsEqSy58KIgyQvQb/9SskQfxv6KR2SyqsARRUYmOAmjWZ7pliKgRGhUlgJO8WKcY2kxEs4Bcl6/Z+kK2RVAf5TOWEEpZoK2ro24aKRCMkoDOqtLQVsMG9VmGGks2DsMUThq3BJSpbm/2g==";
    public static String format = "json";
    public static String charset = "utf-8";
    public static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAguyf+dK2GfgH+MCBzVQjTgXMWCKPkP4X6Knlgb1lPqrAasDIlAKmnjP6ji21ehgbdEJL3WAGYGrPOic6A+6pKFTpFg8xaI4EP85i+oLDR2tT5hajWP1k+QN6ehvNluJXnR9dV7bGXzAaJgdUHSl2Iv2973jcR4tsN1YiJWiaOiO2VcodEtefwlJQGPqtnLDcZzv628zAr1IbD8rBu5eYula/kmWAGQd6iv1sN1kChDEH7Nmulrt2fs+6kHJrtBgWS5d/cWg+YBBph4C7nR6/Ras36JINKBD6gzY+2MEgM86L+0Vs0Ta8TIKMw1NR78VBjDOv0MXMWUHWIVKyuAeZQQIDAQAB";
    public static String signType = "RSA2";
//    public static String return_url = "http://134.175.100.63:8585/zhikongziping/children.html";
    /**
     * 买家付款成功后,如果接口中指定有return_url，买家付完款后会跳到return_url所在的页面，这个页面可以展示给客户看，且该页面只有当付款成功时才会跳转
     */
    public static String return_url = "http://47.115.1.253:8083/return";
    /**
     * 服务器后台通知。这个页面只在后台程序异步运行，即买家和卖家都看不到。买家付完款后，支付宝会调用该接口并
     * 把反馈信息POST发送至该页面。在这个页面可根据支付宝传递过来的参数进行商户本身的业务操作且需要在页面上打
     * 印出一个success给支付宝，如果反馈给支付宝的不是success，支付宝将会继续调用这个页面
     */
    public static String notify_url = "http://47.115.1.253:8083/notify";
    //日志存放
//    public static final String LOG_PATH = "E://alipay.log";

    public static AlipayClient getAlipayClient()
    {
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, publicKey, signType);

        return alipayClient;
    }

    public static AlipayClient getAlipayClient2()
    {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do", appId, privateKey, format, charset, publicKey, signType);

        return alipayClient;
    }
}
