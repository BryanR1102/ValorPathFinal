package pe.edu.upc.valorpathg4.dtos;

public class MostUsebetweendateDTO {
    private String tiporecurso;
    private int totalusos;

    public String getTiporecurso() {
        return tiporecurso;
    }

    public void setTiporecurso(String tiporecurso) {
        this.tiporecurso = tiporecurso;
    }

    public int getTotalusos() {
        return totalusos;
    }

    public void setTotalusos(int totalusos) {
        this.totalusos = totalusos;
    }
}