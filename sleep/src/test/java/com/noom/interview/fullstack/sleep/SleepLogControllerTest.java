package com.noom.interview.fullstack.sleep;


import com.noom.interview.fullstack.sleep.sleeplog.SleepLogService;
import com.noom.interview.fullstack.sleep.sleeplog.controller.SleepLogController;
import com.noom.interview.fullstack.sleep.sleeplog.dto.SleepLogDTO;
import com.noom.interview.fullstack.sleep.sleeplog.dto.TimeInBedIntervalDTO;
import com.noom.interview.fullstack.sleep.sleeplog.models.MorningFeeling;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SleepLogController.class)
class SleepLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SleepLogService sleepLogService;

    @Test
    void shouldCreateSleepLog() throws Exception {
        SleepLogDTO responseDTO = new SleepLogDTO();
        responseDTO.setSleepDate(LocalDateTime.of(2025, 5, 26, 22, 30));
        responseDTO.setTotalTimeInBedSeconds(28800);
        responseDTO.setMorningFeeling(MorningFeeling.GOOD);

        Mockito.when(sleepLogService.createSleepLog(Mockito.any(), Mockito.eq(1L)))
                .thenReturn(responseDTO);

        String requestJson = "{\n" +
                "  \"timeInBedInterval\": {\n" +
                "    \"start\": \"2025-05-26T22:30:00\",\n" +
                "    \"end\": \"2025-05-27T06:30:00\"\n" +
                "  },\n" +
                "  \"totalTimeInBedSeconds\": 28800,\n" +
                "  \"morningFeeling\": \"GOOD\"\n" +
                "}";

        mockMvc.perform(post("/sleeplog/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalTimeInBedSeconds").value(28800))
                .andExpect(jsonPath("$.morningFeeling").value("GOOD"));
    }

    @Test
    void shouldFetchLastNightSleepLog() throws Exception {
        SleepLogDTO mockLog = new SleepLogDTO();
        mockLog.setSleepDate(LocalDateTime.of(2025, 5, 26, 22, 30));
        mockLog.setTotalTimeInBedSeconds(28800);
        mockLog.setMorningFeeling(MorningFeeling.GOOD);
        mockLog.setTimeInBedInterval(new TimeInBedIntervalDTO());

        Mockito.when(sleepLogService.fetchLastNightSleepLog(1L))
                .thenReturn(mockLog);

        mockMvc.perform(get("/sleeplog/fetch_last_night"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalTimeInBedSeconds").value(28800))
                .andExpect(jsonPath("$.morningFeeling").value("GOOD"));
    }

    @Test
    void shouldReturnNotFoundWhenNoLastNightLogExists() throws Exception {
        Mockito.when(sleepLogService.fetchLastNightSleepLog(1L)).thenReturn(null);

        mockMvc.perform(get("/sleeplog/fetch_last_night"))
                .andExpect(status().isNotFound());
    }
}
