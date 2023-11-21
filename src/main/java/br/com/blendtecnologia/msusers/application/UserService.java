package br.com.blendtecnologia.msusers.application;

import br.com.blendtecnologia.msusers.domain.model.User;
import br.com.blendtecnologia.msusers.presentation.dto.UserDTO;
import br.com.blendtecnologia.msusers.domain.service.UserDomainService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDomainService userDomainService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        List<User> users = userDomainService.getAllUsers();
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        
        for (User user : users) {
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            userDTOs.add(userDTO);
        }
        
        return userDTOs;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now(ZoneId.of("UTC")));

        User userSaved = userDomainService.createUser(user);
        return modelMapper.map(userSaved, UserDTO.class);
    }

    public UserDTO getUserById(Long id) {
        User user = userDomainService.getUserById(id);
        if (user != null) {
            return modelMapper.map(user, UserDTO.class);
        }
        return null;  
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userDomainService.getUserById(id);
        if(user != null) {

            //BeanUtils.copyProperties(user, userDTO, "id");
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(userDTO, user);

            //user.setId(id);
            user.setUpdatedAt(LocalDateTime.now(ZoneId.of("UTC")));
            if (user.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            User userUpdated = userDomainService.updateUser(user);
            if (userUpdated != null) {
                return modelMapper.map(userUpdated, UserDTO.class);
            }
        }        
        return null;
    }

    public void deleteUser(Long id) {
        userDomainService.deleteUser(id);
    }

    public UserDTO findByUsername(String username) {
        User user = userDomainService.findByUsername(username);
        if (user != null) {
            return modelMapper.map(user, UserDTO.class);
        }
        return null;
    }

    public boolean isUserExists(String username) {
        User user = userDomainService.findByUsername(username);
        return user != null;
    }

}