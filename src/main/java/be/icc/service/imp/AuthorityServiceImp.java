package be.icc.service.imp;


import be.icc.dto.AuthorityDto;
import be.icc.entity.Authority;
import be.icc.repository.AuthorityRepository;
import be.icc.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Student on 10-12-18.
 */
@Service
@Transactional
public class AuthorityServiceImp implements AuthorityService {
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public AuthorityDto createOrgetIfExists(String authority) {
        Authority foundAuthority=authorityRepository.findByAuthority(authority);
        if(foundAuthority==null){
            Authority createAuthority= new Authority();
            createAuthority.setAuthority(authority);
            return authorityRepository.save(createAuthority).toDto();
        }else {
            return foundAuthority.toDto();
        }
    }
}
