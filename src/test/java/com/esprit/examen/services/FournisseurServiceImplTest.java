package com.esprit.examen.services;

import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;



public class FournisseurServiceImplTest {

    @InjectMocks
    FournisseurServiceImpl fournisseurService;

    @Mock
    FournisseurRepository fournisseurRepository;

    @Mock
    DetailFournisseurRepository detailFournisseurRepository;

    @Mock
    ProduitRepository produitRepository;

    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAllFournisseurs() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        Fournisseur fournisseur1 = new Fournisseur();
        fournisseur1.setIdFournisseur(1L);
        fournisseur1.setLibelle("Fournisseur 1");
        Fournisseur fournisseur2 = new Fournisseur();
        fournisseur2.setIdFournisseur(2L);
        fournisseur2.setLibelle("Fournisseur 2");
        fournisseurs.add(fournisseur1);
        fournisseurs.add(fournisseur2);

        when(fournisseurRepository.findAll()).thenReturn(fournisseurs);

        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        assertEquals(2, result.size());
        assertEquals(fournisseur1, result.get(0));
        assertEquals(fournisseur2, result.get(1));
    }

    @Test
    void testAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setLibelle("Fournisseur 1");

        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        assertEquals(fournisseur, result);
    }

    @Test
    void testUpdateFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setIdFournisseur(1L);
        fournisseur.setLibelle("Fournisseur 1");
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setIdDetailFournisseur(1L);
        detailFournisseur.setDateDebutCollaboration(new Date());
        fournisseur.setDetailFournisseur(detailFournisseur);

        when(detailFournisseurRepository.save(detailFournisseur)).thenReturn(detailFournisseur);
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);

        Fournisseur result = fournisseurService.updateFournisseur(fournisseur);

        assertEquals(fournisseur, result);
        assertEquals(detailFournisseur, result.getDetailFournisseur());
    }


    @Test
    void testDeleteFournisseur() {
        // Arrange
        Long fournisseurId = 1L;

        // Act
        fournisseurService.deleteFournisseur(fournisseurId);

        // Assert
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }
    @Test
    void testRetrieveFournisseur() {
        // Arrange
        Long fournisseurId = 1L;
        Fournisseur expectedFournisseur = new Fournisseur();
        expectedFournisseur.setIdFournisseur(fournisseurId);

        when(fournisseurRepository.findById(fournisseurId)).thenReturn(Optional.of(expectedFournisseur));

        // Act
        Fournisseur actualFournisseur = fournisseurService.retrieveFournisseur(fournisseurId);

        // Assert
        assertEquals(expectedFournisseur.getIdFournisseur(), actualFournisseur.getIdFournisseur());
        verify(fournisseurRepository, times(1)).findById(fournisseurId);
    }



}