package com.esprit.examen.services;


import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {
    public static final Long ID = 111111L;
    Operateur operateur;
    public List<Operateur> listOperateur;
    @Mock
    private OperateurRepository operateurRepository;
    @InjectMocks
    private OperateurServiceImpl operateurService = new OperateurServiceImpl();

    @BeforeEach
    public void initParams() {
        operateur = new Operateur();
        operateur.setIdOperateur(ID);
        listOperateur = new ArrayList<>();
        lenient().when(operateurRepository.findById(ID)).thenReturn(Optional.of(operateur));
        lenient().when(operateurRepository.save(operateur)).thenReturn(operateur);
        lenient().when(operateurRepository.findAll()).thenReturn(listOperateur);
    }

    @Test
    void retrieveAllOperateurs() {
        List<Operateur> expected = operateurService.retrieveAllOperateurs();
        assertEquals(listOperateur, expected);
    }

    @Test
    void addOperateur() {
        var result = operateurService.addOperateur(operateur);
        assertEquals(operateur, result);
    }

    @Test
    void updateOperateur() {
        Operateur result = operateurService.updateOperateur(operateur);
        assertEquals(operateur, result);
    }

    @Test
    void retrieveOperateur() {
        assertEquals(ID, operateurService.retrieveOperateur(ID).getIdOperateur());
    }
}

