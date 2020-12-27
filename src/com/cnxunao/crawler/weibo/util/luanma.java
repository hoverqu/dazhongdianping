package com.cnxunao.crawler.weibo.util;

import org.apache.commons.lang.StringUtils;

public class luanma {
    /**
     * 替换四个字节的字符 '\xF0\x9F\x98\x84\xF0\x9F）的解决方案 ��
     */
    public static String removeFourChar(String content) {
        byte[] conbyte = content.getBytes();
        for (int i = 0; i < conbyte.length; i++) {
            if ((conbyte[i] & 0xF8) == 0xF0) {
                for (int j = 0; j < 4; j++) {
                    conbyte[i + j] = 0x30;// 0x30 int=48   字符=0
                }
                i += 3;
            }
        }
        content = new String(conbyte);
        return content.replaceAll("0000", "");
    }

    /**
     * 将emoji表情替换成*
     * @return 过滤后的字符串
     * 过滤的方式很简单，直接使用正则表达式匹配编码范围，然后替换就行了。
     */
    public static String filterEmoji(String source) {
        if (StringUtils.isNotBlank(source)) {
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
        } else {
            return source;
        }
    }

    public static void main(String[] arg) {
        try {
            System.err.println("测试->将emoji表情替换成*");
            String text = "This is a smiley \uD83C\uDFA6 face\uD860\uDD5D \uD860\uDE07 \uD860\uDEE2 \uD863\uDCCA \uD863\uDCCD \uD863\uDCD2 \uD867\uDD98 ";
            System.out.println(text);
            System.out.println(text.length());
            System.out.println(text.replaceAll("[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]", "*"));
            System.out.println(filterEmoji(text));
            //输出结果
            //This is a smiley �� face�� �� �� �� �� �� �� 
            //45
            //This is a smiley * face�� �� �� �� �� �� �� 
            //This is a smiley * face* * * * * * * 

            System.err.println("测试->替换四个字节的字符 '\\xF0\\x9F\\x98\\x84\\xF0\\x9F）的解决方案 ��");
            String title = "ff的范德萨分��������Llfldakf;dsk。f������������daslfjdsa;lfkjdsd'j'l'f'k'd'j'sa'l'k";
            System.out.println(removeFourChar(title));
            //输出结果：ff的范德萨分Llfldakf;dsk。fdaslfjdsa;lfkjdsd'j'l'f'k'd'j'sa'l'k

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}