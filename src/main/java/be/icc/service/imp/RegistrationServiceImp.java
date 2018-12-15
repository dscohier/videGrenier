package be.icc.service.imp;

import be.icc.dto.UserDto;
import be.icc.entity.User;
import be.icc.service.AuthorityService;
import be.icc.service.RegistrationService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Student on 14-01-16.
 */
@Service
@Transactional
public class RegistrationServiceImp implements RegistrationService {

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

    public void sendConfirmationEmail(final User user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getEmail());
                message.setFrom("37161@heb.be"); // could be parameterized...
                message.setSubject("Confirmation de l'inscription");
                Map model = new HashMap();
                model.put("user", user);
                model.put("adresse", "http://localhost:8080/HiberSpring_Web_exploded/activation/" + user.getId());
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "confirmation.vm", model);
                message.setText(text, true);
            }
        };
        // this.mailSender.send(preparator);
    }


    public void sendEmailComingSoon(final User user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(user.getEmail());
                message.setFrom("cadusphere@gmail.com"); // could be parameterized...
                message.setSubject("Confirmation de l'inscription beta");
                Map model = new HashMap();
                model.put("user", user);
                model.put("adresse", "http://localhost:8080/HiberSpring_Web_exploded/infos");
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "confirmation.vm", model);
                message.setText(text, true);
            }
        };
        //   this.mailSender.send(preparator);
    }

}
