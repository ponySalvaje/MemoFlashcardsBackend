package pe.memo.memoflashcardsbe.authentication.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.memo.memoflashcardsbe.repository.IUserRepository;
import pe.memo.memoflashcardsbe.repository.entities.UserData;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final IUserRepository iUserRepository;

    @Autowired
    public JwtUserDetailsService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserData> possibleUser = iUserRepository.findUserByEmail(username);
        if (possibleUser.isPresent()) {
            return possibleUser
                    .map(userData -> new User(
                            userData.getEmail(),
                            userData.getPassword(),
                            new ArrayList<>()))
                    .orElseThrow(() -> new UsernameNotFoundException("Could not find user with given username" + username));
        } else {
            throw new UsernameNotFoundException("Could not find user with given username" + username);
        }
    }
}
