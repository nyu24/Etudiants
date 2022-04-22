/*
* Author : Nicole Yu
* Date : Apr. 21, 2022
* Description : 
*/
package application;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="etudiants")
public class EtudiantListWrapper
{
	private List<Etudiant> etudiants;
	@XmlElement(name="etudiant")
	public List<Etudiant> getEtudiants(){
		return etudiants;
	}
	public void setEtudiants(List<Etudiant> etudiants) {
		this.etudiants=etudiants;
	}
}
