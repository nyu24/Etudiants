/*
* Author : Nicole Yu
* Date : Apr. 22, 2022
* Description : 
*/
package application;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class DiagCirculaire
{
	@FXML
	private PieChart pieChart;
	private ObservableList<String> intervalAges = FXCollections.observableArrayList();
	@FXML
	private void initialize() {
		intervalAges.add("0-10");
		intervalAges.add("10-20");
		intervalAges.add("20-30");
		intervalAges.add("30-40");
		intervalAges.add("40-50");
		intervalAges.add("50-60");
		
	}
	
	public void SetStat2(List<Etudiant> etudiants) {
		//compter les etudiants appartenant a la meme tranch d'age
		int[] ageCounter = new int[6]; // tableau pour les nombres de tranches d'age
		for(Etudiant e : etudiants) {
			double age = e.getAge();
			if(age <= 10) {
				ageCounter[0]++;
			}else if (age<= 20){
				ageCounter[1]++;
			}else if(age<=30) {
				ageCounter[2]++;
			}else if(age<=40) {
				ageCounter[3]++;
			}else if(age<=50) {
				ageCounter[4]++;
			}else {
				ageCounter[5]++;
			}
		}
		//ajouter les groupes d'age au diagramme circulaire
		if(ageCounter[0]>0) {
			PieChart.Data Gr1 = new PieChart.Data(intervalAges.get(0),ageCounter[0]);
			pieChart.getData().add(Gr1);
		}
		if(ageCounter[1]>0) {
			PieChart.Data Gr2 = new PieChart.Data(intervalAges.get(1),ageCounter[1]);
			pieChart.getData().add(Gr2);
		}
		if(ageCounter[2]>0) {
			PieChart.Data Gr3 = new PieChart.Data(intervalAges.get(2),ageCounter[2]);
			pieChart.getData().add(Gr3);
		}
		if(ageCounter[3]>0) {
			PieChart.Data Gr4 = new PieChart.Data(intervalAges.get(3),ageCounter[3]);
			pieChart.getData().add(Gr4);
		}
		if(ageCounter[4] >0) {
			PieChart.Data Gr5 = new PieChart.Data(intervalAges.get(4),ageCounter[4]);
			pieChart.getData().add(Gr5);
		}
		if(ageCounter[5] >0) {
			PieChart.Data Gr6 = new PieChart.Data(intervalAges.get(5),ageCounter[5]);
			pieChart.getData().add(Gr6);
		}
	}
	
}
