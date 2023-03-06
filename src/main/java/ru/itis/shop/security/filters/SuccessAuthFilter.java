//package ru.itis.shop.security.filters;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import exceptions.ru.shulmin.shop.AccountNotFoundException;
//import mappers.ru.shulmin.shop.AccountMapper;
//import repositories.ru.shulmin.shop.AccountsRepository;
//import details.security.ru.shulmin.shop.AccountUserDetails;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//public class SuccessAuthFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Autowired
//    private AccountsRepository accountsRepository;
//
//    @Autowired
//    private AccountMapper accountMapper;
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        HttpSession session = request.getSession();
//        AccountUserDetails details = (AccountUserDetails) authResult.getDetails();
//        session.setAttribute("user", accountMapper.toAccountDto(accountsRepository.findByEmail(details.getUsername()).orElseThrow(AccountNotFoundException::new)));
//        doFilter(request, response, chain);
//    }
//
//    /*
//    HttpSession session = request.getSession();
//        AccountUserDetails details = (AccountUserDetails) authResult.getDetails();
//        session.setAttribute("user", accountMapper.toAccountDto(accountsRepository.findByEmail(details.getUsername()).orElseThrow(AccountNotFoundException::new)));
//        doFilter(request, response, chain);
//     */
//}
