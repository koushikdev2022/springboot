package com.koushik.firstproject.api.controller.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.koushik.firstproject.config.appLogger.AppLogger;
import com.koushik.firstproject.dto.LoginDTO;
import com.koushik.firstproject.entity.UserEntry;
import com.koushik.firstproject.services.UserEntryService;
import com.koushik.firstproject.utill.JwtUtill;

@RestController
@RequestMapping("user")
public class UserEntryController {
        @Autowired
        private UserEntryService userEntryService;
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private JwtUtill jwtUtill;

       

        @PostMapping("create-user")
        public ResponseEntity<Object> createUser(@RequestBody Optional<UserEntry> userOptional){
            if(userOptional.isPresent()){
                UserEntry user = userOptional.get();
                UserEntry userSave = userEntryService.saveUserEntry(user);
                return ResponseEntity.status(201).body(Map.of(
                    "status", true,
                    "message", "user inser successfully",
                    "status_code", 201,
                    "data", userSave
                ));
            }
            return ResponseEntity.status(400).body(Map.of(
                "status", true,
                "message", "payload is empty",
                "status_code", 400

            ));
        } 
        // @GetMapping("/list-user")
        // public ResponseEntity<Object> allUser(){
        //     List<UserEntry> list = userEntryService.userList();
           
        //     return ResponseEntity.status(200).body(Map.of(
        //         "status", true,
        //         "message", "user found successfully",
        //         "status_code", 200,
        //         "data", list
        //     ));
        // }
        @GetMapping("/list-user")
        public ResponseEntity<Object> allUser() {
            List<UserEntry> list = userEntryService.userList();
            Optional<List<UserEntry>> optionalList = Optional.ofNullable(list.isEmpty() ? null : list);

            if (optionalList.isPresent()) {
                return ResponseEntity.status(200).body(Map.of(
                    "status", true,
                    "message", "Users found successfully",
                    "status_code", 200,
                    "data", optionalList.get()
                ));
            } else {
                return ResponseEntity.status(400).body(Map.of(
                    "status", false,
                    "message", "No users found",
                    "status_code", 400,
                    "data", List.of() // or null if you prefer
                ));
            }
        }
            @GetMapping("/list-user/{username}")
            public ResponseEntity<Object> useNameUser(@PathVariable String username) {
                try {
                    UserEntry list = userEntryService.userListSingle(username);
                    if (list == null) {
                        return ResponseEntity.status(400).body(Map.of(
                            "status", false,
                            "message", "User not found",
                            "status_code", 400
                        ));
                    }
                    // AppLogger.info(getClass(), "Name: {}", list);
                    // AppLogger.debug(getClass(), "Debug info: {}", list);
                    // AppLogger.error(getClass(), "Error occurred: {}", list);
                    AppLogger.dd(list);

                    return ResponseEntity.status(200).body(Map.of(
                        "status", true,
                        "message", "Users found successfully",
                        "status_code", 200,
                        "data", list
                    ));
                } catch (Exception e) {
                    // Logs the error for debugging (optional)
                    e.printStackTrace();
            
                    return ResponseEntity.status(500).body(Map.of(
                        "status", false,
                        "message", "An unexpected error occurred",
                        "status_code", 500,
                        "error", e.getMessage()
                    ));
                }
            }
            
            @PostMapping("/login")
            public ResponseEntity<Object> login(@RequestBody LoginDTO loginDto){
                try{
                    
                    Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            loginDto.getUserName(),
                            loginDto.getPassword()
                        )
                    );
                   
                    UserEntry user = userEntryService.userListSingle(loginDto.getUserName());
                    String jwt = jwtUtill.generateToken(user);
               
                    return ResponseEntity.status(200).body(Map.of(
                        "status", true,
                        "message", "jwt token generated",
                        "status_code", 200,
                        "jwt", jwt
                    ));
                }catch (Exception e){
                    return ResponseEntity.status(400).body(Map.of(
                        "status", false,
                        "message", "An unexpected error occurred",
                        "status_code", 400,
                        "error", e.getMessage()
                    ));
                }
             

            }
            
           
}
