package tn.esprit.spring.khaddem.entities;

public class UniversiteDTO {

    private Integer idUniversite;
    private String nomUniv;

    public UniversiteDTO(Integer idUniversite, String nomUniv) {
        this.idUniversite = idUniversite;
        this.nomUniv = nomUniv;
    }
    public Universite toEntity() {
        Universite universite = new Universite();
        universite.setIdUniversite(this.idUniversite);
        universite.setNomUniv(this.nomUniv);
        return universite;
    }
    public Integer getIdUniversite() {
        return idUniversite;
    }

    public String getNomUniv() {
        return nomUniv;
    }

    public void setIdUniversite(Integer idUniversite) {
        this.idUniversite = idUniversite;
    }

    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }
}
