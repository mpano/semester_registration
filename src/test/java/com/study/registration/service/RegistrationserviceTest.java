package com.study.registration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.study.registration.Dao.Registrationrep;
import com.study.registration.entity.registration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Registrationservice.class})
@ExtendWith(SpringExtension.class)
class RegistrationserviceTest {
    @MockBean
    private Registrationrep registrationrep;

    @Autowired
    private Registrationservice registrationservice;

    /**
     * Method under test: {@link Registrationservice#findall()}
     */
    @Test
    void testFindall() {
        ArrayList<registration> registrationList = new ArrayList<>();
        when(registrationrep.findAll()).thenReturn(registrationList);
        List<registration> actualFindallResult = registrationservice.findall();
        assertSame(registrationList, actualFindallResult);
        assertTrue(actualFindallResult.isEmpty());
        verify(registrationrep).findAll();
    }

    /**
     * Method under test: {@link Registrationservice#findall()}
     */
    @Test
    void testFindall2() {
        when(registrationrep.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> registrationservice.findall());
        verify(registrationrep).findAll();
    }

    /**
     * Method under test: {@link Registrationservice#findbyid(int)}
     */
    @Test
    void testFindbyid() {
        registration registration = new registration();
        registration.setAmount(10);
        registration.setCourse(1);
        registration.setCredits(1);
        registration.setId(1);
        registration.setSudent_name("Sudent name");
        Optional<registration> ofResult = Optional.of(registration);
        when(registrationrep.findById((Integer) any())).thenReturn(ofResult);
        assertSame(registration, registrationservice.findbyid(1));
        verify(registrationrep).findById((Integer) any());
    }

    /**
     * Method under test: {@link Registrationservice#findbyid(int)}
     */
    @Test
    void testFindbyid2() {
        when(registrationrep.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> registrationservice.findbyid(1));
        verify(registrationrep).findById((Integer) any());
    }

    /**
     * Method under test: {@link Registrationservice#findbyid(int)}
     */
    @Test
    void testFindbyid3() {
        when(registrationrep.findById((Integer) any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> registrationservice.findbyid(1));
        verify(registrationrep).findById((Integer) any());
    }

    /**
     * Method under test: {@link Registrationservice#save(registration)}
     */
    @Test
    void testSave() {
        registration registration = new registration();
        registration.setAmount(10);
        registration.setCourse(1);
        registration.setCredits(1);
        registration.setId(1);
        registration.setSudent_name("Sudent name");
        when(registrationrep.save((registration) any())).thenReturn(registration);

        registration registration1 = new registration();
        registration1.setAmount(10);
        registration1.setCourse(1);
        registration1.setCredits(1);
        registration1.setId(1);
        registration1.setSudent_name("Sudent name");
        registrationservice.save(registration1);
        verify(registrationrep).save((registration) any());
        assertEquals(10, registration1.getAmount());
        assertEquals("Sudent name", registration1.getSudent_name());
        assertEquals(1, registration1.getId());
        assertEquals(1, registration1.getCredits());
        assertEquals(1, registration1.getCourse());
        assertTrue(registrationservice.findall().isEmpty());
    }

    /**
     * Method under test: {@link Registrationservice#save(registration)}
     */
    @Test
    void testSave2() {
        when(registrationrep.save((registration) any())).thenThrow(new RuntimeException("An error occurred"));

        registration registration = new registration();
        registration.setAmount(10);
        registration.setCourse(1);
        registration.setCredits(1);
        registration.setId(1);
        registration.setSudent_name("Sudent name");
        assertThrows(RuntimeException.class, () -> registrationservice.save(registration));
        verify(registrationrep).save((registration) any());
    }

    /**
     * Method under test: {@link Registrationservice#delete(int)}
     */
    @Test
    void testDelete() {
        doNothing().when(registrationrep).deleteById((Integer) any());
        registrationservice.delete(1);
        verify(registrationrep).deleteById((Integer) any());
        assertTrue(registrationservice.findall().isEmpty());
    }

    /**
     * Method under test: {@link Registrationservice#delete(int)}
     */
    @Test
    void testDelete2() {
        doThrow(new RuntimeException("An error occurred")).when(registrationrep).deleteById((Integer) any());
        assertThrows(RuntimeException.class, () -> registrationservice.delete(1));
        verify(registrationrep).deleteById((Integer) any());
    }
}

