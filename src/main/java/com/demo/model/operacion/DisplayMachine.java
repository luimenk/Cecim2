package com.demo.model.operacion;


// Objeto creado para despliegue de maquinas en formato (necesita conectar con DB)
public class DisplayMachine {

    private String DisplayM;
    private int codigoMetodo;

    private int NumMaq;

    private String codigoRegla;

    public DisplayMachine() {

    }



    public DisplayMachine(Integer codigoMetodo, int NumMaq) {
        this.codigoMetodo = codigoMetodo;
        this.NumMaq = NumMaq;
        this.DisplayM = "Sin Especificar";
        this.codigoRegla = "NOT";
        procesaDisplay();
    }

    public void procesaDisplay(){
        switch (codigoMetodo) {
            case 1:
                this.DisplayM = "Kit de adhesión de tintas, ADTI-01, (N/A)";
                break;
            case 2:
                switch (codigoRegla) {
                    case "REGL-06":
                        DisplayM = "Regla de 30 cm, REGL-06, (N/A)";
                        break;
                    case "REGL-07":
                        DisplayM = "Regla de 30 cm, REGL-07, (N/A)";
                        break;
                    case "REGL-08":
                        DisplayM = "Regla de 60 cm, REGL-08, (ARLY)";
                        break;
                    case "REGL-09":
                        DisplayM = "Regla de 60 cm, REGL-09, (ARLY)";
                        break;
                    case "REGL-14":
                        DisplayM = "Regla de 150 cm, REGL-14, (Keuffl&Esser profesional)";
                        break;
                }
                break;
            case 3:
                this.DisplayM = "Micrómetro, MICR-02, (Mitutoyo)";
                break;
            case 4:
                this.DisplayM = "Balanza analítica, BALA-02, (OHAUS)";
                break;
            case 5:
                switch (codigoRegla) {
                    case "HORN-04":
                        this.DisplayM = "Horno de convección, HORN-04, (Memmert), Balanza analítica, BALA-02, (OHAUS)";
                        break;
                    case "HORN-05":
                        this.DisplayM = "Horno de convección, HORN-05, (Memmert), Balanza analítica, BALA-02, (OHAUS)";
                        break;
                }
                break;
            case 6:
                this.DisplayM = "Microscopio óptico, MICRO-04, (Motic), Micrómetro, MICR-02, (Mitutoyo)";
                break;
            case 7:
                this.DisplayM = "Balanza analítica, BALA-02, (OHAUS)";
                break;
            case 8:
                switch (codigoRegla) {
                    case "FTIR-14":
                        DisplayM = "Espectrofotómetro infrarrojo FTIR iS20/iZ10, FTIR-14, (Thermo Scientific), Parrilla de calentamiento, PACA-05, (Thermo Scientific)";
                        break;
                    case "FTIR-20":
                        DisplayM = "Espectrofotómetro infrarrojo FTIR/NIR, FTIR-20, (PerkinElmer), Parrilla de calentamiento, PACA-05, (Thermo Scientific)";
                        break;
                }
                break;
            case 9:
                switch (codigoRegla) {
                    case "BALA-02":
                        DisplayM = "Analizador termogravimétrico, TGA-01, (NETZSCH), Balanza analítica, BALA-02, (OHAUS)";
                        break;
                    case "BALA-21":
                        DisplayM = "Analizador termogravimétrico, TGA-01, (NETZSCH), Balanza analítica, BALA-21, (Sartorius)";
                        break;
                }
                break;
            case 10:
                switch (codigoRegla) {
                    case "FTIR-14":
                        DisplayM = "Espectrofotómetro infrarrojo FTIR iS20/iZ10, FTIR-14, (Thermo Scientific), Micrómetro, MICR-02, (Mitutoyo)";
                        break;
                    case "FTIR-20":
                        DisplayM = "Espectrofotómetro infrarrojo FTIR/NIR, FTIR-20, (PerkinElmer), Micrómetro, MICR-02, (Mitutoyo)";
                        break;
                }
                break;
        }
    }

    public Integer getCodigoMetodo() {
        return codigoMetodo;
    }

    public void setCodigoMetodo(Integer codigoMetodo) {
        this.codigoMetodo = codigoMetodo;
    }
    public String getDisplayM() {
        return DisplayM;
    }

    public void setDisplayM(String DisplayM) {
        this.DisplayM = DisplayM;
    }

    public Integer getNumMaq() {
        return NumMaq;
    }

    public void setNumMaq(Integer NumMaq) {
        this.NumMaq = NumMaq;
    }

    public String getcodigoRegla() {
        return codigoRegla;
    }

    public void setcodigoRegla(String codigoRegla) {
        this.codigoRegla = codigoRegla;
        procesaDisplay();
    }

    public String getTruncate(double NumDisp)
    {
        Integer i;
        String Num;
        NumDisp=NumDisp*1000;
        System.out.println(NumDisp);
        i=(int) Math.abs(NumDisp);
        System.out.println(i);
        Num=Integer.toString(i);
        if (Num.length()==1)
            Num="0.00"+Num;
        else
        if (Num.length()==2)
            Num="0.0"+Num;
        else
        if (Num.length()==3)
            Num="0."+Num;
        else{
            Num=Num.substring(0,Num.length()-3)+"."+Num.substring(Num.length()-3,Num.length());
        }

        System.out.println(Num);
        return Num;
    }

}
