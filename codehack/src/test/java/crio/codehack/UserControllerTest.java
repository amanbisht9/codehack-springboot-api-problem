package crio.codehack;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import crio.codehack.controller.UserController;
import crio.codehack.dto.Userdto;
import crio.codehack.model.User;
import crio.codehack.service.Userservice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Userservice userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testCreateUser() throws Exception {
        Userdto userDto = new Userdto("1", "John");
        User createdUser = new User("1", "John");

        when(userService.createUser(any(Userdto.class))).thenReturn(createdUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value("1"))
                .andExpect(jsonPath("$.userName").value("John"));
    }

    @Test
    public void testDeleteUser_Success() throws Exception {
        String userId = "1";

        when(userService.delByUserId(userId)).thenReturn(true);

        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testDeleteUser_NotFound() throws Exception {
        String userId = "1";

        when(userService.delByUserId(userId)).thenReturn(false);

        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isNotFound())
                .andExpect(content().string("false"));
    }
}