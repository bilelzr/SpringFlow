package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
public class CategorieProduitServiceImplTest {
    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }


    public CategorieProduit newCategorieProduit() {
        return CategorieProduit.builder().idCategorieProduit(1L).codeCategorie("code1").libelleCategorie("libelle1").produits(null).build();
    }

    CategorieProduit categorieProduit = newCategorieProduit();


    /**
     * method test the retrieveCategorieProduit
     */
    @Test
    void retrieveAllCategorieProduits() {

        List<CategorieProduit> categorieProduits = new ArrayList<>();
        categorieProduits.add(categorieProduit);
        categorieProduits.add(categorieProduit);
        when(categorieProduitRepository.findAll()).thenReturn(categorieProduits);

        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();

        assertEquals(categorieProduits, result);
        verify(categorieProduitRepository, times(1)).findAll();
    }

    /**
     * method test the addCategorieProduit
     */
    @Test
    void addCategorieProduit() {

        when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);

        CategorieProduit result = categorieProduitService.addCategorieProduit(categorieProduit);

        assertEquals(categorieProduit, result);
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    /**
     * method test the deleteCategorieProduit
     */
    @Test
    void deleteCategorieProdui() {

        Long id = 1L;

        categorieProduitService.deleteCategorieProduit(id);

        verify(categorieProduitRepository, times(1)).deleteById(id);
    }

    /**
     * method test the updateCategorieProduit
     */
    @Test
    void updateCategorieProduit() {

        when(categorieProduitRepository.save(categorieProduit)).thenReturn(categorieProduit);

        CategorieProduit result = categorieProduitService.updateCategorieProduit(categorieProduit);

        assertEquals(categorieProduit, result);
        verify(categorieProduitRepository, times(1)).save(categorieProduit);
    }

    /**
     * method test the retrieveCategorieProduit
     */
    @Test
    void retrieveCategorieProduit() {

        Long id = 1L;
        CategorieProduit categorieProduit = new CategorieProduit(id, "1", "libelle1", null);
        when(categorieProduitRepository.findById(id)).thenReturn(Optional.of(categorieProduit));

        CategorieProduit result = categorieProduitService.retrieveCategorieProduit(id);

        assertEquals(categorieProduit, result);
        verify(categorieProduitRepository, times(1)).findById(id);
    }

}

