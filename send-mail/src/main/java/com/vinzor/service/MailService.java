package com.vinzor.service;

public interface MailService {


    public void sendSimpleMail(String to, String subject, String content);

    //这里content为html
    public void sendHtmlMail(String to, String subject, String content);

    //发送带附件邮件
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    //发送带静态资源的邮件
    // 添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 helper.addInline(rscId, res) 来实现
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
