package be.icc.service.imp;

import be.icc.dto.UserDto;
import be.icc.service.AuthorityService;
import be.icc.service.MailService;
import be.icc.service.UserService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Scohier Dorian on 14-03-18.
 */
@Service
@Transactional
public class MailServiceImp implements MailService {
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    @Autowired
    UserService userService;
    @Autowired
    AuthorityService authorityService;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    public void sendConfirmationSignUpEmail(UserDto user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getEmail());
                message.setFrom("videgreniernoreply@gmail.com");
                message.setSubject("Confirmation de l'inscription");
                Map model = new HashMap();
                model.put("user", user);
                // model.put("adresse", "http://localhost:8080/HiberSpring_Web_exploded/activation/" + user.getId());
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "confirmation.vm", "UTF-8", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }

    public void sendMessage(String mailTo, String content, String userFrom, String mailFrom, String productTitle) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(mailTo);
                message.setFrom("videgreniernoreply@gmail.com");
                String subject = "Nouveau message de la part de " + userFrom + " concernant l'annonce " + productTitle;
                message.setSubject(subject);
                Map model = new HashMap();
                model.put("subject", subject);
                model.put("content", content);
                model.put("mailFrom", mailFrom);
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "sendMessage.vm", "UTF-8", model);
                message.setText(text, true);
            }
        };
        this.mailSender.send(preparator);
    }
}
