package com.sip.ams.controllers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TestFirstController {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void Test1_home() throws Exception
    {
        this.mockMvc.perform(get("/home")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Symfony")));
    }

    @Test
    public void Test2_home() throws Exception
    {
        this.mockMvc.perform(get("/home")).andExpect(status().isOk())
                .andExpect(content().string(containsString("is")));
    }

    @Test
    public void Test3_home() throws Exception
    {
        this.mockMvc.perform(get("/home")).andExpect(status().isOk())
                .andExpect(content().string(containsString("framework")));
    }

}
