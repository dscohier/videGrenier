package be.icc.service;


import be.icc.dto.AuthorityDto;

/**
 * Created by Scohier Dorian on 10-12-18.
 */
public interface AuthorityService {

    AuthorityDto createOrgetIfExists(String authority);
}
