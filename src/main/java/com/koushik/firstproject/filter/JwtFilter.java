// package com.koushik.firstproject.filter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;

// @Component
// public class JwtFilter extends OncePerRequestFilter {

//     // Inject your JWT service or utility here
//     // @Autowired
//     // private JwtService jwtService;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, 
//                                     HttpServletResponse response, 
//                                     FilterChain filterChain)
//             throws ServletException, IOException {

//         String authHeader = request.getHeader("Authorization");

//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             String jwt = authHeader.substring(7);

//             // Validate JWT and extract username (implement this method)
//             String username = extractUsername(jwt); // pseudo-method
//             if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                
//                 // Normally you'd load user details from DB
//                 // UserDetails userDetails = userDetailsService.loadUserByUsername(username);

//                 // Example setup, you should replace it with your own logic
//                 UsernamePasswordAuthenticationToken authToken = 
//                     new UsernamePasswordAuthenticationToken(username, null, null);
                
//                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

//                 SecurityContextHolder.getContext().setAuthentication(authToken);
//             }
//         }

//         filterChain.doFilter(request, response);
//     }

//     // Placeholder method: replace with actual JWT parsing logic
//     private String extractUsername(String jwt) {
//         // Use a JWT library like jjwt or java-jwt to parse and validate token
//         return "exampleUser"; // Replace with parsed username
//     }
// }

