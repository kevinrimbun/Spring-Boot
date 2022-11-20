package net.javaSpring.springBoot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaSpring.springBoot.model.dto.ResponseData;
import net.javaSpring.springBoot.model.dto.UserDto;
import net.javaSpring.springBoot.model.entity.DetailUser;
import net.javaSpring.springBoot.model.entity.User;
import net.javaSpring.springBoot.repository.DetailUserRepository;
import net.javaSpring.springBoot.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
  
    @Autowired
    private DetailUserRepository detailUserRepository;
  
    private ResponseData<Object> responseData;
    private User user;
    private DetailUser detailUser;
    private List<User> users;
    private Map<Object, Object> data;

    @Override
    public ResponseData<Object> register(UserDto request) {
        // TODO Auto-generated method stub
        // check user apakah usernya ini udh terdaftar atau belum, check emailnya
        // select * from users where email = ?
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "User is found, please login!", null);
        } else {
            user = new User(request.getEmail(), request.getPassword());
            // save to db
            userRepository.save(user);

            detailUser = new DetailUser(request.getFirstName(), request.getLastName(), request.getPhoneNumber());
            detailUser.setUserId(user);
            // detailUser.setUserEmail(user);
            detailUserRepository.save(detailUser);

            // data spesific yg dikirim
            data = new HashMap<>();
            data.put("email", user.getEmail());
            data.put("firstName", detailUser.getFirstName());
            data.put("lastName", detailUser.getLastName());
            data.put("phoneNumber", detailUser.getPhoneNumber());

            // response
            responseData = new ResponseData<Object>(HttpStatus.CREATED.value(), "Success registered", data);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> login(UserDto request) {
        // TODO Auto-generated method stub
            // check user
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
        user = userOpt.get();

        if (request.getPassword().equals(user.getPassword())) {
            // data spesific
            data = new HashMap<>();
            data.put("email", user.getEmail());

            // response data
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Success login.", data);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "Wrong password", null);
        }

        } else {
        responseData = new ResponseData<Object>(HttpStatus.BAD_REQUEST.value(), "User is not found, please register",
            null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> updateUser(long id, UserDto request) {
        // TODO Auto-generated method stub
        Optional<User> updateOpt = userRepository.findById(id);
        if (updateOpt.isPresent()) {
            user = updateOpt.get();
            Optional<DetailUser> detailUserOptional = detailUserRepository.findByUserId(user);
            
            if (detailUserOptional.isPresent()) {
                // Update User
                detailUser = detailUserOptional.get();
                
                detailUser.setFirstName(request.getFirstName());
                detailUser.setLastName(request.getLastName());
                detailUser.setPhoneNumber(request.getPhoneNumber());
                detailUserRepository.save(detailUser);
            }

            // response data
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "Updated Success", detailUser);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "User Not Found", null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> getById(long id) {
        // TODO Auto-generated method stub
        Optional<User> userOpt = userRepository.findById(id);
        if ( userOpt.isPresent()) {
            user = userOpt.get();
            responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", user);
        } else {
            responseData = new ResponseData<Object>(HttpStatus.NOT_FOUND.value(), "empty data", null);
        }
        return responseData;
    }

    @Override
    public ResponseData<Object> getAll() {
        // TODO Auto-generated method stub
        // find all book
        users = userRepository.findAll();
    
        // response data
        responseData = new ResponseData<Object>(HttpStatus.OK.value(), "success", user);
        return responseData;
    }
    
}