package com.teksystems.custom_authentication_union_bank.config;
import com.teksystems.custom_authentication_union_bank.model.Customer;
import com.teksystems.custom_authentication_union_bank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("User not found"));


        //  Check if account locked
        if (!customer.isAccountNonLocked()) {
            throw new BadCredentialsException("Account is locked due to multiple failed attempts");
        }



        //  Wrong password
        if (!passwordEncoder.matches(password, customer.getPwd())) {

            int attempts = customer.getFailedAttempts() + 1;
            customer.setFailedAttempts(attempts);

            if (attempts >= 3) {
                customer.setAccountNonLocked(false);
            }

            customerRepository.save(customer);

            throw new BadCredentialsException("Invalid password");
        }



        //  Successful login → reset attempts
        customer.setFailedAttempts(0);
        customerRepository.save(customer);

        return new UsernamePasswordAuthenticationToken(
                email,
                password,
                List.of(() -> customer.getRole())
        );
    }


    
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }


}
