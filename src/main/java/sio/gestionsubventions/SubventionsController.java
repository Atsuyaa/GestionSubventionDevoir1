package sio.gestionsubventions;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import sio.gestionsubventions.Model.Structure;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class SubventionsController implements Initializable
{
    HashMap<String,HashMap<String, TreeMap<Integer,ArrayList<Structure>>>> lesSubventions;
    TreeItem rootSecteur;
    TreeItem rootAnnee;
    @FXML
    private AnchorPane apAffecter;
    @FXML
    private ListView lvVilles;
    @FXML
    private AnchorPane apStatistiques;
    @FXML
    private ListView lvSecteurs;
    @FXML
    private ComboBox cboAnnees;
    @FXML
    private TextField txtNomStructure;
    @FXML
    private TextField txtMontant;
    @FXML
    private Button btnAffecterSubvention;
    @FXML
    private Button btnMenuAffecter;
    @FXML
    private Button btnMenuStatistiques;
    @FXML
    private ListView lvVillesStats;
    @FXML
    private TreeView tvMontantsParSecteurs;
    @FXML
    private TreeView tvMontantsParAnnees;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        rootSecteur  = new TreeItem("Par secteur");
        tvMontantsParSecteurs.setRoot(rootSecteur);

        rootAnnee = new TreeItem("Par année");
        tvMontantsParAnnees.setRoot(rootAnnee);

        apAffecter.toFront();
        lesSubventions = new HashMap<>();
        lvVilles.getItems().addAll("Bordeaux","Nantes","Paris");
        lvSecteurs.getItems().addAll("Culture","Education","Santé","Sport");
        cboAnnees.getItems().addAll(2020,2021,2022,2023,2024,2025);
        cboAnnees.getSelectionModel().selectFirst();

        // Jeu d'essais au cas où :)
//        Structure structure1 = new Structure("Structure 1",1000);
//        Structure structure2 = new Structure("Structure 2",2000);
//        Structure structure3 = new Structure("Structure 3",3000);
//        Structure structure4 = new Structure("Structure 4",4000);
//        Structure structure5 = new Structure("Structure 5",5000);
//        Structure structure6 = new Structure("Structure 6",6000);
//        Structure structure7 = new Structure("Structure 7",7000);
//        Structure structure8 = new Structure("Structure 8",8000);
//        Structure structure9 = new Structure("Structure 9",9000);
//
//        ArrayList<Structure> lesStructuresDeBordeaux = new ArrayList<>();
//        lesStructuresDeBordeaux.add(structure1);
//        lesStructuresDeBordeaux.add(structure2);
//        lesStructuresDeBordeaux.add(structure3);
//
//        ArrayList<Structure> lesStructuresDeNantes = new ArrayList<>();
//        lesStructuresDeNantes.add(structure4);
//        lesStructuresDeNantes.add(structure5);
//        lesStructuresDeNantes.add(structure6);
//
//        ArrayList<Structure> lesStructuresDeParis = new ArrayList<>();
//        lesStructuresDeParis.add(structure7);
//        lesStructuresDeParis.add(structure8);
//        lesStructuresDeParis.add(structure9);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeBordeaux = new TreeMap<>();
//        lesAnneesDeBordeaux.put(2020, lesStructuresDeBordeaux);
//        lesAnneesDeBordeaux.put(2021, lesStructuresDeBordeaux);
//        lesAnneesDeBordeaux.put(2022, lesStructuresDeBordeaux);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeNantes = new TreeMap<>();
//        lesAnneesDeNantes.put(2020, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2021, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2022, lesStructuresDeNantes);
//        lesAnneesDeNantes.put(2023, lesStructuresDeNantes);
//
//        TreeMap<Integer,ArrayList<Structure>> lesAnneesDeParis = new TreeMap<>();
//        lesAnneesDeParis.put(2022, lesStructuresDeParis);
//        lesAnneesDeParis.put(2023, lesStructuresDeParis);
//        lesAnneesDeParis.put(2024, lesStructuresDeParis);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeBordeaux = new HashMap<>();
//        lesSecteursDeBordeaux.put("Santé", lesAnneesDeBordeaux);
//        lesSecteursDeBordeaux.put("Sport", lesAnneesDeBordeaux);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeNantes = new HashMap<>();
//        lesSecteursDeNantes.put("Education", lesAnneesDeNantes);
//        lesSecteursDeNantes.put("Culture", lesAnneesDeNantes);
//
//        HashMap<String,TreeMap<Integer,ArrayList<Structure>>> lesSecteursDeParis = new HashMap<>();
//        lesSecteursDeParis.put("Culture", lesAnneesDeParis);
//
//        lesSubventions.put("Bordeaux",lesSecteursDeBordeaux);
//        lesSubventions.put("Nantes",lesSecteursDeNantes);
//        lesSubventions.put("Paris",lesSecteursDeParis);

    }

    @FXML
    public void btnMenuClicked(Event event)
    {
        if(event.getSource()==btnMenuAffecter)
        {
            apAffecter.toFront();
        }
        else if(event.getSource()==btnMenuStatistiques)
        {
            apStatistiques.toFront();
        }
    }

    @FXML
    public void btnAffecterSubventionClicked(Event event)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (lvVilles.getSelectionModel().getSelectedItem() == null)
        {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez choisir une ville");
            alert.setHeaderText("");
            alert.showAndWait();

        }
        else if(lvSecteurs.getSelectionModel().getSelectedItem() == null)
        {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez choisir un secteur");
            alert.setHeaderText("");
            alert.showAndWait();

        }
        else if(cboAnnees.getSelectionModel().getSelectedItem().toString() == null)
        {
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez choisir une année");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if(txtNomStructure.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir le nom de la structure");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else if (txtMontant.getText().isEmpty())
        {
            alert.setTitle("Erreur de saisie");
            alert.setContentText("Veuillez saisir un montant");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Affectation réussie");
            alert1.setContentText("Subvention enredistré");
            alert1.setHeaderText("");
            alert1.showAndWait();

            String ville = lvVilles.getSelectionModel().getSelectedItem().toString();
            String secteur = lvSecteurs.getSelectionModel().getSelectedItem().toString();
            String anneeEnStr = cboAnnees.getSelectionModel().getSelectedItem().toString();
            int annee = Integer.parseInt(anneeEnStr);
            String nomStructure = txtNomStructure.getText().toString();
            String montantEnStr = txtMontant.getText().toString();
            int montant = Integer.parseInt(montantEnStr);

            Structure structure = new Structure(nomStructure, montant);
            ArrayList<Structure> lesStructure = new ArrayList<>();
            TreeMap<Integer, ArrayList<Structure>> treeMapAnnee = new TreeMap<>();

            if (!lesSubventions.containsKey(lvVilles)) {
                lesStructure.add(structure);
                treeMapAnnee.put(annee,lesStructure);
                HashMap<String,TreeMap<Integer, ArrayList<Structure>>> hashMapSecteur= new HashMap<>();
                hashMapSecteur.put(secteur,treeMapAnnee);

                lesSubventions.put(ville,hashMapSecteur);

            } else if (!lesSubventions.get(ville).containsKey(secteur)) {
                lesStructure.add(structure);
                treeMapAnnee.put(annee,lesStructure);

                lesSubventions.get(ville).put(secteur,treeMapAnnee);

            }
            else if (!lesSubventions.get(ville).get(secteur).containsKey(annee))
            {
                lesStructure.add(structure);

                lesSubventions.get(ville).get(secteur).put(annee,lesStructure);
            }
            else
            {
                lesSubventions.get(ville).get(secteur).get(annee).add(structure);
            }
            lvVillesStats.getItems().add(ville);
        }
    }

    @FXML
    public void lvVillesStatsClicked(Event event)
    {
        if(lvVillesStats.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de sélection");
            alert.setContentText("Veuillez choisir la ville");
            alert.setHeaderText("");
            alert.showAndWait();
        }
        else
        {
            //lvParSecteur
            rootSecteur.getChildren().clear();
            rootSecteur.setExpanded(true);

            rootSecteur.getChildren().clear();
            rootSecteur.setExpanded(true);
            String choixVille = lvVillesStats.getSelectionModel().getSelectedItem().toString();
            int montant = 0;
            for (String nomSecteur : lesSubventions.get(choixVille).keySet())
            {
                TreeItem noeudVille = new TreeItem(choixVille);
                noeudVille.setExpanded(true);
                rootSecteur.getChildren().add(noeudVille);
            }
            tvMontantsParSecteurs.setRoot(rootSecteur);

            //lvParAnee
            rootAnnee.getChildren().clear();
            rootAnnee.setExpanded(true);
            for (String nomSecteur : lesSubventions.get(choixVille).keySet())
            {

            }
            tvMontantsParAnnees.setRoot(rootAnnee);
        }
    }
}